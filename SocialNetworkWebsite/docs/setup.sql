DROP DATABASE `social`;

CREATE DATABASE `social`;

USE `social`;

CREATE TABLE `image` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`img` longblob NOT NULL,
	PRIMARY KEY (`id`)
) AUTO_INCREMENT = 1;

CREATE TABLE `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`email` varchar(255) NOT NULL,
	`passwd` varchar(20) NOT NULL,
	`screen_name`varchar(20) NOT NULL,
	`real_name` text,
	`gender` tinyint(1),
	`birthday` varchar(16),
	`interests` text,
	`profile_img_id` int(11),
	`config` text,
        `confirmation_code` char(36),
        `confirmation_status` tinyint(1),
	PRIMARY KEY(`id`),
	UNIQUE KEY `email_UNIQUE` (`email`),
        UNIQUE KEY `screenname_UNIQUE` (`screen_name`),
	FOREIGN KEY (`profile_img_id`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) AUTO_INCREMENT = 10000;

CREATE TABLE `wall` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`posted_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`user_id` int(11) NOT NULL,
	`poster_id` int(11) NOT NULL,	
	`text` text,
	`img_id` int(11),
	PRIMARY KEY(`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`poster_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`img_id`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) AUTO_INCREMENT = 1;

CREATE TABLE `friends` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`user_id` int(11) NOT NULL,	
	`friend_id` int(11) NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT = 1;

CREATE TABLE `invite_code` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL,
	`code` char(36) NOT NULL,
	`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT = 1;

INSERT INTO user(email, passwd, screen_name) VALUES ('admin@suffolk.edu', 'admin', 'Administrator');
INSERT INTO invite_code(user_id, code) VALUES ('10000', 'ebfcf0c5-71ed-4735-a7af-7c2a4d8d5d8f');
INSERT INTO invite_code(user_id, code) VALUES ('10000', 'bcdda4dd-abc9-4b4e-9db0-c4fd63d9f519');
INSERT INTO invite_code(user_id, code) VALUES ('10000', '2e701bed-9601-46b7-be46-c8b63b29e7b6');