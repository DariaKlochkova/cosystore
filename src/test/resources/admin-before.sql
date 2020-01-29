delete from user_role;
delete from usr;
delete from value;
delete from product_version;
delete from product;
delete from property;
delete from category;
delete from category_group;

insert into category_group(id, name) values
(1, 'Столы'),
(2, 'Диваны');

insert into category(id, name, group_id) values
(1, 'Обеденные столы', 1),
(2, 'Журнальные столики', 1);

insert into property(id, name, category_id) values
(1, 'Складной', 1);

insert into product(id, general_inf, name, height, width, depth, price, promotion_price, category_id) values
(1, 'Обеденный стол', 'Клубу', 120, 120, 90, 4000, 0, 1);

insert into product_version(id, article, product_id, count) values
(1, '111.111.11', 1, 10);

insert into value(product_id, property_id, value) values
(1, 1, 'Да');

insert into usr(id, username, password, firstname, surname) values
(1, 'admin', '123', 'Иван', 'Иванов');

insert into user_role(user_id, roles) values
(1, 'ADMIN');