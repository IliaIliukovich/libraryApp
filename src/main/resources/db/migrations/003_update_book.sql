-- liquibase formatted sql
-- changeset ilia:003
ALTER TABLE book
    MODIFY COLUMN isbn VARCHAR(13) NULL;