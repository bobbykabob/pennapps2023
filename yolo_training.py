from ultralytics import YOLO
import os

# Load the model.
model = YOLO('yolov8n.pt')

cwd = os.getcwd()
# Training.
results = model.train(
    data=cwd + '/dataset1/data.yaml',
    imgsz=1280,
    epochs=50,
    batch=8,
    name='lid1b'
)