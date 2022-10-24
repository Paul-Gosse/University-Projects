"use strict";

let sidebar = document.getElementById("side-bar");
let sidebarToggle = document.getElementById("side-bar-toggle");
let mobile = document.getElementById("mobile-menu-bar");
let dropdown = document.getElementById("dropdown-btn");
let trashIcon = document.getElementsByClassName("trashIcon");

dropdown.addEventListener("click", toggleDropdownMenu);

function toggleDropdownMenu(){
	window.getComputedStyle(mobile).getPropertyValue("display") === "none" ? mobile.style.display = "block" : mobile.style.display = "none";
}

window.addEventListener("resize", e => {
	if (window.innerWidth > 768 && mobile.style.display !== "none") {
		mobile.style.display = "none";
	}
})
