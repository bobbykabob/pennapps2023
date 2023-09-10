import cv_core
import cv2
import base64
from pyodide.http import pyfetch

print("hello")
a_cv_core = cv_core.CV_Core()
cap = cv2.VideoCapture(0)

async def get_static_image_py(
    lon,
    lat,
    zoom,
    access_token=js.getToken(),  # retrieve access token from js
    bearing=0,
    pitch=0,
    username="mapbox",
    style_id="satellite-v9",
    overlay="",
    width=300,
    height=200,
    scale="",
):
    url = f"https://api.mapbox.com/styles/v1/{username}/{style_id}/static/{overlay}{lon},{lat},{zoom},{bearing},{pitch}/{width}x{height}{scale}?access_token={access_token}"
    response = await pyfetch(url=url, method="GET")
    return bytes_to_data_url(await response.bytes())
async def click_py():
    ret, frame = cap.read()
    _, buffer = cv2.imencode(".jpg", frame)
    data_url = bytes_to_data_url(buffer)

    return f"data:image/jpg;base64,{data_url}"

def bytes_to_data_url(img_bytes):
    return base64.b64encode(img_bytes).decode("ascii")
while True:
    click_py()
    print("hi")
while True:
    a_cv_core.update()

    