DROP DATABASE IF EXISTS internet_bank_db;
CREATE DATABASE internet_bank_db DEFAULT CHARACTER SET 'utf8';
USE internet_bank_db;

create table clients
(
	id int unsigned not null auto_increment,
    surname varchar(64) not null,
    name varchar(64) not null,
    patronymic varchar(64) not null,
    address varchar(128) not null,
    primary key (id)
)engine=InnoDB;

create table bills
(
	id int unsigned not null auto_increment,
    id_client int unsigned not null,
    sum float default 0,
    primary key (id),
    foreign key (id_client) references clients(id)
)engine=InnoDB;

create table transactions
(
	id int unsigned not null auto_increment,
    id_bill_sender int unsigned not null,
    id_bill_recipient int unsigned not null,
    amount float unsigned not null,
    primary key (id),
    foreign key (id_bill_sender) references bills(id),
    foreign key (id_bill_recipient) references bills(id)
)engine=InnoDB;