create database car_store;

create table body (
    id serial primary key,
    name varchar(50)
);

create table engine (
    id serial primary key,
    name varchar(50)
);

create table transmission (
    id serial primary key,
    name varchar(50)
);

create table car (
    id serial primary key,
    name varchar(100),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body (name) values ('Sedan');
insert into body (name) values ('Roadster');
insert into body (name) values ('Minivan');
insert into body (name) values ('SUV');
insert into body (name) values ('Pickup');
insert into body (name) values ('Sport');

insert into engine (name) values ('V8');
insert into engine (name) values ('V6');
insert into engine (name) values ('Classic');
insert into engine (name) values ('Chines engine');
insert into engine (name) values ('Unknown engine');

insert into transmission (name) values ('Manual 5');
insert into transmission (name) values ('Manual 6');
insert into transmission (name) values ('Robot');
insert into transmission (name) values ('Auto');

insert into car (name, body_id, engine_id, transmission_id) values ('Sport car', 6, 1, 3);
insert into car (name, body_id, engine_id, transmission_id) values ('Family car', 1, 3, 4);
insert into car (name, body_id, engine_id, transmission_id) values ('Farmer car', 5, 1, 2);

-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.name, b.name, e.name, t.name from car c
    left join body b on c.body_id = b.id
    left join engine e on c.engine_id = e.id
    left join transmission t on c.transmission_id = t.id;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select b.name from car c right join body b on b.id = c.body_id where c is null
union
select e.name from car c right join engine e on e.id = c.engine_id where c is null
union
select t.name from car c right join transmission t on t.id = c.transmission_id where c is null;

