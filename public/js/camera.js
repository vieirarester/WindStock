// camera.js

const videoElement = document.getElementById('video');

function startup() {
  navigator.mediaDevices.getUserMedia({ audio: false, video: { facingMode: 'environment' } })
    .then(stream => {
      videoElement.srcObject = stream;
    })
    .catch(error => {
      console.error('Erro ao acessar a câmera:', error);
    });
}

window.addEventListener('load', startup, false);
