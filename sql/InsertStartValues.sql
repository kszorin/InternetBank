USE internet_bank_db;
set names 'utf8';
insert into clients (surname, name, patronymic, address)
values ('Степанов', 'Иван', 'Сергеевич', 'г.Новосибирск, ул.Инженерная, 22 - 40');
insert into clients (surname, name, patronymic, address)
values ('Филиппова', 'Анастасия', 'Карловна', 'г.Новосибирск, ул.Красный проспект, 5 - 20');
insert into clients (surname, name, patronymic, address)
values ('Звыкова', 'Лариса', 'Геннадьевна', 'г.Барнаул, ул.Ленина, 2 - 88');
insert into bills (idClient, sum) values (1, 1000.30);
insert into bills (idClient, sum) values (1, 500.50);
insert into bills (idClient, sum) values (2, 300.12);