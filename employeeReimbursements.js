'use strict';

console.log("employee Reimbursement request test");

const baseUrl = "/localproject1/p1Api";
// const baseUrl = "http://localhost:8080/localproject1/reimbursements"
// const oUrl = `${baseUrl}/tdatabase`;
const oUrl = `${baseUrl}/reimbursements`;

let eReimbursements = document.getElementById("eReimbursements");
let eReimbursementsResults = document.getElementById("eReimbursementsResults");
let pendingReimbursements = document.getElementById("pendingReimbursements");
let getPendingReimbursements = document.getElementById("get-pending-reimbursements");
let updateReimbursement = document.getElementById("update-reimbursements");

eReimbursements.addEventListener("submit", (event) => {
  event.preventDefault();

  let selectedEmployee = eReimbursements.ePicker.value;
  console.log(`user selected: ${selectedEmployee}`);

  getPendingReimbursements.addEventListener("click", (event) => {
    fetch(oUrl, { method: "GET" })
      .then((response) => {
        return response.json();
      })
      .then((reimbursementsJson) => {
        newDisplay();
        for (let reimbursement in reimbursementsJson) {
          console.log(reimbursementsJson[reimbursement]);
          createLi(reimbursementsJson[reimbursement]);
        }
      })
      .catch(console.log)
  });
  

  
});

// getEmployeeFiles(selectedEmployee, (jsonString) => {
//   let employeeFiles = JSON.parse(jsonString);
//   console.log(employeeFiles);

//   eReimbursementsResults.innerHTML = "";

//   //for-in to loop through keys on object
//   for (let k in employeeFiles) {
//     let resultItem = document.createElement("li");
//     resultItem.innerText = `${k} : ${employeeFiles[k]}`;
//     eReimbursementsResults.appendChild(resultItem);
//   }
// })

// function getEmployeeFiles(selectedEmployee, onSuccess) {
//   let xhr = new XMLHttpRequest();

//   xhr.addEventListener("readystatechange", () => {
//     if (xhr.readyState === 4) {
//       let response = xhr.response;
//       console.log(`Response received: ${response}`);

//       // Before we declare victory and call onSuccess, let's
//       // check the status code:
//       if (xhr.status >= 200 && xhr.status < 300) {
//         onSuccess(response);
//       } else {
//         console.error(`Failure with status ${xhr.status}`);
//       }
//     }
//   });
// }

getPendingReimbursements.addEventListener("click", (event) => {
  fetch(oUrl, { method: "GET" })
    .then((response) => {
      return response.json();
    })
    .then((reimbursementsJson) => {
      newDisplay();
      for (let reimbursement in reimbursementsJson) {
        console.log(reimbursementsJson[reimbursement]);
        createLi(reimbursementsJson[reimbursement]);
      }
    })
    .catch(console.log)
});

updateReimbursement.addEventListener("submit", (event) => {
  event.preventDefault();

  fetch(baseUrl,
    { method: "PUT", body: JSON.stringify(reimbursementFromForm(updateReimbursement)) }
  )
    .then((response) => {
      console.log(response);
      if (response.status >= 200 && response.status < 300) {
        alert("Reimbursement updated");
      } else {
        alert("Failed to update reimbursement");
      }
      updateReimbursement.hidden = true;
    })
    .catch(console.error);
});