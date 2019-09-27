'use strict';

console.log("test");
const bUrl = "file:///C:/Users/the2n/Desktop/Revature/Code/Projects/project-1-CROSS_SAMS/"
const 

let button = document.querySelector(".btn");
let employeeGet = document.getElementById("name");

button.addEventListener("click", (event)=> {
    fetch("/")
})

function updateInfo() {
    var popup = document.getElementById("updatePopup");
    popup.classList.toggle("show");

}

function openForm() {
    document.getElementById("updatePopup");
  }

function closeForm() {
    document.getElementById("updatePopup").style.display = "none";
  }