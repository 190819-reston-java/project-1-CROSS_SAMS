'use strict';

console.log("employee Reimbursement request test");

let eReimbursements = document.getElementById("eReimbursements");
let eReimbursementsResults = document.getElementById("eReimbursementsResults");

eReimbursements.addEventListener("submit", (event) => {
    event.preventDefault();

    let selectedEmployee = eReimbursements.ePicker.value;
    console.log(`user selected: ${selectedEmployee}`);

    getEmployeeFiles(selectedEmployee, (jsonString) => {
        let employeeFiles = JSON.parse(jsonString);
        console.log(employeeFiles);

        eReimbursementsResults.innerHTML = "";

    //for-in to loop through keys on object
    for(let k in employeeFiles) {
      let resultItem = document.createElement("li");
      resultItem.innerText = `${k} : ${employeeFiles[k]}`;
      eReimbursementsResults.appendChild(resultItem);
    }
    })
})

function getEmployeeFiles(selectedEmployee, onSuccess) {
    let xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", () => {
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

//   xhr.open("get", `database-1.crm8pn4irylh.us-east-1.rds.amazonaws.com/${selectedEmployee}`);

//   xhr.send();
}