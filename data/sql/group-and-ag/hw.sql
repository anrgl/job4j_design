create database devices_and_people;

create table device(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references device(id),
    people_id int references people(id)
);

insert into device(name, price) values ('Nokia 3110', 39.9);
insert into device(name, price) values ('Samsung TV', 425);
insert into device(name, price) values ('Xbox Series X', 500);

insert into people(name) values ('Ivan');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 1);

insert into device(name, price) values ('Iphone 12', 799);
insert into device(name, price) values ('Macbook Pro', 1700);
insert into device(name, price) values ('Airpods', 100);

insert into people(name) values ('Anastasia');

insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (5, 2);
insert into devices_people(device_id, people_id) values (6, 2);

insert into device(name, price) values ('Samsung A51', 109.9);
insert into device(name, price) values ('Sony Walkman', 245);
insert into device(name, price) values ('PlayStation 5', 560);

insert into people(name) values ('Oleg');

insert into devices_people(device_id, people_id) values (7, 3);
insert into devices_people(device_id, people_id) values (8, 3);
insert into devices_people(device_id, people_id) values (9, 3);

-- 3. Используя агрегатные функции вывести среднюю цену устройств
select avg(price) from device;
-- 4. Используя группировку вывести для каждого человеку среднюю цену его устройств
select p.name, avg(d.price) from devices_people as dp
    join people p on dp.people_id = p.id
    join device d on dp.device_id = d.id
group by p.name;
-- 5. Дополнить запрос п.4. условием, что цена устройства должны быть больше 5000
select p.name, avg(d.price) from devices_people as dp
     join people p on dp.people_id = p.id
     join device d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;