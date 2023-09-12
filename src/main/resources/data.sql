create table users
(
    id       bigint auto_increment primary key,
    name     varchar(255) not null,
    age      int,
    email    varchar(255),
    password varchar(255) not null
);

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
    users_id bigint,
    roles_id bigint
);



INSERT INTO users ( name, age, email, password) VALUES ( 'user1', 123, 'asd@gmail.com', '$2a$12$Ag1kPDLRAyE0TnEsAyQQU.LTlCCouxF7FyEhGfE3FLmtzCsih/4Lu');

alter table user_role
    add foreign key (users_id) references users (id);
alter table user_role
    add foreign key (roles_id) references roles (id);

insert into user_role (users_id, roles_id) VALUES (1, 1);
insert into user_role (users_id, roles_id) VALUES (1, 2);