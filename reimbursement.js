'use strict';

console.log("reimburse");

const baseUrl = "/localproject1/p1Api";
// const baseUrl = "http://localhost:8080/localproject1/reimbursements"
// const oUrl = `${baseUrl}/tdatabase`;
const oUrl = `${baseUrl}/reimbursements`;

let newReimbursement = document.getElementById("reimbursementForm");
let pendingReimbursements = document.getElementById("pendingReimbursements");
let getPendingReimbursements = document.getElementById("get-pending-reimbursements");
let updateReimbursement = document.getElementById("update-reimbursements")

newReimbursement.addEventListener("submit", (event) => {
  event.preventDefault();

  console.log(reimbursementFromForm(newReimbursement));

  fetch(oUrl,
    { method: "POST", body: JSON.stringify(reimbursementFromForm(newReimbursement)) }
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
  // let reason = reimbursementForm.rType.value;
  // let amount = reimbursementForm.rAmount.value;
  // let date = reimbursementForm.rDate.value;

  // console.log(`user selected ${reason}, ${amount}, ${date}`);
});

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


// getPendingReimbursements(reason, amount, date, (jsonString) => {
//   let pendingReimbursements = JSON.parse(jsonString);
//   console.log(pendingObjects);



//   pendingReimbursements.innerHTML = "";

//   for (let k in pendingOReimbursements) {
//     let resultItem = document.createElement("li");
//     resultItem.innerText = `${k} : ${pendingObjects[k]}`;
//     pendingReimbursements.appendChild(resultItem);
//   }
//   // });


//   function getPendingReimbursements(reason, amount, date, onSuccess) {
//     let xhr = new XMLHttpRequest();

//     xhr.addEventListener("readystatechange", () => {

//       if (xhr.readyState === 4) {
//         let response = xhr.response;
//         console.log(`Response received: ${response}`);


//         if (xhr.status >= 200 && xhr.status < 300) {
//           onSuccess(response);
//         } else {
//           console.error(`Failure with status ${xhr.status}`);
//         }
//       }
//     });

//     // prepares the request for sending
//     xhr.open("POST", `${baseUrl}/${reason}/${amount}/${date}`);

//     // actually sends the request
//     xhr.send();

//   }


let reimbursementFromForm = (form) => {
  let nReimbursement = {};
  nReimbursement.reason = form.rType.value || "some reason";
  nReimbursement.amount = form.rAmount.value || "1000";
  nReimbursement.date = form.rDate.value || "1_1_2019";
  return nReimbursement;
}

let newDisplay = (reimbursement) => {
  pendingReimbursements.innerHTML = "";
};

let createLi = (reimbursement) => {

  let li = document.createElement("li");
  li.innerText = `Reason: ${reimbursement.reason} Amount: ${reimbursement.amount} Date: ${reimbursement.date}`;
  li.addEventListener("click", () => {
    updateReimbursement.reason.value = reimbusement.reason;
    updateReimbursement.amount.value = reimbusement.amount;
    updateReimbursement.date.value = reimbusement.date;
    newDisplay();
  });
  pendingReimbursements.append(li);
}