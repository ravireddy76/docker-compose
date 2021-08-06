# nomansland-eve

#Prerequisites
py -m pip install SpeechRecognition
py -m pip install pyttsx3
py -m pip install pipwin   (for portaudio issue)
py -m pipwin install pyaudio

py -m pip install google-cloud-speech
py -m pip install google-cloud-dialogflow

- python3 -m venv py38
- source py38/bin/activate
- pip install -r requirements.txt

#How to run the app
1. Execute server.py and agenteve.py simultaneously in two different terminals
2. Open /UI/client.html to view the UI and speak to EVE.

