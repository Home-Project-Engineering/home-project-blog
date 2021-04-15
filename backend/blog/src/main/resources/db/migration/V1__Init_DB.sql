create sequence comment_seq start with 1 increment by 50;
create sequence post_seq start with 1 increment by 50;
create sequence tag_seq start with 1 increment by 50;
create sequence user_seq start with 1 increment by 50;
create table comments(
    id         bigint not null,
    created_on DATE,
    text       varchar(255),
    updated_on DATE,
    author_id  bigint,
    post_id    bigint,
    primary key (id)
);

create table posts(
    id                 bigint not null,
    created_on         DATE,
    preview_attachment varchar(255),
    text               varchar(255),
    title              varchar(255),
    updated_on         DATE,
    author_id          bigint,
    primary key (id)
);
create table posts_tags(
    post_id bigint not null,
    tags_id bigint not null
);
create table tags(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);
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
/*alter table if exists posts_tags add constraint UK_lgvve0d1k6aqpjc20d0pw7syb unique (tags_id)
alter table if exists tags add constraint UK_t48xdq560gs3gap9g7jg36kgc unique (name)
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table if exists users add constraint UK_3g1j96g94xpk3lpxl2qbl985x unique (name)
alter table if exists comments add constraint FKn2na60ukhs76ibtpt9burkm27 foreign key (author_id) references users
alter table if exists comments add constraint FKh4c7lvsc298whoyd4w9ta25cr foreign key (post_id) references posts
alter table if exists posts add constraint FK6xvn0811tkyo3nfjk2xvqx6ns foreign key (author_id) references users
alter table if exists posts_tags add constraint FK79lx4quime8ct09nbmmf6wuao foreign key (tags_id) references tags
alter table if exists posts_tags add constraint FKcreclgob71ibo58gsm6l5wp6 foreign key (post_id) references posts
*/