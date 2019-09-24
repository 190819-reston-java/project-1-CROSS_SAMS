'use strict';

console.log("manager reimbursement test");

let reimbursementForm = document.getElementById("reimbursementForm");
let pendingReimbursements = document.getElementById("pendingReimbursements");

reimbursementForm.addEventListener("submit", (event) => {
    event.preventDefault();

    let reason = reimbursementForm.rType.value;
    let amount = reimbursementForm.rAmount.value;
    let date = reimbursementForm.rDate.value;

    console.log(`user selected ${reason}, ${amount}, ${date}`);

    getPendingObjects(reason, amount, date, (jsonString) => {
        let pendingObjects = JSON.parse(jsonString);
        console.log(pendingObjects);

        pendingReimbursements.innerHTML = "";

        for(let k in pendingObjects) {
            let resultItem = document.createElement("li");
            resultItem.innerText = `${k} : ${pendingObjects[k]}`;
            pendingReimbursements.appendChild(resultItem);
        }
    });
});

function getPendingObjects(reason, amount, date, onSuccess) {
    let xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", () => {
        // Here we can do granualar things, but we'll just check for 4
        if(xhr.readyState === 4) {
          let response = xhr.response;
          console.log(`Response received: ${response}`);
    
          // Before we declare victory and call onSuccess, let's
          // check the status code:
          if(xhr.status >= 200 && xhr.status < 300) {
            onSuccess(response);
          } else {
            console.error(`Failure with status ${xhr.status}`);
          }
        }
      });
    
      // prepares the request for sending
    //   xhr.open("get", `database-1.crm8pn4irylh.us-east-1.rds.amazonaws.com/${reason}/${amount}/${date}`);
    
      // actually sends the request
    //   xhr.send();
}