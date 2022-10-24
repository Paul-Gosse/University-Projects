"use strict";

let longitude = document.getElementById("Longitude");
let latitude = document.getElementById("Latitude");

let newLongitude = parseFloat(longitude.value); //Conversion en nombre
let newLatitude = parseFloat(latitude.value);

let changeLng = '';
let changeLat = '';

mapboxgl.accessToken = 'your_token';
const map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v11', // style URL
    center: [2.0,46.603354], // starting position [lng, lat]
    zoom: 2 // starting zoom
});

const marker = new mapboxgl.Marker({
        color: "#FF0000"
    });


if(newLongitude.value === '' && newLongitude.value === ''){
    map.setCenter([newLongitude,newLatitude]);
    marker.setLngLat([longitude.value, latitude.value]).addTo(map);
}

if(!Number.isNaN(newLongitude) && !Number.isNaN(newLatitude)){
    map.setCenter([longitude.value,latitude.value]);
    marker.setLngLat([longitude.value, latitude.value]).addTo(map);
}

longitude.addEventListener("change", e => {
    map.setCenter([longitude.value,latitude.value]);
    marker.setLngLat([longitude.value, latitude.value]).addTo(map);
})

latitude.addEventListener("change", e => {
    marker.setLngLat([longitude.value, latitude.value]).addTo(map);
})

map.on("click", e => {
    let coord = e.lngLat;
    longitude.value = coord.lng;
    latitude.value = coord.lat;
    marker.setLngLat(coord).addTo(map);
});