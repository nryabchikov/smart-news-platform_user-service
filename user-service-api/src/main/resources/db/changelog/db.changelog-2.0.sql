--liquibase formatted sql

--changeset ryabchikov:2
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_JOURNALIST');
INSERT INTO roles (name) VALUES ('ROLE_SUBSCRIBER');
