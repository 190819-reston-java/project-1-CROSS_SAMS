--CREATE DATABASE tDatabase;
--DROP DATABASE tDatabase;

DROP TABLE employee_table;
DROP TABLE tDatabase;
DROP TABLE manager_table;
--DROP TABLE employees;

--CREATE TABLE rDatabase (
--	id serial NOT NULL UNIQUE,
--	name TEXT NOT NULL UNIQUE,
--	reason TEXT NOT NULL,
--	amount NUMERIC NOT NULL,
--	rdate date NOT null
--);

CREATE TABLE tdatabase (
	id serial NOT NULL UNIQUE,
	Reason TEXT NOT NULL,
	Amount NUMERIC NOT NULL,
	Date TEXT NOT NULL,
	employee TEXT,
	status TEXT,
	manager TEXT
);

CREATE TABLE EMPLOYEE_TABLE(
	id serial NOT NULL UNIQUE,
	NAME VARCHAR(12),
	EMAIL VARCHAR(30),
	ADDRESS VARCHAR(12),
	password VARCHAR(12),
	phone VARCHAR(13)
);

CREATE TABLE MANAGER_TABLE(
	id serial NOT NULL UNIQUE,
	NAME VARCHAR(12),
	EMAIL VARCHAR(30),
	ADDRESS VARCHAR(12),
	MANAGER_PASSWORD VARCHAR(12),
	PHONE_NUMBER VARCHAR(13)
);

INSERT INTO tdatabase (Reason, Amount, date) VALUES ('idk', '500', '8-30-2019');
INSERT INTO EMPLOYEE_TABLE (name, email, address, password, phone) VALUES ('name', 'anemail@email.com', '10 m st.', 'pass', '757-303-9584');
INSERT INTO EMPLOYEE_TABLE (name, email, address, password, phone) VALUES ('name3', 'anotheremail@email.com', '30 m st.', 'passwords', '757-303-9584');
INSERT INTO MANAGER_TABLE (name, email, address, manager_password, phone_number) VALUES ('name2', 'aemail@email.com', '20 m st.', 'password', '757-303-1853');


SELECT *
FROM tdatabase;

SELECT *
FROM EMPLOYeE_TABLE;

SELECT *
FROM MANAGER_TABLE;
