DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id        bigserial PRIMARY KEY NOT NULL,
    name      VARCHAR(255)          NOT NULL UNIQUE,
    firstName VARCHAR(255),
    lastName  VARCHAR(255),
    email     VARCHAR(255)          NOT NULL UNIQUE,
    password  VARCHAR(255)          NOT NULL,
    role      varchar(10)           NOT NULL
);

DROP TABLE IF EXISTS tags;
CREATE TABLE tags
(
    id   bigserial PRIMARY KEY NOT NULL,
    name VARCHAR(255)          NOT NULL UNIQUE
)

DROP TABLE IF EXISTS posts;
CREATE TABLE posts
(
    id                bigserial PRIMARY KEY NOT NULL,
    text              VARCHAR,
    title             VARCHAR(255),
    previewAttachment VARCHAR(255),
    user_id           bigserial REFERENCES users,
    tag_id            bigserial REFERENCES tags,
    created_on        timestamp default current_timestamp,
    updated_on        timestamp default current_timestamp
);

CREATE TABLE posts_tags
(
    post_id bigserial REFERENCES posts ON DELETE CASCADE,
    tag_id  bigserial REFERENCES tags ON DELETE RESTRICT,
    PRIMARY KEY (product_no, order_id)
);

alter table if exists posts_tags add constraint posts_tags_tags foreign key (tag_id) references tags on delete cascade;
alter table if exists posts_tags add constraint posts_tags_posts foreign key (post_id) references posts on delete cascade;

alter table if exists comments add constraint comments_posts foreign key (post_id) references posts on delete cascade;

-- CREATE TYPE user_role AS ENUM ( 'guest', 'user', 'moderator', 'admin', 'expert');

-- role user_role default 'user'
-- firstName, lastName, email, role;