-- liquibase formatted sql
-- changeset ilia:001

CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

CREATE TABLE IF NOT EXISTS `library`.`category` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`category_id`));

CREATE TABLE IF NOT EXISTS `library`.`tag` (
  `tag_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`tag_id`));

CREATE TABLE IF NOT EXISTS `library`.`visitor` (
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`));

CREATE TABLE IF NOT EXISTS `library`.`building` (
  `building_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(90) NOT NULL,
  `has_reading_room` TINYINT NOT NULL,
  PRIMARY KEY (`building_id`));

CREATE TABLE IF NOT EXISTS `library`.`book_detail` (
  `book_detail_id` INT NOT NULL,
  `abstract` VARCHAR(255) NULL,
  `publisher` VARCHAR(90) NULL,
  `year` VARCHAR(10) NULL,
  PRIMARY KEY (`book_detail_id`));

CREATE TABLE IF NOT EXISTS `library`.`book` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(90) NOT NULL,
  `available_amount` INT NOT NULL,
  `isbn` VARCHAR(45) NULL,
  `access_level` VARCHAR(45) NOT NULL,
  `category_id` INT NOT NULL,
  `building_id` INT NOT NULL,
  `book_detail_id` INT NULL,
  PRIMARY KEY (`book_id`),
  INDEX `fk_book_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_book_building1_idx` (`building_id` ASC) VISIBLE,
  INDEX `fk_book_book_detail1_idx` (`book_detail_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `library`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_building1`
    FOREIGN KEY (`building_id`)
    REFERENCES `library`.`building` (`building_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_book_detail1`
    FOREIGN KEY (`book_detail_id`)
    REFERENCES `library`.`book_detail` (`book_detail_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `library`.`author` (
  `author_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL,
  `author_info` VARCHAR(255) NULL,
  PRIMARY KEY (`author_id`));

CREATE TABLE IF NOT EXISTS `library`.`book_has_tag` (
  `book_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  INDEX `fk_book_has_tag_tag1_idx` (`tag_id` ASC) VISIBLE,
  INDEX `fk_book_has_tag_book1_idx` (`book_id` ASC) VISIBLE,
  PRIMARY KEY (`book_id`, `tag_id`),
  CONSTRAINT `fk_book_has_tag_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_tag_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `library`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `library`.`book_has_author` (
  `author_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`author_id`, `book_id`),
  INDEX `fk_author_has_book_book1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_author_has_book_author1_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_author_has_book_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `library`.`author` (`author_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_author_has_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `library`.`visitor_took_book` (
  `visitor_email` VARCHAR(45) NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`visitor_email`, `book_id`),
  INDEX `fk_visitor_has_book_book1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_visitor_has_book_visitor1_idx` (`visitor_email` ASC) VISIBLE,
  CONSTRAINT `fk_visitor_has_book_visitor1`
    FOREIGN KEY (`visitor_email`)
    REFERENCES `library`.`visitor` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_visitor_has_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `library`.`visitor_reading_room_book` (
  `visitor_email` VARCHAR(45) NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`visitor_email`, `book_id`),
  INDEX `fk_visitor_has_book_book2_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_visitor_has_book_visitor2_idx` (`visitor_email` ASC) VISIBLE,
  CONSTRAINT `fk_visitor_has_book_visitor2`
    FOREIGN KEY (`visitor_email`)
    REFERENCES `library`.`visitor` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_visitor_has_book_book2`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);