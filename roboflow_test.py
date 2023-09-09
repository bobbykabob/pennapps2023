from roboflow import Roboflow
import cv2
rf = Roboflow(api_key="mIvPWCB2jGHVELOKW2NA")
project = rf.workspace().project("ecolens")
model = project.version(2).model

cap = cv2.VideoCapture(0)
# infer on a local image
#print(model.predict(0, confidence=40, overlap=30).json())

# visualize your prediction

while True:

    model.predict("your_image.jpg", confidence=40, overlap=30).save("prediction.jpg")

# infer on an image hosted elsewhere
# print(model.predict("URL_OF_YOUR_IMAGE", hosted=True, confidence=40, overlap=30).json())