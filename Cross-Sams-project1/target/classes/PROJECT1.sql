CREATE TABLE EMPLOYEE_TABLE(
EMAIL VARCHAR(30),
EMPLOYEE_PASSWORD VARCHAR(12),
REIMBURSEMENT_REQUEST VARCHAR(12) PRIMARY KEY);

CREATE TABLE MANAGER_TABLE(
EMAIL VARCHAR(30),
EMPLOYEE_PASSWORD VARCHAR(12),
REIMBURSEMENT_REQUEST VARCHAR(12));



ALTER TABLE EMPLOYEE_TABLE ADD CONSTRAINT
MANAGER_TABLE FOREIGN KEY (REIMBURSEMENT_REQUEST) REFERENCES EMPLOYEE_TABLE;