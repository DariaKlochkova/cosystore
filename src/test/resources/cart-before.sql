delete from cart;
delete from product_version;
delete from user_role;
delete from usr;
delete from product;
delete from category;
delete from category_group;

insert into category_group(id, name) values
(1, 'Столы');

insert into category(id, name, group_id) values
(1, 'Письменные столы', 1),
(2, 'Обеденные столы', 1);

insert into product(id, general_inf, name, height, width, depth, price, promotion_price, category_id) values
(1, 'Стол', 'Пакс', 80, 120, 50, 1000, 0, 1),
(2, 'Обеденный стол', 'Клубу', 120, 120, 90, 4000, 0, 1);

insert into usr(id, username, password, firstname, surname) values
(1, 'ivan', '123', 'Иван', 'Иванов');

insert into user_role(user_id, roles) values
(1, 'USER');

insert into product_version(id, article, product_id, count) values
(1, '111.111.11', 1, 10),
(2, '111.111.22', 2, 10);

insert into cart(product_version_id, user_id, count) values
(1, 1, 1);