"use strict";

let animal_name = document.getElementById("name");
let div = document.getElementById("scrappingWiki");
let radioButtons = document.getElementsByName("link");
let checkboxImage = document.getElementById("scrapImage");
let checkboxDescription = document.getElementById("scrapDescription");
let formulaire = document.getElementById("form");
let scrappedImage = "";
let scrappedDescripion = "";
let form_modif = document.getElementById('form-modif');
let animal_modif = document.getElementById('name_modif');
let previous = document.getElementById('prev-link');

let divGPS = document.getElementById('coordFound');
let getterExifGPS = document.getElementById('getgpscoord');
let longitudeInput = document.getElementById('Longitude');
let latitudeInput = document.getElementById('Latitude');

function getExifFromInputFile(inputArrayBuffer){ /*Recupere infos exif depuis array buffer*/
  let result = EXIF.readFromBinaryFile(inputArrayBuffer)
  let lat = result["GPSLatitude"];
  let ref = result["GPSLatitudeRef"];
  let lng = result["GPSLongitude"];
  let ref2 = result["GPSLongitudeRef"];
  if(lat === undefined || ref === undefined || lng === undefined || ref2 === undefined){
    let p = document.createElement("p");
    p.textContent = "Aucune donnée de géolocalisation trouvée !";
    divGPS.replaceChildren(p);
    return true;
  } else {
    let latDD = convertDMSToDD(lat[0], lat[1], lat[2], ref);
    let lngDD = convertDMSToDD(lng[0], lng[1], lng[2], ref2);
    longitudeInput.value = lngDD;
    latitudeInput.value = latDD;
    longitudeInput.dispatchEvent(new Event('change'));
    latitudeInput.dispatchEvent(new Event('change'));
    let p = document.createElement("p");
    p.textContent = "Longitude trouvé :" + longitudeInput.value + " " + "Latitude trouvé :" + latitudeInput.value;
    divGPS.replaceChildren(p);
    return true;
  }
}

function getExifFromWikipediaImage(scrappedImage) { /*Recupere info exif depuis lien wikipedia*/
    let img = document.createElement("img");
    img.src = scrappedImage;
    img.addEventListener("load", e => {
      EXIF.getData(e.target, function() {
          let lat = EXIF.getTag(this, 'GPSLatitude');
          let ref = EXIF.getTag(this, 'GPSLatitudeRef');
          let lng = EXIF.getTag(this, 'GPSLongitude');
          let ref2 = EXIF.getTag(this, 'GPSLongitudeRef')
          if(lat === undefined || ref === undefined || lng === undefined || ref2 === undefined){
            let p = document.createElement("p");
            p.textContent = "Aucune donnée de géolocalisation trouvée !";
            divGPS.replaceChildren(p);
          } else {
            let latDD = convertDMSToDD(lat[0], lat[1], lat[2], ref);
            let lngDD = convertDMSToDD(lng[0], lng[1], lng[2], ref2);
            longitudeInput.value = lngDD;
            latitudeInput.value = latDD;
            longitudeInput.dispatchEvent(new Event('change'));
            latitudeInput.dispatchEvent(new Event('change'));
            let p = document.createElement("p");
            p.textContent = "Longitude trouvé :" + longitudeInput.value + " " + "Latitude trouvé :" + latitudeInput.value;
            divGPS.replaceChildren(p);
          }
      });
    });
}

function convertDMSToDD(degrees, minutes, seconds, direction) { /*Fonction de conversion DMS en DecimalDegrees (DD)*/
    let decimaldegrees = degrees + minutes/60 + seconds/(60*60);
    if (direction == "S" || direction == "W") {
        decimaldegrees = decimaldegrees * -1;
    }
    return decimaldegrees;
}

async function callAPI(){ /*Réalisation des calls API page upload*/
  for (let i = 0; i < radioButtons.length; i++) {
    if(radioButtons[i].checked){
      if(checkboxImage.checked){
        let titleOfPage = splitLinkFromTab(radioButtons[i].value);
        await callAPIImageFromLink(titleOfPage);
      }
      if(checkboxDescription.checked){
        let titleOfPage = splitLinkFromTab(radioButtons[i].value);
        await callAPIDescriptionFromLink(titleOfPage);
      }
    }
  }

  let xhr = new XMLHttpRequest();
  let formData = new FormData(formulaire);

  xhr.addEventListener("readystatechange", function() {
	  if(this.readyState === 4) {
      location.href = "index.php?o=upload&a=show";
	  }
	});
  formData.append("scrappingImage", scrappedImage);
  formData.append("scrappingDescription", scrappedDescripion);
  formData.append("file_name", animal_name.value);
  xhr.open("POST", "index.php?o=upload&a=insertAnimal");
  xhr.send(formData);
}

