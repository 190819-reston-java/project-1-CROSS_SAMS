'use strict';

console.log("reimburse");

const baseUrl = "/localproject1";
// const baseUrl = "http://localhost:8080/localproject1/reimbursements"
const oUrl = `${baseUrl}/tdatabase`;

let newReimbursement = document.getElementById("reimbursementForm");
let pendingReimbursements = document.getElementById("pendingReimbursements");

newReimbursement.addEventListener("submit", (event) => {
  event.preventDefault();

  console.log(reimbursementFromForm(newReimbursement));

  fetch(baseUrl,
    { method: "POST", body: JSON.stringify(reimbursementFromForm(reimbursementForm)) }
  )
    .then((response) => {
      console.log(response);
      if (response.status >= 200 && response.status < 300) {
        alert("Reimbursement created");
      } else {
        alert("Failed to create reimbursement");
      }
    })
    .catch(console.error);
});

let reason = reimbursementForm.rType.value;
let amount = reimbursementForm.rAmount.value;
let date = reimbursementForm.rDate.value;

console.log(`user selected ${reason}, ${amount}, ${date}`);

getPendingObjects(reason, amount, date, (jsonString) => {
  let pendingObjects = JSON.parse(jsonString);
  console.log(pendingObjects);



  pendingReimbursements.innerHTML = "";

  for (let k in pendingObjects) {
    let resultItem = document.createElement("li");
    resultItem.innerText = `${k} : ${pendingObjects[k]}`;
    pendingReimbursements.appendChild(resultItem);
  }
});


function getPendingObjects(reason, amount, date, onSuccess) {
  let xhr = new XMLHttpRequest();

  xhr.addEventListener("readystatechange", () => {

    if (xhr.readyState === 4) {
      let response = xhr.response;
      console.log(`Response received: ${response}`);


      if (xhr.status >= 200 && xhr.status < 300) {
        onSuccess(response);
      } else {
        console.error(`Failure with status ${xhr.status}`);
      }
    }
  });

  //       // prepares the request for sending
  //       xhr.open("POST", `${baseUrl}/${reason}/${amount}/${date}`);

  //       // actually sends the request
  //       xhr.send();

}


let reimbursementFromForm = (form) => {
  let nReimbursement = {};
  nReimbursement.reason = form.rType.value || "some reason";
  nReimbursement.amount = form.rAmount.value || "1000";
  nReimbursement.date = form.rDate.value || "1_1_2019";
  return nReimbursement;
}