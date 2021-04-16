DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id bigserial PRIMARY KEY,
    name VARCHAR (200),
    firstName VARCHAR (200),
    lastName VARCHAR (200),
    email VARCHAR (200),
    password VARCHAR (200),
    role VARCHAR (200)
    );

-- firstName, lastName, email, role;