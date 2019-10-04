'use strict';

console.log("employee Reimbursement request test");

const baseUrl = "/localproject1/p1Api";
const oUrl = `${baseUrl}/reimbursements`;
const o2Url = `${baseUrl}/resolvedReimbursements`;
const o3Url = `${baseUrl}/allReimbursements`


let eReimbursements = document.getElementById("eReimbursements");
let eReimbursementsResults = document.getElementById("eReimbursementsResults");
let pendingReimbursements = document.getElementById("pendingReimbursements");
let getPendingReimbursements = document.getElementById("get-pending-reimbursements");
let updateReimbursement = document.getElementById("update-reimbursements");
let update = document.getElementById("rUpdate");
let ReimbursementsResults = document.getElementById("reimbursementsResults");
let getResolvedReimbursements = document.getElementById("get-resolved-reimbursements");
let resolvedReimbursements = document.getElementById("resolved-reimbursements");





eReimbursements.addEventListener("submit", (event) => {
  event.preventDefault();

  const ePicker = eReimbursements.ePicker.value;
  console.log(`user selected: ${ePicker}`);


  if(ePicker=='All') {
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

  } else if (ePicker=='Jim') {
    console.log("employee1 = Jim");
    fetch(oUrl, { method: "GET" })
    .then((response) => {
      return response.json();
    })
    .then((reimbursementsJson) => {
      newDisplay();
      for (let reimbursement in reimbursementsJson) {
        console.log(reimbursementsJson[reimbursement]);
        if (reimbursement.employee != 'aemail@email.com'){
        createLi(reimbursementsJson[reimbursement]);
      }
    }
    })
  } else {
    console.log("No Employee Selected. Something went wrong :(")
  }
  
});

updateReimbursement.addEventListener("submit", (event) => {
  event.preventDefault();

  fetch(oUrl,
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
})

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
  let reimbursement = {};
  reimbursement.reason = form.reason.value || "some reason";
  reimbursement.amount = form.amount.value || 0;
  reimbursement.date = form.date.value || "1_1_2019";
  reimbursement.status = form.status.value;
  reimbursement.id = form.id.value || 0;
  return reimbursement;
}

let newDisplay = () => {
  eReimbursementsResults.innerHTML = "";
}

let newDisplay2 = (reimbursement) => {
  resolvedReimbursements.innerHTML = "";
};

let createLi = (reimbursement) => {

  let li = document.createElement("li");
  li.innerText = `ID: ${reimbursement.id} Reason: ${reimbursement.reason} Amount: $${reimbursement.amount} Date: ${reimbursement.date} Status: ${reimbursement.status}`;
  li.addEventListener("click", () => {
    updateReimbursement.id.value = reimbursement.id;
    updateReimbursement.reason.value = reimbursement.reason;
    updateReimbursement.amount.value = reimbursement.amount;
    updateReimbursement.date.value = reimbursement.date;
    updateReimbursement.status.value = reimbursement.status;
    updateReimbursement.hidden = false;
    // newDisplay();
  });
  eReimbursementsResults.append(li);
}

let createLi2 = (reimbursement) => {

  let li = document.createElement("li");
  li.innerText = `ID: ${reimbursement.id} Reason: ${reimbursement.reason} Amount: $${reimbursement.amount} Date: ${reimbursement.date} Status: ${reimbursement.status}  Manager: ${reimbursement.manager}`;
  li.addEventListener("click", () => {
    updateReimbursement.id.value = reimbursement.id;
    updateReimbursement.reason.value = reimbursement.reason;
    updateReimbursement.amount.value = reimbursement.amount;
    updateReimbursement.date.value = reimbursement.date;
    updateReimbursement.status.value = reimbursement.status;
    updateReimbursement.hidden = false;
    newDisplay2();
  });
  resolvedReimbursements.append(li);
}