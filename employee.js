'use strict';

console.log("test");

// let button = document.querySelector(".btn");
// let employeeGet = document.getElementById("name");

const baseUrl = "/localproject1/p1Api";
const oUrl = `${baseUrl}/employees`;

let employeeList = document.getElementById("employee-list");
let getEmployees = document.getElementById("get-employees");
let employeeForm = document.getElementById("employee-form");
let updateEmployee = document.getElementById("update-employee");


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

// employeeForm.addEventListener("submit", (event) => {
//   event.preventDefault();

//   fetch(baseUrl,
//     { method: "PUT", body: JSON.stringify(employeeFromForm(updateemployee)) }
//   )
//     .then((response) => {
//       console.log(response);
//       if (response.status >= 200 && response.status < 300) {
//         alert("Employy updated");
//       } else {
//         alert("Failed to update employee");
//       }
//       updateEmployee.hidden = true;
//     })
//     .catch(console.error);
// });

updateEmployee.addEventListener("submit", (event) => {
  event.preventDefault();

  fetch(oUrl,
    { method: "PUT", body: JSON.stringify(employeeFromForm(updateEmployee)) }
  )
    .then((response) => {
      console.log(response);
      if (response.status >= 200 && response.status < 300) {
        alert("Employee updated");
      } else {
        alert("Failed to update employee");
      }
      updateEmployee.hidden = true;
    })
    .catch(console.error);
})

let employeeFromForm = (form) => {
  let employee = {};
  employee.address = form.address.value || "address";
  employee.password = form.password.value || "pass";
  employee.phone = form.phone.value || "phone";
  employee.id = form.id.value;
  return employee;
}

let newDisplay = (employee) => {
  employeeList.innerHTML = "";
};

let createLi = (employee) => {

  let li = document.createElement("li");
  li.innerText = `Name: ${employee.name} | Email: ${employee.email} | Address: ${employee.address} | Password: ${employee.password} | Phone: ${employee.phone}`;
  li.addEventListener("click", () => {
    updateEmployee.id.value = employee.id;
    updateEmployee.address.value = employee.address;
    updateEmployee.password.value = employee.password;
    updateEmployee.phone.value = employee.phone;
    console.log(updateEmployee.address.value);
    console.log(employee.address);
    updateEmployee.hidden = false;
  });
  employeeList.append(li);
}