CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);

-- first table of users and v1 is version of the table
-- we are using flyway migration to create and update the tables not the hibernate it is only for validation
