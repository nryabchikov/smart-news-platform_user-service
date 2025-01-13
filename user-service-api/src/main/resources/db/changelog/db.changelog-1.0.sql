--liquibase formatted sql

--changeset ryabchikov:1
CREATE TABLE users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login    VARCHAR(50) UNIQUE NOT NULL CHECK (LENGTH(login) BETWEEN 3 AND 50),
    password VARCHAR(100)       NOT NULL CHECK (LENGTH(password) BETWEEN 8 AND 100)
);

CREATE INDEX idx_users_login ON users (login);

CREATE TABLE roles
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) UNIQUE NOT NULL CHECK (LENGTH(name) < 50)
);

CREATE INDEX idx_roles_name ON roles (name);

CREATE TABLE users_roles
(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE SET NULL,
    CONSTRAINT uk_user_role unique (user_id, role_id)
);
