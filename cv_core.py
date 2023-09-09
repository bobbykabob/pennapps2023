# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import cv2
import numpy as np
from ultralytics import YOLO
import json
import os
from roboflow import Roboflow

class CV_Core:
    #call once
    def __init__(self):
        self.cap = cv2.VideoCapture(0)
        cwd = os.getcwd()
        rf = Roboflow(api_key="mIvPWCB2jGHVELOKW2NA")
        project = rf.workspace().project("ecolens")
        self.model = project.version(2).model

        # show video through opencv library
        self.show_video = True
    #call every loop
    def update(self):
        ret, frame = self.cap.read()
        cv2.imwrite('temp.jpg', frame)
        self.model.predict('temp.jpg', confidence=75, overlap=30).save("prediction.jpg")
        detection_output = self.model.predict('temp.jpg', confidence=50, overlap=30)

        if self.show_video:
            self.show_frame(frame)
        dictionary = {}

        print(detection_output)

        if detection_output is not [] or detection_output is not None:
            detection_output_json = detection_output.json()
            detection_output_json_predictions = detection_output_json["predictions"]
            detection_output_json_predictions_list = detection_output_json_predictions[0]
            print(detection_output_json_predictions)
            dictionary = {"item_name": detection_output_json_predictions_list["class"],
                          "confidence": detection_output_json_predictions_list["confidence"]
                          }

        #print(dictionary)
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



