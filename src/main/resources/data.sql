create table users
(
    id       bigint auto_increment primary key,
    name     varchar(255) not null,
    age      int,
    email    varchar(255),
    password varchar(255) not null
);

ALTER TABLE users
    ADD UNIQUE (name);

create table roles
(
    id   bigint primary key auto_increment,
    name varchar(255)
);

ALTER TABLE roles
    ADD UNIQUE (name);
insert into roles(name)
values ('ROLE_ADMIN');
insert into roles(name)
values ('ROLE_USER');

create table user_role
(
    user_id bigint,
    role_id bigint
);



INSERT INTO users ( name, age, email, password) VALUES ( 'user1', 123, 'asd@gmail.com', '$2a$12$Ag1kPDLRAyE0TnEsAyQQU.LTlCCouxF7FyEhGfE3FLmtzCsih/4Lu');
INSERT INTO users ( name, age, email, password) VALUES ( 'user2', 123, 'asd@gmail.com', '$2a$12$Ag1kPDLRAyE0TnEsAyQQU.LTlCCouxF7FyEhGfE3FLmtzCsih/4Lu');

alter table user_role
    add foreign key (user_id) references users (id);
alter table user_role
    add foreign key (role_id) references roles (id);

insert into user_role (user_id, role_id) VALUES (1, 1);
insert into user_role (user_id, role_id) VALUES (2, 2);
