--liquibase formatted sql

--changeset ryabchikov:1
DROP TABLE users_roles;
DROP TABLE users;
DROP TABLE roles;

CREATE TABLE users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    keycloak_user_id UUID NOT NULL UNIQUE,
    username    VARCHAR(50) UNIQUE NOT NULL CHECK (LENGTH(username) BETWEEN 3 AND 50)
);

CREATE INDEX idx_users_keycloak_user_id ON users (keycloak_user_id);