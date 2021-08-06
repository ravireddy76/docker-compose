from __future__ import division

import re
import sys
import uuid
import pyttsx3
import logging 
import time 
import os

from google.cloud import speech
from twilio.rest import Client

import pyaudio
from six.moves import queue

# create logger with log app
real_path = os.path.realpath(__file__)
dir_path = os.path.dirname(real_path)
print(real_path)
LOGFILE = "{real_path}/test.log"
logger = logging.getLogger('log_app')
logger.setLevel(logging.DEBUG)
fh = logging.FileHandler(LOGFILE)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
fh.setFormatter(formatter)
logger.addHandler(fh)

# Audio recording parameters
RATE = 16000
CHUNK = int(RATE / 10)  # 100ms

#Twilio account setup
account_sid = 'xxx'
auth_token = 'xxx'

engine = pyttsx3.init()
# engine.setProperty("rate", 178)

class MicrophoneStream(object):
    """Opens a recording stream as a generator yielding the audio chunks."""
    
    def __init__(self, rate, chunk):
        self._rate = rate
        self._chunk = chunk

        # Create a thread-safe buffer of audio data
        self._buff = queue.Queue()
        self.closed = True

    def __enter__(self):
        self._audio_interface = pyaudio.PyAudio()
        self._audio_stream = self._audio_interface.open(
            format=pyaudio.paInt16,
            # The API currently only supports 1-channel (mono) audio
            # https://goo.gl/z757pE
            channels=1,
            rate=self._rate,
            input=True,
            frames_per_buffer=self._chunk,
            # Run the audio stream asynchronously to fill the buffer object.
            # This is necessary so that the input device's buffer doesn't
            # overflow while the calling thread makes network requests, etc.
            stream_callback=self._fill_buffer,
        )

        self.closed = False

        return self

    def __exit__(self, type, value, traceback): 
             
        self._audio_stream.stop_stream()
        self._audio_stream.close()
        self.closed = True
        # Signal the generator to terminate so that the client's
        # streaming_recognize method will not block the process termination.
        self._buff.put(None)
        self._audio_interface.terminate()

    def _fill_buffer(self, in_data, frame_count, time_info, status_flags):
        """Continuously collect data from the audio stream, into the buffer."""
       
        self._buff.put(in_data)
        return None, pyaudio.paContinue

    def generator(self):
        while not self.closed:
            # Use a blocking get() to ensure there's at least one chunk of
            # data, and stop iteration if the chunk is None, indicating the
            # end of the audio stream.
            chunk = self._buff.get()
            if chunk is None:
                return
            data = [chunk]

            # Now consume whatever other data's still buffered.
            while True:
                try:
                    chunk = self._buff.get(block=False)
                    if chunk is None:
                        return
                    data.append(chunk)
                except queue.Empty:
                    break

            yield b"".join(data)


def listen_print_loop(responses):
    num_chars_printed = 0
    for response in responses:
        if not response.results:
            continue

        # The `results` list is consecutive. For streaming, we only care about
        # the first result being considered, since once it's `is_final`, it
        # moves on to considering the next utterance.
        result = response.results[0]
        if not result.alternatives:
            continue

        # Display the transcription of the top alternative.
        transcript = result.alternatives[0].transcript

        # Display interim results, but with a carriage return at the end of the
        # line, so subsequent lines will overwrite them.
        #
        # If the previous result was longer than this one, we need to print
        # some extra spaces to overwrite the previous result
        overwrite_chars = " " * (num_chars_printed - len(transcript))

        if not result.is_final:
            sys.stdout.write(transcript + overwrite_chars + "\r")
            sys.stdout.flush()

            num_chars_printed = len(transcript)

        else:
            print("User : "+transcript + overwrite_chars)
            logger.info("User : "+transcript + overwrite_chars)
            text = transcript + overwrite_chars            

            # Exit recognition if any of the transcribed phrases could be
            # one of our keywords.
            if re.search(r"\b(exit|quit)\b", transcript, re.I):
                print("Exiting..")
                break
            elif re.search(r"\b(live agent|liveagent)\b", transcript, re.I):
                print('Eve : I will connect you now. Just a moment')
                logger.info('Eve : I will connect you now. Just a moment')
                speak('I will connect you now. Just a moment')
                print('Eve : Calling 18776995710....')
                logger.info(' ')
                
                callLiveAgent()
                break
            else:
                detect_intent_with_texttospeech_response(text)

            num_chars_printed = 0


def speak(text):
    engine.say(text)
    engine.runAndWait()
          

def callLiveAgent():
    from playsound import playsound
    playsound('Rev.mp3')
#    client = Client(account_sid, auth_token)
# message = client.messages.create(
#     to="+1XXXXXXXXXX", 
#     from_="+1XXXXXXXXXX",
#     body="Hello from Eve!")
# print(message.sid)

# [START dialogflow_detect_intent_with_texttospeech_response]
def detect_intent_with_texttospeech_response(text):
    from google.cloud import dialogflow

    project_id = "river-button-321115"
    session_id = str(uuid.uuid4())
    language_code = "en-US"

    session_client = dialogflow.SessionsClient()

    session_path = session_client.session_path(project_id, session_id)
    
    text_input = dialogflow.TextInput(text=text, language_code=language_code)

    query_input = dialogflow.QueryInput(text=text_input)

    # Set the query parameters with sentiment analysis
    output_audio_config = dialogflow.OutputAudioConfig(
        audio_encoding=dialogflow.OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_LINEAR_16
    )

    request = dialogflow.DetectIntentRequest(
        session=session_path,
        query_input=query_input,
        output_audio_config=output_audio_config,
    )
    response = session_client.detect_intent(request=request)

    logger.info("Eve : {}\n".format(response.query_result.fulfillment_text))   
    speak(response.query_result.fulfillment_text)
    print("Eve : {}\n".format(response.query_result.fulfillment_text))  
    

def main():
    # See http://g.co/cloud/speech/docs/languages
    # for a list of supported languages.
    language_code = "en-US"  # a BCP-47 language tag

    client = speech.SpeechClient()
    config = speech.RecognitionConfig(
        encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=RATE,
        language_code=language_code,
    )

    streaming_config = speech.StreamingRecognitionConfig(
        config=config, interim_results=True
    )

    with MicrophoneStream(RATE, CHUNK) as stream:
        audio_generator = stream.generator()
        requests = (
            speech.StreamingRecognizeRequest(audio_content=content)
            for content in audio_generator
        )

        responses = client.streaming_recognize(streaming_config, requests)

        # Now, put the transcription responses to use.
        listen_print_loop(responses)


if __name__ == "__main__":
    main()