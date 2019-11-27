delete from cart;
delete from product_version;
delete from usr;
delete from product;
delete from category;
delete from category_group;

insert into category_group(id, name) values
(1, 'Столы');

insert into category(id, name, group_id) values
(1, 'Письменные столы', 1);

insert into product(id, general_inf, name, height, width, depth, price, promotion_price, category_id) values
(1, 'Стол', 'Пакс', 80, 120, 50, 1000, 0, 1);

insert into usr(id, username, password, fullname) values
(1, 'alex', '123', 'alex');

insert into product_version(id, article, product_id, count) values
(1, '111.111.11', 1, 10);

insert into cart(product_version_id, user_id, count) values
(1, 1, 1);