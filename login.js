'use strict';

console.log("login test");

let sessionInfo = document.getElementById("loginForm");

sessionInfo.addEventListener("submit", ()=>{
    // event.preventDefault();

   const user = document.getElementsByName("login_email");
   console.log(user);
});

// let login = document.querySelector(".user&Pass");

// login.inner