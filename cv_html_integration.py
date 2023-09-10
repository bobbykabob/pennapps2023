# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import cv2
import numpy as np
from ultralytics import YOLO
import json
import os
from roboflow import Roboflow

from flask import Flask,render_template, Response
cap = cv2.VideoCapture(0)
cwd = os.getcwd()
rf = Roboflow(api_key="mIvPWCB2jGHVELOKW2NA")
project = rf.workspace().project("ecolens")
model = project.version(5).model

app = Flask(__name__)

def generate_frames(self):
        while True:
            success,frame=cap.read()
            ret, buffer = cv2.imencode('.jpg', frame)
            frame = buffer.tobytes()
            cv2.imwrite('temp.jpg', frame)
            self.model.predict('temp.jpg', confidence=75, overlap=30).save("prediction.jpg")
            detection_output = self.model.predict('temp.jpg', confidence=50, overlap=30)

            if self.show_video:
                self.show_frame(frame)
            dictionary = {}

            # print(detection_output)

            if len(detection_output) != 0:
                detection_output_json = detection_output.json()
                detection_output_json_predictions = detection_output_json["predictions"]

                if len(detection_output_json_predictions) != 0:
                    print(detection_output_json_predictions)
                    detection_output_json_predictions_list = detection_output_json_predictions[0]
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
            yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')

@app.route('/')
def index():
    return render_template('intro.html')

@app.route('/video')
def video():
    return Response(generate_frames(),mimetype='multipart/x-mixed-replace; boundary=frame')

if __name__=="__main__":
    app.run(debug=True)

