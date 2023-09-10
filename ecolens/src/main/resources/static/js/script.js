const videoButton = document.getElementById('main__video-button');
const video = document.getElementById('main__video');

let mediaRecorder;

async function init() {

    try {
        const stream = await navigator.mediaDevices.getUserMedia({
            audio: true,
            video: true
        });
        startWebcam(stream);
    } catch (err) {
        console.log('Error retrieving a media device.');
        console.log(err);
    }

    // roboflow.auth({
    //
    // }).then(function(model) {
    //     // model has loaded!
    // });

}

// async function getModel() {
//     return await roboflow
//         .auth({
//             publishable_key: "rf_u7rYUutCECb4Rl3pN9hTuhh8XVg2"
//         })
//         .load({
//             model: "ecolens",
//             version: 5
//         });
// }

// var initalized_model = getModel();

// initalized_model.then(function (model){})
function startWebcam(stream) {
    window.stream = stream;
    video.srcObject = stream;
   
}

// videoButton.onclick = () => {
//     switch (videoButton.textContent) {
//         case 'Start!':
//             startRecording();
//             videoButton.textContent = 'Stop';
//             break;
//         case 'Stop':
//             videoButton.textContent = 'Record';
//             mediaRecorder.stop();
//             break;
//     }
// }

function startRecording() {
    if (video.srcObject === null) {
        video.srcObject = window.stream;
    }
    mediaRecorder = new MediaRecorder(window.stream, {mimeType: 'video/webm;codecs=vp9,opus'});
    mediaRecorder.start();
    mediaRecorder.ondataavailable = recordVideo;
    model.detect(video).then(function(predictions) {
        console.log("Predictions:", predictions);
    });
}
function getItemJSON() {
    let httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", "/Hot%20Cup"); // Use a relative URL
    httpRequest.setRequestHeader("Accept", "application/json");
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            // Parse the JSON response
            const response = JSON.parse(httpRequest.responseText);
            
            // Handle the parsed JSON data here
            console.log(response);
        } else if (httpRequest.readyState === 4) {
            // Handle errors or other status codes
            console.error("Request failed with status: " + httpRequest.status);
        }
    };
    httpRequest.send();
}

// function recordVideo(event) {
//     if (event.data && event.data.size > 0) {
//         video.srcObject = null;
//         let videoUrl = URL.createObjectURL(event.data);
//         video.src = videoUrl;
//     }
// }

// function stopRecording() {
//     mediaRecorder.stop();
// }

init();