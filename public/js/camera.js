// camera.js

document.addEventListener('DOMContentLoaded', function() {
  var constraints = { video: { width: 800, height: 800 } };
  navigator.mediaDevices.getUserMedia(constraints)
    .then(function(mediaStream) {
      var video = document.querySelector('video');
      video.srcObject = mediaStream;
      video.onloadedmetadata = function(e) {
        video.play();
      };
    })
    .catch(function(err) {
      console.log(err.name + ": " + err.message);
    });
});
