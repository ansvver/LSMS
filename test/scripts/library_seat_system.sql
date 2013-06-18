-- ----------------------------
-- Table structure for `PARTNER`
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE USER (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`user_num` varchar(64) NOT NULL,
	`password` varchar(64) NOT NULL,
	`name` varchar(64) NOT NULL,
	`dept` varchar(64) NOT NULL,
	`reserve_times` int NOT NULL DEFAULT '0',
	`break_times` int NOT NULL DEFAULT '0',
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `user_num_idx` (`user_num`),
	INDEX `dept_idx` (`dept`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `DESK`;
CREATE TABLE DESK (
	`desk_id` int(11) NOT NULL AUTO_INCREMENT,
	`floor` tinyint NOT NULL,
	`block` varchar(8) NOT NULL,
	`desk_num` tinyint NOT NULL,
	`is_able` tinyint(1) NOT NULL DEFAULT '1',
	PRIMARY KEY (`desk_id`),
	UNIQUE KEY `floor` (`floor`),
	INDEX `floor_idx` (`floor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE ORDERS (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` varchar(64) NOT NULL,
	`desk_id` varchar(64) NOT NULL,
	`desk_num` tinyint NOT NULL,
	`reserve_type` varchar(64) NOT NULL,
	`reserve_begin_time` timestamp,
	`reserve_end_time` timestamp,
	PRIMARY KEY (`id`),
	INDEX `user_id_idx` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

