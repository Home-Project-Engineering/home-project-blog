
create sequence user_seq start with 1 increment by 50;

create table users(
    id         bigint       not null,
    email      varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    name       varchar(255) not null,
    password   varchar(255),
    role       varchar(255),
    primary key (id)
);
