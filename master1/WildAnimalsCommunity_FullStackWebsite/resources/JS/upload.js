"use strict";

let progress = document.getElementById('progressBar');
let input = document.getElementById('uploadMultiple');
let dragDropArea = document.getElementById('dragNdropArea');
let array = [];

console.log(array);

dragDropArea.addEventListener('dragenter', e =>{
    e.preventDefault();
    e.stopPropagation();
    dragDropArea.classList.add('changingColor');
});

dragDropArea.addEventListener('dragover', e =>{
    e.preventDefault();
    e.stopPropagation();
    dragDropArea.classList.add('changingColor');
});

dragDropArea.addEventListener('dragleave', e =>{
    e.preventDefault();
    e.stopPropagation();
    dragDropArea.classList.remove('changingColor');
});

dragDropArea.addEventListener('drop', e => {
    e.preventDefault();
    e.stopPropagation();
    dragDropArea.classList.remove('changingColor');
    let droppedFiles = e.dataTransfer.files;
    input.files = droppedFiles;
    array.push(droppedFiles);
    console.log(array);
    displayImages();
});

input.addEventListener("change", e => {
    displayImages();
});

let form = document.getElementById("form");
if(form){
    form.addEventListener("submit",  async e => {
        await catchSubmit(e);
    });
}
let form2 = document.getElementById("form-modif");
if(form2){
    form2.addEventListener("submit",  async e => {
        await catchSubmit(e);
    });
}

async function catchSubmit(e){
    if(input.files.length > 0){
        let worker = new Worker("resources/JS/uploadWorker.js");
        worker.addEventListener("message", (message) => {
            if (message.data.inProgress){
                updateProgress(message.data.percentage);
            } else if (message.data.terminated) {
                updateProgress(100);
            } else if (message.data.terminated === false){
                alert("Échec du téléversement ! \n" + message.data.error)
            }
        });
        for(let i = 0; i < input.files.length; i++){
            worker.postMessage({'files' : input.files[i]});
        }
        document.getElementById("progressBarContainer").classList.remove("hidden");
    }
}

function displayImages(){
    let imageDiv = document.getElementById("imageZone");
    imageDiv.classList.remove("hidden");
    for (let i = 0; i < input.files.length; i++){
        addImage(imageDiv, input.files[i]);
    }
}

function addImage(parentNode, imageBlob){
    let img = document.createElement("img");
    parentNode.replaceChildren(img);
    let fr = new FileReader();
    fr.addEventListener("load", e => {
        img.src = e.target.result;
    });
    fr.readAsDataURL(imageBlob);
}

function updateProgress(percentage){
    progress.textContent = percentage + "%";
    progress.style.width = percentage + "%";
}
