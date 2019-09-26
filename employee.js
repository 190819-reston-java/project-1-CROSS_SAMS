'use strict';

console.log("test");

let button = document.querySelector(".btn");

button.addEventListener("click", (event)=> {
    
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