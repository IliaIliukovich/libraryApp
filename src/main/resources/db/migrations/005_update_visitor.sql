-- liquibase formatted sql
-- changeset ilia:005

ALTER TABLE visitor
    ADD COLUMN refresh_token VARCHAR(255);