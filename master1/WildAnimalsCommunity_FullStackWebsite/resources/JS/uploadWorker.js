"use strict";

function upload(files) {
    try {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../../index.php?o=upload&a=receiveImage");
        xhr.upload.addEventListener('progress', catchProgress);
        xhr.addEventListener('load', () => {
          self.postMessage({"terminated" : true, "result" : xhr.response});
        });
        let formData = new FormData();
        let mime_type;
        for (let i = 0; i < files.length; i++){
            mime_type = files[0].type;
            if (mime_type === "image/png" || mime_type === "image/jpeg"){
              formData.append("file[]", files[i], files[i].name);
            }
            else {
              throw "Type de fichier non reconnu : " + mime_type +"\nVeuillez utiliser des fichiers images."
            }
        }
        xhr.send(formData);
        return true;
    } catch (e) {
        self.postMessage({"terminated" : false, "error" : e});
    }
}

function catchProgress(e) {
    let percentage = Math.floor((e.loaded / e.total) * 100);
    self.postMessage({"inProgress" : true, "percentage" : percentage});
}

self.addEventListener("message", (message) => {
    upload(message.data.files);
});

