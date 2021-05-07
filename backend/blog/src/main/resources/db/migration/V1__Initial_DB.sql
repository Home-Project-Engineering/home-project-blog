create sequence hibernate_sequence start 1 increment 1;

create table comments
(
    id         int8 not null,
    created_on timestamp,
    text       varchar(255),
    updated_on timestamp,
    author_id  int8,
    post_id    int8,
    primary key (id)
);

create table posts
(
    id                 int8 not null,
    created_on         timestamp,
    preview_attachment varchar(255),
    text               varchar(255),
    title              varchar(255),
    updated_on         timestamp,
    author_id          int8,
    primary key (id)
);

create table posts_tags
(
    post_id int8 not null,
    tag_id  int8 not null
);

create table roles
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table tags
(
    id   int8         not null,
    name varchar(255) not null,
    primary key (id)
);

create table users
(
    id             int8         not null,
    email          varchar(255) not null,
    first_name     varchar(255),
    last_name      varchar(255),
    name           varchar(255) not null,
    password       varchar(255) not null,
    role_entity_id int8,
    primary key (id)
);

alter table if exists tags add constraint UK_tag_name unique (name);

alter table if exists users add constraint UK_users_email unique (email);

alter table if exists users add constraint UK_users_name unique (name);

alter table if exists comments add constraint FK_comments_author_id foreign key (author_id) references users;

alter table if exists comments add constraint FK_comments_pist_id foreign key (post_id) references posts;

alter table if exists posts add constraint FK_posts_author_id foreign key (author_id) references users
    on delete set null;

alter table if exists posts_tags add constraint FK_posts_tags_tag_id foreign key (tag_id) references tags
    on delete cascade;

alter table if exists posts_tags add constraint FK_posts_tags_post_id foreign key (post_id) references posts
    on delete cascade;
alter table if exists users add constraint FK_users_role_entity_id foreign key (role_entity_id) references roles;
