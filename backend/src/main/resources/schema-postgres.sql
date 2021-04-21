DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id bigserial PRIMARY KEY NOT NULL,
    name VARCHAR (255) NOT NULL,
    firstName VARCHAR (255),
    lastName VARCHAR (255),
    email VARCHAR (255) NOT NULL,
    password VARCHAR (255) NOT NULL,
    role varchar(10)
    );

-- CREATE TYPE user_role AS ENUM ( 'guest', 'user', 'moderator', 'admin', 'expert');

role user_role default 'user'
-- firstName, lastName, email, role;