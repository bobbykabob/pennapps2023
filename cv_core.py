# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import cv2
import numpy as np
from ultralytics import YOLO
import json
import os

class CV_Core:
    #call once
    def __init__(self):
        self.cap = cv2.VideoCapture(0)
        cwd = os.getcwd()

        self.model = YOLO(cwd + "/runs/detect/yolov8n_v8_50e3/weights/best.pt", "v8")

        # show video through opencv library
        self.show_video = True
    #call every loop
    def update(self):
        ret, frame = self.cap.read()


        if self.show_video:
            self.show_frame(frame)
        dictionary = {"item_name": "monkey",
                      "confidence": 0.9
        }

        detection_output = self.model.predict(source=0, conf=0.75, show=True)
        print(detection_output)
        # saving json file
        cwd = os.getcwd()

        if not os.path.exists(cwd + "/cv_core"):
            os.mkdir(cwd + "/cv_core")

        json_object = json.dumps(dictionary, indent=2)
        with open(cwd + "/cv_core/camera.json", "w") as outfile:
            outfile.write(json_object)

    def show_frame(self, frame):
        cv2.imshow('frame', frame)

    #debug
    def print_hi():
        # Use a breakpoint in the code line below to debug your script.
        print(f'Goodmorning')  # Press ⌘F8 to toggle the breakpoint.



