create database car;

create table car(
    id serial primary key,
    name varchar(255),
    max_speed int,
    description text
);

insert into car(name, max_speed, description) values ('VAZ 2110', 180, 'Classic russian car');
update car set max_speed = 190;
delete from car;
select * from car;