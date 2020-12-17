create table user
(
    id      serial primary key,
    name    varchar(255),
    role_id int references role (id)
);

create table role
(
    id   serial primary key,
    name varchar(255)
);

create table rules
(
    id   serial primary key,
    name varchar(255)
);

create table role_rules
(
    id       serial primary key,
    role_id  int references role (id),
    rules_id int references rules (id)
);

create table item
(
    id          serial primary key,
    name        varchar(255),
    user_id     int references user (id),
    category_id int references category (id),
    state_id    int references state (id)
);

create table comments
(
    id      serial primary key,
    body    text,
    item_id int references item (id)
);

create table attaches
(
    id      serial primary key,
    file    varchar(255),
    item_id int references item (id)
);

create table category
(
    id   serial primary key,
    name varchar(50)
);

create table state
(
    id   serial primary key,
    name varchar(100)
);