"use strict";

let divDragDrop = document.getElementById("dragNdropArea");
let textArea = document.getElementById("desc");
let checkImage = document.getElementById("scrapImage");
let checkDescription = document.getElementById("scrapDescription");
let checkOwnImage = document.getElementById("ownImage");
let checkOwnDescription = document.getElementById("ownDescription")
let categorySelect = document.getElementById("categorie-select");
let selectedCategory = document.getElementById("selectedCategory");

divDragDrop.classList.add("hidden");
textArea.classList.add("hidden");

function removeFileFromFileList(){
	const inputFile = document.getElementById("uploadMultiple");
	inputFile.value = '';
	let imageDiv = document.getElementById("imageZone");
	setTimeout(e => {
		imageDiv.classList.add("hidden");
	},700);
	console.log(inputFile.files);
}


checkImage.addEventListener("change", e => {
	if(e.checked){
		divDragDrop.classList.add("hidden");
		removeFileFromFileList();
	} else {
		fadeOutTransition(divDragDrop);
		checkOwnImage.checked = false;
		removeFileFromFileList();
	}
});

checkDescription.addEventListener("change", e => {
	if(e.checked){
		textArea.classList.add("hidden");
	} else {
		fadeOutTransition(textArea);
		checkOwnDescription.checked = false;
	}
});

checkOwnImage.addEventListener("change", e => {
	if(e.checked){
		divDragDrop.classList.add("hidden");
	} else {
		toggleTransition(divDragDrop);
		checkImage.checked = false;
	}
});

checkOwnDescription.addEventListener("change", e => {
	if(e.checked){
		textArea.classList.add("hidden");
	} else {
		toggleTransition(textArea);
		checkDescription.checked = false;
	}
});



function toggleTransition(elem){
	if (elem.classList.contains("hidden")) {
		elem.classList.remove("hidden");
		elem.classList.add("fadein");
	} else {
		elem.classList.remove("fadein");
		elem.classList.add("fadeout");
		setTimeout(e => {
			elem.classList.add("hidden");
			elem.classList.remove("fadeout");
		},700);
	}
}

function fadeOutTransition(elem){
	if (elem.classList.contains("fadein")) {
		elem.classList.remove("fadein");
		elem.classList.add("fadeout");
		setTimeout(e => {
			elem.classList.add("hidden");
			elem.classList.remove("fadeout");
		},700);
	}
}

categorySelect.addEventListener("change", e =>{
	if(e.target.value === "Oiseaux"){
		let newDiv = document.createElement("div");
		let h3 = document.createElement("h3");
		let icon = document.createElement("img");
		icon.src = "./images/icons/60px/oiseauxIcon.png";
		h3.textContent = "Catégorie choisi : ";
		newDiv.classList.add("divCategory");
		newDiv.appendChild(h3);
		newDiv.appendChild(icon);
		selectedCategory.replaceChildren(newDiv);
	}
	if(e.target.value === "Mammifères"){
		let newDiv = document.createElement("div");
		let h3 = document.createElement("h3");
		let icon = document.createElement("img");
		icon.src = "./images/icons/60px/mammiferesIcon.png";
		h3.textContent = "Catégorie choisi : ";
		newDiv.classList.add("divCategory");
		newDiv.appendChild(h3);
		newDiv.appendChild(icon);
		selectedCategory.replaceChildren(newDiv);
	}
	if(e.target.value === "Reptiles"){
		let newDiv = document.createElement("div");
		let h3 = document.createElement("h3");
		let icon = document.createElement("img");
		icon.src = "./images/icons/60px/reptilesIcon.png";
		h3.textContent = "Catégorie choisi : ";
		newDiv.classList.add("divCategory");
		newDiv.appendChild(h3);
		newDiv.appendChild(icon);
		selectedCategory.replaceChildren(newDiv);
	}
	if(e.target.value === "Insectes"){
		let newDiv = document.createElement("div");
		let h3 = document.createElement("h3");
		let icon = document.createElement("img");
		icon.src = "./images/icons/60px/insectesIcon.png";
		h3.textContent = "Catégorie choisi : ";
		newDiv.classList.add("divCategory");
		newDiv.appendChild(h3);
		newDiv.appendChild(icon);
		selectedCategory.replaceChildren(newDiv);
	}
	if(e.target.value === "Poissons"){
		let newDiv = document.createElement("div");
		let h3 = document.createElement("h3");
		let icon = document.createElement("img");
		icon.src = "./images/icons/60px/poissonsIcon.png";
		h3.textContent = "Catégorie choisi : ";
		newDiv.classList.add("divCategory");
		newDiv.appendChild(h3);
		newDiv.appendChild(icon);
		selectedCategory.replaceChildren(newDiv);
	}
	if(e.target.value === ''){
		selectedCategory.removeChild(selectedCategory.firstChild);		
	}
})