async function callAPIModif(){ /*Réalisation des calls API page modif*/
  if(radioButtons.length === 0){
    if(checkboxImage.checked){
      let titleOfPage = splitLinkFromTab(previous.textContent);
      await callAPIImageFromLink(titleOfPage);
    }
    if(checkboxDescription.checked){
      let titleOfPage = splitLinkFromTab(previous.textContent);
      await callAPIDescriptionFromLink(titleOfPage);
    }
  }else{
    for (let i = 0; i < radioButtons.length; i++) {
      if(radioButtons[i].checked){
        if(checkboxImage.checked){
          let titleOfPage = splitLinkFromTab(radioButtons[i].value);
          await callAPIImageFromLink(titleOfPage);
        }
        if(checkboxDescription.checked){
          let titleOfPage = splitLinkFromTab(radioButtons[i].value);
          await callAPIDescriptionFromLink(titleOfPage);
        }
      }
    }
  }
  
  let xhr = new XMLHttpRequest();
  let formData = new FormData(form_modif);
  xhr.addEventListener("readystatechange", function() {
    if(this.readyState === 4) {
      location.href = window.location.href;
    }
  });
  formData.append("scrappingImage", scrappedImage);
  formData.append("scrappingDescription", scrappedDescripion);
  formData.append("file_name", animal_modif.value);
  
  xhr.open("POST", "index.php?o=edit&a=updateAnimal");
  xhr.send(formData);
}

function callAPILinkWebsite(input){ /*Call API pour récup les liens de page*/
	let xhr = new XMLHttpRequest();

	xhr.addEventListener("readystatechange", function() {
	  if(this.readyState === 4) {
	    console.log(this.responseText);
	    let array = JSON.parse(this.responseText);
	    createListOfLinks(array[3]);
	  }
	});

	xhr.open("GET", "https://fr.wikipedia.org/w/api.php?action=opensearch&search=" + input + "&limit=5&origin=*&namespace=0&format=json", true);
	xhr.withCredentials = false;
	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
	xhr.send();

}

function callAPILinkWebsiteModif(input){ /*Call API pour récup les liens de page (Page modif)*/
  let xhr = new XMLHttpRequest();
  xhr.addEventListener("readystatechange", function() {
    if(this.readyState === 4) {
      console.log(this.responseText);
      let array = JSON.parse(this.responseText);
      createListOfLinksModif(array[3]);
    }
  });
  xhr.open("GET", "https://fr.wikipedia.org/w/api.php?action=opensearch&search=" + input + "&limit=5&origin=*&namespace=0&format=json", true);
  xhr.withCredentials = false;
  xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
  xhr.send();
}

async function callAPIImageFromLink(input){ /*Call API sur lien wikipedia pour récup l'image*/
  return new Promise((resolve, reject) => {
    let xhr = new XMLHttpRequest();

  	xhr.addEventListener("readystatechange", function() {
  	  if(this.readyState === 4) {
  	    console.log(this.responseText);
  	    let array = JSON.parse(this.responseText);
        if(array["query"]["pages"][0]["original"] === undefined){
          scrappedImage = "./images/imageBlanche.jpg";
        } else {
          scrappedImage = array["query"]["pages"][0]["original"]["source"];
        }
        resolve();
  	  }
  	});

  	xhr.open("GET", "https://fr.wikipedia.org/w/api.php?action=query&format=json&formatversion=2&prop=pageimages%7Cpageterms&piprop=original&titles="+ input +"&origin=*");
  	xhr.withCredentials = false;
  	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
  	xhr.send();
  })

}

async function callAPIDescriptionFromLink(input){ /*Call API sur lien wikipedia pour récup la description*/ 
   return new Promise((resolve,reject) => {
      let xhr = new XMLHttpRequest();

     	xhr.addEventListener("readystatechange", function() {
     	  if(this.readyState === 4) {
     	    console.log(this.responseText);
     	    let array = JSON.parse(this.responseText);
     	    let pageID = Object.keys(array["query"]["pages"]);
     	    scrappedDescripion = array["query"]["pages"][pageID]["extract"];
          resolve();
     	  }
     	});

     	xhr.open("GET", "https://fr.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=" + input + "&origin=*");
     	xhr.withCredentials = false;
     	xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
     	xhr.send();
   })

 }


