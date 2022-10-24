"use strict"

const geojson = {};

fetch("./images/data/Animaux.json")
.then(response => {
    return response.json();
})
.then(jsondata => {
    map.on('load', e => {
        map.addSource('places', {
            'type': 'geojson',
            'data': jsondata
        });
        for (const feature of jsondata.features) {
            // create a HTML element for each feature

            const el = document.createElement("div");
            el.style.width = "40px";
            el.style.height = "40px";
            el.classList.add(feature.properties.categorie);

            if(feature.properties.categorie === "Mammifères"){
                el.style.backgroundImage = `url(./images/icons/40px/mammiferesIcon.png)`
            } else if (feature.properties.categorie === "Insectes"){
                el.style.backgroundImage = `url(./images/icons/40px/insectesIcon.png)`
            } else if (feature.properties.categorie === "Oiseaux"){
                el.style.backgroundImage = `url(./images/icons/40px/oiseauxIcon.png)`
            } else if (feature.properties.categorie === "Reptiles"){
                el.style.backgroundImage = `url(./images/icons/40px/reptilesIcon.png)`
            } else if (feature.properties.categorie === "Poissons"){
                el.style.backgroundImage = `url(./images/icons/40px/poissonsIcon.png)`
            }

            const marker = new mapboxgl.Marker(el);

            marker.setLngLat(feature.geometry.coordinates)

            if(feature.properties.wikisource === ''){
                // make a marker for each feature and add to the map
                marker.setPopup(
                    new mapboxgl.Popup({ offset: 25 }) // add popups
                    .setHTML(
                        `<h3 class="titleOfPopup">${feature.properties.title}</h3><p class="descriptionOfPopup">${feature.properties.description}</p>
                        <a href=${feature.properties.link}>${feature.properties.link}</a><img src="data:image/jpeg;base64,${feature.properties.file_data}" width="200" height="200">`
                    )
                )
                marker.addTo(map);
            } else {
                // make a marker for each feature and add to the map
                marker.setPopup(
                    new mapboxgl.Popup({ offset: 25 }) // add popups
                    .setHTML(
                        `<h3 class="titleOfPopup">${feature.properties.title}</h3><p class="descriptionOfPopup">${feature.properties.description}</p>
                        <a href=${feature.properties.link}>${feature.properties.link}</a><img src="${feature.properties.wikisource}" width="200" height="200">`
                    )
                )
                marker.addTo(map);
            }

            const filterGroup = document.getElementById('filter-group');
            const symbol = feature.properties.categorie
            const layerID = `poi-${feature.properties.categorie}`;

            if (!map.getLayer(layerID)) {
                map.addLayer({
                    'id': layerID,
                    'type': 'symbol',
                    'source': 'places',
                    'filter': ['==', 'categorie', symbol]
                });

                const input = document.createElement('input');
                input.type = 'checkbox';
                input.id = feature.properties.categorie;
                input.checked = true;
                filterGroup.appendChild(input);
                
                const label = document.createElement('label');
                label.setAttribute('for', feature.properties.categorie);
                label.textContent = feature.properties.categorie;
                filterGroup.appendChild(label);
            }
        }
    });
})


mapboxgl.accessToken = 'your_token';
const map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/light-v10', // style URL
    center: [2.0,46.603354], // starting position [lng, lat]
    zoom: 4 // starting zoom
});

let reptilesIcons = document.getElementsByClassName("Reptiles");
let poissonsIcons = document.getElementsByClassName("Poissons");
let insectesIcons = document.getElementsByClassName("Insectes");
let mammiferesIcons = document.getElementsByClassName("Mammifères");
let oiseauxIcons = document.getElementsByClassName("Oiseaux");

map.on('idle', e => {
    let reptilesInput = document.getElementById('Reptiles')
    let poissonsInput = document.getElementById("Poissons");
    let insectesInput = document.getElementById("Insectes");
    let mammiferesInput = document.getElementById("Mammifères");
    let oiseauxInput = document.getElementById("Oiseaux");

    reptilesInput.addEventListener('change', e => {
        if(reptilesInput.checked){
            for (let i=0; i<reptilesIcons.length;i++){
                reptilesIcons[i].style.display='initial';
            }
        } else {
            for (let i=0; i<reptilesIcons.length;i++){
                reptilesIcons[i].style.display='none';
            }
        }
    });

    poissonsInput.addEventListener('change', e => {
        if(poissonsInput.checked){
            for (let i=0; i<poissonsIcons.length;i++){
                poissonsIcons[i].style.display='initial';
            }
        } else {
            for (let i=0; i<poissonsIcons.length;i++){
                poissonsIcons[i].style.display='none';
            }
        }
    });

    insectesInput.addEventListener('change', e => {
        if(insectesInput.checked){
            for (let i=0; i<insectesIcons.length;i++){
                insectesIcons[i].style.display='initial';
            }
        } else {
            for (let i=0; i<insectesIcons.length;i++){
                insectesIcons[i].style.display='none';
            }
        }
    });

    mammiferesInput.addEventListener('change', e => {
        if(mammiferesInput.checked){
            for (let i=0; i<mammiferesIcons.length;i++){
                mammiferesIcons[i].style.display='initial';
            }
        } else {
            for (let i=0; i<mammiferesIcons.length;i++){
                mammiferesIcons[i].style.display='none';
            }
        }
    });

    oiseauxInput.addEventListener('change', e => {
        if(oiseauxInput.checked){
            for (let i=0; i<oiseauxIcons.length;i++){
                oiseauxIcons[i].style.display='initial';
            }
        } else {
            for (let i=0; i<oiseauxIcons.length;i++){
                oiseauxIcons[i].style.display='none';
            }
        }
    });
})





