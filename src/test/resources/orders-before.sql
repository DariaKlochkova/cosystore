delete from user_role;
delete from usr;
delete from orders;

insert into orders(id, cost, date, status, address, phone_number, recipient, email) values
(1, 5000, '2019-12-07', 0, 'Москва', '88005553535', 'Григорий', 'some@mail.ru');

insert into usr(id, username, password, firstname, surname) values
(1, 'delivery', '123', 'Иван', 'Иванов');

insert into user_role(user_id, roles) values
(1, 'DELIVERY');