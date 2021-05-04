-- DROP DATABASE blog;
-- CREATE DATABASE blog;

-- DROP TABLE IF EXISTS "user";
CREATE TABLE IF NOT EXISTS "user"
(
    id         bigserial    NOT NULL,
    name       VARCHAR(20)  NOT NULL unique,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL unique,
    password   VARCHAR(255) NOT NULL,
    create_on  timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_on  timestamptz  NULL,
    roleDto       VARCHAR(20)   NOT NULL,
    PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS "tag";
CREATE TABLE IF NOT EXISTS "tag"
(
    id        bigserial   NOT NULL,
    name      VARCHAR(50) NOT NULL unique,
    create_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_on timestamptz NULL,
    PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS "post_tags";
create table post_tags
(
    post_id bigint not null unique,
    tags_id bigint not null,
    FOREIGN KEY (tags_id) REFERENCES tag (id)
);

-- DROP TABLE IF EXISTS "post";
CREATE TABLE IF NOT EXISTS "post"
(
    id                 bigserial    NOT NULL,
    title              varchar(250) NOT NULL,
    preview_attachment text         NOT NULL,
    tags_id            bigint       NULL, -- one to many via the intermediate table in hibernate
    user_id            bigint       NOT NULL,
    create_on          timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    text               text         NOT NULL,
    update_on          timestamptz  NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (tags_id) REFERENCES "post_tags" (post_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

-- DROP TABLE IF EXISTS "comment";
CREATE TABLE IF NOT EXISTS "comment"
(
    id        bigserial   NOT NULL,
    text      text        NOT NULL,
    post_id   bigint      NOT NULL,
    user_id   bigint      NOT NULL,
--     tags_id   bigint      NOT NULL,
    create_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_on timestamptz NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (post_id) REFERENCES "post" (id)
);

-- DROP TABLE IF EXISTS "comment_tags";
-- create table comment_tags
-- (
--     comment_id int not null unique,
--     tags_id    int not null,
--     FOREIGN KEY (tags_id) REFERENCES tag (id)
-- );


-- alter table if exists post
--     add constraint post_tags foreign key (tags_id) references "post_tags" (post_id);
-- alter table if exists comment
--     add constraint comment_tags foreign key (tags_id) references "comment_tags" (comment_id);
