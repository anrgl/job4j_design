create database car_country;

create table country(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    model varchar(255),
    country_id int references country(id)
);

insert into country(name) values ('Russia');
insert into country(name) values ('Japan');
insert into country(name) values ('German');

insert into car(model, country_id) values ('VAZ', 1);
insert into car(model, country_id) values ('Kamaz', 1);
insert into car(model, country_id) values ('GAZ', 1);
insert into car(model, country_id) values ('Mazda', 2);
insert into car(model, country_id) values ('Toyota', 2);
insert into car(model, country_id) values ('BMW', 3);
insert into car(model) values ('Reno');

select model as Модель, name as Название from car join country c on c.id = car.country_id;