function splitLinkFromTab(link){ /*Splitter de lien pour conserver uniquement le titre*/
	let newStr = link.split("/");

	return newStr[4];
}


function createListOfLinks(res){ /*Création d'une div avec tout les liens (Page Upload)*/
  if(res === undefined){
    div.removeChild(div.firstChild);
  } else {
    let replaceDiv = document.createElement("fieldset");
    let label = document.createElement("legend");
    label.textContent = "Sélection d'un lien :";
    replaceDiv.appendChild(label);
    replaceDiv.classList.add("fieldsetLinks");
    for (let i = 0; i < res.length; i++) {
        let strid = "link"+i;
        let radio = document.createElement('input');
        let label = document.createElement('label');
        let a = document.createElement('a');
        radio.setAttribute("type", "radio");
        radio.id = strid;
        radio.setAttribute("name", "link");
        radio.setAttribute("value", res[i]);
        radio.setAttribute("required", "")
        label.setAttribute("for", strid);
        a.setAttribute("href", res[i]);
        a.setAttribute("target", "_blank");
        a.textContent = res[i];
        label.appendChild(a);
        replaceDiv.appendChild(radio);
        replaceDiv.appendChild(label);
    }
    div.replaceChildren(replaceDiv);
    console.log(div);
  }
}

function createListOfLinksModif(res){ /*Création d'une div avec tout les liens (Page modif)*/
if(res === undefined){
    div.removeChild(div.firstChild);
  } else {
    let replaceDiv = document.createElement("fieldset");
    let label = document.createElement("legend");
    label.textContent = "Sélection d'un lien :";
    replaceDiv.appendChild(label);
    replaceDiv.classList.add("fieldsetLinks");
    for (let i = 0; i < res.length; i++) {
        let strid = "link"+i;
        let radio = document.createElement('input');
        let label = document.createElement('label');
        let a = document.createElement('a');
        radio.setAttribute("type", "radio");
        radio.id = strid;
        radio.setAttribute("name", "link");
        radio.setAttribute("value", res[i]);
        radio.setAttribute("required", "")
        label.setAttribute("for", strid);
        a.setAttribute("href", res[i]);
        a.setAttribute("target", "_blank");
        a.textContent = res[i];
        label.appendChild(a);
        replaceDiv.appendChild(radio);
        replaceDiv.appendChild(label);
    }
    div.replaceChildren(replaceDiv);
    console.log(div);
  }
}


if(animal_name){ /*On regarde si l'element existe en DOM avant de coller un event listener*/
  animal_name.addEventListener("keyup", e =>{
      callAPILinkWebsite(animal_name.value);
  });
}

if(animal_modif){/*On regarde si l'element existe en DOM avant de coller un event listener*/
  animal_modif.addEventListener("keyup", e =>{
    callAPILinkWebsiteModif(animal_modif.value);
  });
}

if(formulaire){/*On regarde si l'element existe en DOM avant de coller un event listener*/
  formulaire.addEventListener("submit", async e => {
    e.preventDefault();
    await callAPI();
  })
}

if(form_modif){/*On regarde si l'element existe en DOM avant de coller un event listener*/
  form_modif.addEventListener("submit", async e => {
    e.preventDefault();
    await callAPIModif();
  })
}

getterExifGPS.addEventListener('click', async e => { /*Récupération données GPS*/
  e.preventDefault();
  if(radioButtons.length === 0 && previous){
    if(checkboxImage.checked){
        let titleOfPage = splitLinkFromTab(previous.textContent);
        await callAPIImageFromLink(titleOfPage);
        getExifFromWikipediaImage(scrappedImage);
        scrappedImage = '';
      }
      else if(input.files.length > 0){
        let fr = new FileReader();
        fr.addEventListener("load", e => {
            getExifFromInputFile(e.target.result);
        });
        fr.readAsArrayBuffer(input.files[0]);
      }
  }
  for (let i = 0; i < radioButtons.length; i++) {
    if(radioButtons[i].checked){
      if(checkboxImage.checked){
        let titleOfPage = splitLinkFromTab(radioButtons[i].value);
        await callAPIImageFromLink(titleOfPage);
        getExifFromWikipediaImage(scrappedImage);
      }
      else if(input.files.length > 0){
        let fr = new FileReader();
        fr.addEventListener("load", e => {
            getExifFromInputFile(e.target.result);
        });
        fr.readAsArrayBuffer(input.files[0]);
      }
    }
  }
})