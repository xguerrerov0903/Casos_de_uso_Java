create database store;

use store;

create table products (
id_product INT PRIMARY KEY AUTO_INCREMENT,
name varchar(100) unique not null,
price DECIMAL(10,2),
stock int,
category enum ("Food", "Appliance")

);

-- DROP DATABASE store;