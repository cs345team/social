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
	`user_id` int(11) NOT NULL,	
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
	UNIQUE KEY `userid_UNIQUE` (`user_id`),
	UNIQUE KEY `email_UNIQUE` (`email`),
        UNIQUE KEY `screenname_UNIQUE` (`screen_name`),
	FOREIGN KEY (`profile_img_id`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) AUTO_INCREMENT = 1;

CREATE TABLE `wall` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
	`time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
	`time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT = 1;

INSERT INTO user(user_id, email, passwd, screen_name, gender) VALUES (10000, 'admin@suffolk.edu', 'admin', 'Administrator', 0);
