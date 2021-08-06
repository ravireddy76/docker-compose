"""
server.py
This script will launch a web server on port 8000 which sends SSE events anytime
logs are added to our log file.
"""

from fastapi import FastAPI, Request
from sse_starlette.sse import EventSourceResponse
from datetime import datetime 
import uvicorn
from fastapi.middleware.cors import CORSMiddleware
import time 
import os

#create our app instance
app = FastAPI()

#add CORS so our web page can connect to our api
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

file = open('test.log','r')

async def logGenerator(request):
    '''generator function that yields new lines in a file
    '''
    # seek the end of the file
    # file.seek(0, os.SEEK_END)
    # start infinite loop
    while True:
        # read last line of file
        line = file.readline()
        # sleep if file hasn't been updated
        if await request.is_disconnected():
            print("client disconnected!!!")
            break
        yield line
        time.sleep(0.1)

# async def logGenerator(request):
#     '''generator function that yields new lines in a file
#     '''
#     # seek the end of the file
#     file.seek(0, os.SEEK_END)
#     print('enter')
#     # start infinite loop
#     while True:
#         # read last line of file
#         line = file.readline()        
#         # sleep if file hasn't been updated
#         if await request.is_disconnected():
#             print("client disconnected!!!")
#             break
#         if not line:
#             time.sleep(0.5)
#             continue
#         yield line


#This is our api endpoint. When a client subscribes to this endpoint, they will recieve SSE from our log file
@app.get('/stream-logs')
async def runStatus(request: Request):
    event_generator = logGenerator(request)
    return EventSourceResponse(event_generator)

#run the app
uvicorn.run(app, host="0.0.0.0", port=8000, debug=True)
