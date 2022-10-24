"use strict";


window.onload = function () {
    let myForm = document.getElementById('myForm');
    let progressBar = document.getElementById('progressBar');
    myForm.onsubmit = function (e) {
        e.preventDefault();
        let myFile = document.getElementById('myFile');
        let files = myFile.files;
        let formData = new FormData();
        for (let index = 0; index < files.length; index++) {
            formData.append('myFile' + index, files[index]);
            createImageElement(files[index]).then((value) => {
                document.body.appendChild(value);
            });
        }

        console.log(formData.get('myFile'));
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                console.log(xhr.response);
            }
        }

        xhr.open('POST', '?o=upload&a=show', true);

        xhr.upload.addEventListener('progress', (e) => {
            progressBar.value = e.loaded;
            progressBar.max = e.total;
        });
        xhr.send(formData);
    }
}

function createImageElement(file) {
    return new Promise(function (resolve, reject) {
        let fileReader = new FileReader();
        let createImage = document.createElement('img');
        createImage.id = 'img_upload';
        fileReader.addEventListener('load', e => {
            createImage.src = fileReader.result;
        });
        fileReader.readAsDataURL(file);
        resolve(createImage);
    });

}
