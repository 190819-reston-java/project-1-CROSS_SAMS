--CREATE DATABASE tDatabase;
--DROP DATABASE tDatabase;

DROP TABLE tDatabase;
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
	Date TEXT NOT NULL
);

--CREATE TABLE employees (
--	id serial NOT NULL UNIQUE,
--	name TEXT NOT NULL UNIQUE,
--	email TEXT NOT NULL UNIQUE,
--	address TEXT NOT NULL UNIQUE,
--	PASSWORD TEXT NOT NULL,
--	phone_number TEXT NOT NULL UNIQUE,
--	reason TEXT NOT NULL,
--	amount NUMERIC NOT NULL,
--	rdate date NOT NULL
--);

--INSERT INTO tdatabase (Reason, Amount, date) VALUES ('idk', '500', '8-30-2019');
--INSERT INTO tdatabase (Reason, Amount, date) VALUES ('test', '9000', '6-30-2019');
--INSERT INTO employees (name, email, address, PASSWORD, phone_number, reason, amount, rdate) VALUES ('test', 'me@email.cim', '1057 new market st', 'pas', '757-658-3718', 'idk', '500', '8_30_2019');

SELECT *
FROM tdatabase;

--SELECT *
--FROM employees;

