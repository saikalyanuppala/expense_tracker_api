CREATE DATABASE expensetracker;

USE expensetracker;

CREATE TABLE tbl_expenses
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    expense_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    expense_amount DOUBLE(5, 2) NOT NULL,
    category VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);

INSERT INTO tbl_expenses(expense_name, description, expense_amount, category, date)
VALUES("Water bill", "water bill", 600.00, "Bills", "2021-10-14");

INSERT INTO tbl_expenses(expense_name, description, expense_amount, category, date)
VALUES("Electricity bill", "electricity bill", 900.00, "Bills", "2021-10-13");


INSERT INTO department VALUES(1,'Accounting','Hyderabad');
INSERT INTO department VALUES(2,'Operations','Delhi');
INSERT INTO department VALUES(3,'Admin','Chennai');
INSERT INTO department VALUES(4,'Finance','Hyderabad');

INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (1,'sai',35,12345.67,1);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (2,'kalyan',45,112233.67,1);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (3,'sandra',55,887766.67,2);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (4,'durga',60,369786.67,3);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (5,'laxmi',62,7654321.67,2);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (6,'shiva',75,78965668.67,3);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (7,'chandi',25,5656577.67,4);
INSERT INTO employee(id,NAME,age,salary,dept_id) VALUES (8,'narasimha',30,545456.67,4);