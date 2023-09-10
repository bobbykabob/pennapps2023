import asyncio

import cv2
from viam.media.video import RawImage
from viam.robot.client import RobotClient
from viam.rpc.dial import Credentials, DialOptions
from viam.components.camera import Camera
from PIL import Image
import numpy
import io
import pyvirtualcam


async def connect():
    creds = Credentials(
        type='robot-location-secret',
        payload='6lcjtaq262cigo5mubjoyncvu6w2ylpkqf14fpqw9va21770')
    opts = RobotClient.Options(
        refresh_interval=0,
        dial_options=DialOptions(credentials=creds)
    )
    return await RobotClient.at_address('rasp-camera-main.bndgsum6kv.viam.cloud', opts)


async def main():
    robot = await connect()

    print('Resources:')
    print(robot.resource_names)

    # cam
    cam = Camera.from_robot(robot, "cam")
    with pyvirtualcam.Camera(width=640, height=480, fps=20) as virtual_cam:

        while True:
            cam_return_value = await cam.get_image()
            print(f"cam get_image return value: {cam_return_value}")
            img = Image.open(io.BytesIO(cam_return_value.data))
            destRGB = cv2.cvtColor(numpy.array(img), cv2.COLOR_BGR2RGB)

            virtual_cam.send(numpy.array(img))
            virtual_cam.sleep_until_next_frame()
            cv2.imshow("img", destRGB)
            cv2.waitKey(1)

    # Don't forget to close the robot when you're done!
    await robot.close()


if __name__ == '__main__':
    asyncio.run(main())

