create database shop;

create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('ЧАЙ');
insert into type(name) values ('ШОКОЛАД');
insert into type(name) values ('ГАЗИРОВКА');
insert into type(name) values ('КНИГА');

insert into product(name, type_id, expired_date, price) values ('Сыр Честер', 1, '2021-01-25', 700);
insert into product(name, type_id, expired_date, price) values ('Сыр Манчестер', 1, '2021-02-25', 800);
insert into product(name, type_id, expired_date, price) values ('Сыр Итальянский', 1, '2021-04-25', 990);
insert into product(name, type_id, expired_date, price) values ('Молоко шоколадное', 2, '2020-12-25', 90);
insert into product(name, type_id, expired_date, price) values ('Молоко мороженное, сливочное', 2, '2022-10-16', 190);
insert into product(name, type_id, expired_date, price) values ('Java за 21 день', 6, '2022-10-16', 790);

-- Написать запрос получение всех продуктов с типом "СЫР"
select * from product
    join type t on t.id = product.type_id
where t.name = 'СЫР';
-- Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product
where name like '%мороженное%';
-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product
where expired_date = (current_date + interval '1 month');
-- Написать запрос, который выводит самый дорогой продукт.
select * from product
where price in (
    select max(price) from product);
-- Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*), t.name from product as p
    join type t on t.id = p.type_id
group by t.name;
-- Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product as p
    join type t on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО';
-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(p) from product as p
    join type t on p.type_id = t.id
group by t.name
having count(p) < 10;
-- Вывести все продукты и их тип.
select p.name, t.name from product as p
    join type t on t.id = p.type_id;