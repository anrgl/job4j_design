create database course;

create table students(
    id serial primary key,
    name varchar(255)
);

create table courses(
    id serial primary key,
    name varchar(255)
);

create table students_courses(
    id serial primary key,
    student_id int references students(id),
    course_id int references courses(id)
);

insert into students(name) values ('Ivan');
insert into students(name) values ('Kiril');
insert into students(name) values ('Roman');

select * from students;

insert into courses(name) values ('Java SE');
insert into courses(name) values ('Spring');
insert into courses(name) values ('Hibernate');

select * from courses;

insert into students_courses(student_id, course_id) VALUES (1, 1);
insert into students_courses(student_id, course_id) VALUES (1, 2);
insert into students_courses(student_id, course_id) VALUES (1, 3);
insert into students_courses(student_id, course_id) VALUES (2, 1);
insert into students_courses(student_id, course_id) VALUES (2, 2);
insert into students_courses(student_id, course_id) VALUES (3, 1);