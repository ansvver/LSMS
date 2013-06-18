/*
Navicat MySQL Data Transfer

Source Server         : localhostMySql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : library_seat_system

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-01-15 20:59:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `desk`
-- ----------------------------
DROP TABLE IF EXISTS `desk`;
CREATE TABLE `desk` (
  `desk_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor` tinyint(4) NOT NULL,
  `block` varchar(8) NOT NULL,
  `desk_num` tinyint(4) NOT NULL,
  `is_able` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`desk_id`),
  KEY `floor_idx` (`floor`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desk
-- ----------------------------
INSERT INTO desk VALUES ('1', '1', 'A', '2', '1');
INSERT INTO desk VALUES ('2', '1', 'B', '1', '1');
INSERT INTO desk VALUES ('3', '1', 'A', '1', '1');
INSERT INTO desk VALUES ('4', '1', 'A', '3', '1');
INSERT INTO desk VALUES ('5', '1', 'A', '4', '1');
INSERT INTO desk VALUES ('6', '1', 'A', '5', '1');
INSERT INTO desk VALUES ('7', '1', 'A', '6', '1');
INSERT INTO desk VALUES ('8', '1', 'B', '2', '1');
INSERT INTO desk VALUES ('9', '1', 'B', '3', '1');
INSERT INTO desk VALUES ('10', '1', 'B', '4', '1');
INSERT INTO desk VALUES ('11', '1', 'B', '5', '1');
INSERT INTO desk VALUES ('12', '1', 'B', '6', '1');
INSERT INTO desk VALUES ('13', '1', 'C', '1', '1');
INSERT INTO desk VALUES ('14', '1', 'A', '7', '1');
INSERT INTO desk VALUES ('15', '1', 'A', '8', '1');
INSERT INTO desk VALUES ('16', '1', 'B', '7', '1');
INSERT INTO desk VALUES ('17', '1', 'B', '8', '1');
INSERT INTO desk VALUES ('18', '1', 'C', '2', '1');
INSERT INTO desk VALUES ('19', '1', 'C', '3', '1');
INSERT INTO desk VALUES ('20', '1', 'C', '4', '1');
INSERT INTO desk VALUES ('21', '1', 'C', '5', '1');
INSERT INTO desk VALUES ('22', '1', 'C', '6', '1');
INSERT INTO desk VALUES ('23', '1', 'D', '1', '1');
INSERT INTO desk VALUES ('24', '1', 'D', '2', '1');
INSERT INTO desk VALUES ('25', '1', 'D', '3', '1');
INSERT INTO desk VALUES ('26', '1', 'D', '4', '1');
INSERT INTO desk VALUES ('27', '1', 'D', '5', '1');
INSERT INTO desk VALUES ('28', '1', 'D', '6', '1');
INSERT INTO desk VALUES ('29', '1', 'E', '1', '1');
INSERT INTO desk VALUES ('30', '1', 'E', '2', '1');
INSERT INTO desk VALUES ('31', '1', 'E', '3', '1');
INSERT INTO desk VALUES ('32', '1', 'E', '3', '1');
INSERT INTO desk VALUES ('33', '1', 'E', '4', '1');
INSERT INTO desk VALUES ('34', '1', 'E', '5', '1');
INSERT INTO desk VALUES ('35', '1', 'E', '6', '1');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `desk_id` varchar(64) NOT NULL,
  `seat_num` tinyint(4) NOT NULL,
  `reserve_type` varchar(64) NOT NULL,
  `reserve_begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reserve_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO orders VALUES ('1', '1', '1', '2', '全天', '2013-01-15 00:05:51', '2013-01-15 00:06:13');
INSERT INTO orders VALUES ('2', '1', '1', '3', '全天', '2013-01-15 00:14:08', '2013-01-15 00:14:14');
INSERT INTO orders VALUES ('3', '1', '1', '1', '全天', '2013-01-15 00:14:49', '2013-01-15 00:14:53');
INSERT INTO orders VALUES ('4', '1', '2', '1', '全天', '2013-01-15 00:15:37', '2013-01-15 00:15:41');
INSERT INTO orders VALUES ('5', '1', '2', '2', '全天', '2013-01-15 00:16:02', '2013-01-15 00:16:07');
INSERT INTO orders VALUES ('6', '1', '3', '1', '全天', '2013-01-15 00:58:16', '2013-01-15 00:58:19');
INSERT INTO orders VALUES ('7', '1', '4', '3', '全天', '2013-01-15 00:58:52', '2013-01-15 00:58:55');
INSERT INTO orders VALUES ('8', '1', '17', '1', '全天', '2013-01-15 00:59:15', '2013-01-15 00:59:19');
INSERT INTO orders VALUES ('9', '1', '20', '1', '全天', '2013-01-15 00:59:36', '2013-01-15 00:59:41');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `dept` varchar(64) NOT NULL,
  `reserve_times` int(11) NOT NULL DEFAULT '0',
  `break_times` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_num_idx` (`user_num`),
  KEY `dept_idx` (`dept`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'sdf', 'sdf', 'sdfs', 'adf', '0', '0');
INSERT INTO user VALUES ('2', 'sdf223', 'sdf', 'sdfs', 'adf', '0', '0');
