-- 1. Создать таблицы и заполнить их начальными данными
create database company2;

create table department (
    id serial primary key,
    name varchar(255)
);

create table employee (
    id serial primary key,
    name varchar(255),
    department_id int references department(id)
);

insert into department (name) values ('IT');
insert into department (name) values ('HR');
insert into department (name) values ('Sales');
insert into department (name) values ('Bank');

insert into employee (name, department_id) values ('Ivan', 1);
insert into employee (name, department_id) values ('Masha', 2);
insert into employee (name, department_id) values ('John', 3);
insert into employee (name, department_id) values ('Linus', null);
insert into employee (name, department_id) values ('Ilona', null);
insert into employee (name, department_id) values ('Alex', null);
insert into employee (name, department_id) values ('Oleg', 4);
insert into employee (name, department_id) values ('Petr', 1);

-- 2. Выполнить запросы с left, right, full, cross соединениями
select * from employee e left join department d on e.department_id = d.id;
select * from department d left join employee e on d.id = e.department_id;

select * from employee e right join department d on e.department_id = d.id;
select * from department d right join employee e on d.id = e.department_id;

select * from employee e full join department d on e.department_id = d.id;
select * from department d full join employee e on d.id = e.department_id;

select * from employee e cross join department d;

-- 3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select * from employee e left join department d on e.department_id = d.id where d is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from employee e left join department d on e.department_id = d.id;
select * from department d left join employee e on d.id = e.department_id;

select * from employee e right join department d on e.department_id = d.id;
select * from department d right join employee e on d.id = e.department_id;
-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары
create table teens (
    id serial primary key,
    name varchar(255),
    gender boolean
);

insert into teens (name, gender) values ('Ivan', true);
insert into teens (name, gender) values ('Oleg', true);
insert into teens (name, gender) values ('Ana', false);
insert into teens (name, gender) values ('Vera', false);

select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;