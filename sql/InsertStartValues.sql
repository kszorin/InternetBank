USE internet_bank_db;
set names 'utf8';
insert into clients (surname, name, patronymic, address)
values ('Степанов', 'Иван', 'Сергеевич', 'г.Новосибирск, ул.Инженерная, 22 - 40');
insert into clients (surname, name, patronymic, address)
values ('Филиппова', 'Анастасия', 'Карловна', 'г.Новосибирск, ул.Красный проспект, 5 - 20');
insert into clients (surname, name, patronymic, address)
values ('Звыкова', 'Лариса', 'Геннадьевна', 'г.Барнаул, ул.Ленина, 2 - 88');
insert into bills (id_client, sum) values (1, 1000.30);
insert into bills (id_client, sum) values (1, 500.54);
insert into bills (id_client, sum) values (2, 300.12);
insert into bills (id_client) values (3);
insert into transactions (id_bill_sender, id_bill_recipient, amount) values (1, 2, 300);
insert into transactions (id_bill_sender, id_bill_recipient, amount) values (2, 3, 500);
insert into transactions (id_bill_sender, id_bill_recipient, amount) values (3, 1, 100);