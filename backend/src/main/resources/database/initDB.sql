-- DROP DATABASE blog;
-- CREATE DATABASE blog;

-- DROP TABLE IF EXISTS "user";
CREATE TABLE IF NOT EXISTS "user"
(
    id          bigserial    NOT NULL,
    name        VARCHAR(20)  NOT NULL unique,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    email       VARCHAR(255) NOT NULL unique,
    password    VARCHAR(255) NOT NULL,
    create_time timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time timestamptz  NULL,
    role        VARCHAR(1)   NOT NULL,
    PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS "tag";
CREATE TABLE IF NOT EXISTS "tag"
(
    id          bigserial   NOT NULL,
    name        VARCHAR(50) NOT NULL unique,
    create_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time timestamptz NULL,
    PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS "post";
CREATE TABLE IF NOT EXISTS "post"
(
    id          bigserial    NOT NULL,
    tags_id     bigint       NULL, -- один ко многим через промежуточную таблицу в хибернейте
    create_on   timestamptz  NOT NULL,
    user_id     bigint       NOT NULL,
    text        text         NOT NULL,
    title       varchar(250) NOT NULL,
    name        VARCHAR(50)  NOT NULL,
    preview     text         NOT NULL,
    create_time timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time timestamptz  NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

-- DROP TABLE IF EXISTS "comment";
CREATE TABLE IF NOT EXISTS "comment"
(
    id          bigserial   NOT NULL,
    user_id     bigint      NOT NULL,
    tags_id     integer     NULL, -- один ко многим через промежуточную таблицу в хибернейте
    text        text        NOT NULL,
    post_id     bigint      NOT NULL,
    create_on   timestamptz NOT NULL,
    create_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time timestamptz NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (post_id) REFERENCES "post" (id)
);

-- DROP TABLE IF EXISTS "comment_tags";
create table comment_tags
(
    comment_id int not null unique,
    tags_id    int not null,
    FOREIGN KEY (tags_id) REFERENCES tag (id)
);

-- DROP TABLE IF EXISTS "post_tags";
create table post_tags
(
    post_id int not null unique,
    tags_id int not null,
    FOREIGN KEY (tags_id) REFERENCES tag (id)
);

alter table if exists post add constraint post_tags foreign key (tags_id) references "post_tags"(post_id);
alter table if exists comment add constraint comment_tags foreign key (tags_id) references "comment_tags"(comment_id);
