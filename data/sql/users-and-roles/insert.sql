insert into role(name) values ('Seller');
insert into "user"(name, role_id) values ('Ivan', 1);
insert into rules(name) values ('Administrate');
insert into category(name) values ('Cars');
insert into state(name) values ('Done');
insert into item(name, user_id, category_id, state_id) values ('Car', 2, 1, 1);
insert into comments(body, item_id) values ('Good car', 3);
insert into attaches(file, item_id) values ('./good_car/car.jpg', 3);