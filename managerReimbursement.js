'use strict';

console.log("reimburse");

const baseUrl = "/localproject1/p1Api";

const oUrl = `${baseUrl}/reimbursements`;
const o2Url = `${baseUrl}/resolvedReimbursements`;
const o3Url = `${baseUrl}/managerReimbursements`;

let newReimbursement = document.getElementById("reimbursementForm");
let pendingReimbursements = document.getElementById("pendingReimbursements");
let getPendingReimbursements = document.getElementById("get-pending-reimbursements");
let updateReimbursement = document.getElementById("update-reimbursements");
let getResolvedReimbursements = document.getElementById("get-resolved-reimbursements");
let resolvedReimbursements = document.getElementById("resolved-reimbursements");


newReimbursement.addEventListener("submit", (event) => {
  event.preventDefault();

  console.log(reimbursementFromForm(newReimbursement));

  fetch(o3Url,
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

});

getPendingReimbursements.addEventListener("click", (event) => {
  fetch(o3Url, { method: "GET" })
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

getResolvedReimbursements.addEventListener("click", (event) => {
  fetch(o2Url, { method: "GET" })
    .then((response) => {
      return response.json();
    })
    .then((reimbursementsJson) => {
      // newDisplay();
      newDisplay2();
      for (let reimbursement in reimbursementsJson) {
        console.log(reimbursementsJson[reimbursement]);
        createLi2(reimbursementsJson[reimbursement]);
      }
    })
    .catch(console.log)
})

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

let newDisplay2 = (reimbursement) => {
  resolvedReimbursements.innerHTML = "";
};

let createLi = (reimbursement) => {

  let li = document.createElement("li");
  li.innerText = `Reason: ${reimbursement.reason} Amount: $${reimbursement.amount} Date: ${reimbursement.date}`;

  pendingReimbursements.append(li);
}

let createLi2 = (reimbursement) => {

  let li = document.createElement("li");
  li.innerText = `Reason: ${reimbursement.reason} Amount: $${reimbursement.amount} Date: ${reimbursement.date}`;

  resolvedReimbursements.append(li);
}