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
    user_id           bigserial,
    FOREIGN KEY(user_id) REFERENCES users(id)
--     tag_id bigserial FOREIGN KEY REFERENCES blog.tag
);

-- CREATE TYPE user_role AS ENUM ( 'guest', 'user', 'moderator', 'admin', 'expert');

-- role user_role default 'user'
-- firstName, lastName, email, role;