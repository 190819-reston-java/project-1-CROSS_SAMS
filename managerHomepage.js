'use strict';

console.log("manager homepage test");

const baseUrl = "/localproject1/p1Api";
const oUrl = `${baseUrl}/employees`;

let employeeList = document.getElementById("employee-list");
let getEmployees = document.getElementById("get-employees");
let employeeForm = document.getElementById("employee-form");

getEmployees.addEventListener("click", (event) => {
    fetch(oUrl, { method: "GET" })
      .then((response) => {
        return response.json();
      })
      .then((employeesJson) => {
        newDisplay();
        for (let employee in employeesJson) {
          console.log(employeesJson[employee]);
          createLi(employeesJson[employee]);
        }
      })
      .catch(console.log)
  });

  employeeForm.addEventListener("submit", (event) => {
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

  let employeeFromForm = (form) => {
    let employee = {};
    employee.reason = form.rType.value || "some reason";
    employee.amount = form.rAmount.value || "1000";
    employee.date = form.rDate.value || "1_1_2019";
    employee.email = form.rEmail.value || "idk";
    return employee;
  }
  
  let newDisplay = (employee) => {
    employeeList.innerHTML = "";
  };
  
  let createLi = (employee) => {
  
    let li = document.createElement("li");
    li.innerText = `Name: ${employee.name} | Email: ${employee.email} | Address: ${employee.address} | Password: ${employee.password} | Phone: ${employee.phone}`;
    
    employeeList.append(li);
  }