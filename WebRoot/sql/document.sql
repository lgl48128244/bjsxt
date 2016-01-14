/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50129
Source Host           : localhost:3306
Source Database       : document

Target Server Type    : MYSQL
Target Server Version : 50129
File Encoding         : 65001

Date: 2016-01-14 14:46:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_attach
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `new_name` varchar(255) DEFAULT NULL,
  `old_name` varchar(255) DEFAULT NULL,
  `size` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `doc_id` int(11) DEFAULT NULL,
  `msg_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5F974070E410B6B7` (`doc_id`),
  KEY `FK5F974070A6D97F46` (`msg_id`),
  CONSTRAINT `FK5F974070A6D97F46` FOREIGN KEY (`msg_id`) REFERENCES `t_msg` (`id`),
  CONSTRAINT `FK5F974070E410B6B7` FOREIGN KEY (`doc_id`) REFERENCES `t_document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attach
-- ----------------------------

-- ----------------------------
-- Table structure for t_dep
-- ----------------------------
DROP TABLE IF EXISTS `t_dep`;
CREATE TABLE `t_dep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dep
-- ----------------------------
INSERT INTO `t_dep` VALUES ('1', '人事处');
INSERT INTO `t_dep` VALUES ('2', '办公室');
INSERT INTO `t_dep` VALUES ('3', '团委');
INSERT INTO `t_dep` VALUES ('4', '学生处');
INSERT INTO `t_dep` VALUES ('5', '招办处');
INSERT INTO `t_dep` VALUES ('6', '教务处');
INSERT INTO `t_dep` VALUES ('8', '账务处');

-- ----------------------------
-- Table structure for t_dep_document
-- ----------------------------
DROP TABLE IF EXISTS `t_dep_document`;
CREATE TABLE `t_dep_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) DEFAULT NULL,
  `doc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB3A16536E410B6B7` (`doc_id`),
  KEY `FKB3A165364228837` (`dep_id`),
  CONSTRAINT `FKB3A165364228837` FOREIGN KEY (`dep_id`) REFERENCES `t_dep` (`id`),
  CONSTRAINT `FKB3A16536E410B6B7` FOREIGN KEY (`doc_id`) REFERENCES `t_document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dep_document
-- ----------------------------

-- ----------------------------
-- Table structure for t_dep_scope
-- ----------------------------
DROP TABLE IF EXISTS `t_dep_scope`;
CREATE TABLE `t_dep_scope` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) DEFAULT NULL,
  `s_dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD2CD98194FB9AD83` (`s_dep_id`),
  CONSTRAINT `FKD2CD98194FB9AD83` FOREIGN KEY (`s_dep_id`) REFERENCES `t_dep` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dep_scope
-- ----------------------------

-- ----------------------------
-- Table structure for t_document
-- ----------------------------
DROP TABLE IF EXISTS `t_document`;
CREATE TABLE `t_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `create_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEA09448655109AF4` (`user_id`),
  CONSTRAINT `FKEA09448655109AF4` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_document
-- ----------------------------

-- ----------------------------
-- Table structure for t_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_msg`;
CREATE TABLE `t_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `create_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68F7D3655109AF4` (`user_id`),
  CONSTRAINT `FK68F7D3655109AF4` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_msg
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB63CCB64228837` (`dep_id`),
  CONSTRAINT `FKCB63CCB64228837` FOREIGN KEY (`dep_id`) REFERENCES `t_dep` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('4', '123456789@qq.com', 'admin', 'admin', '1', 'admin', '1');
INSERT INTO `t_user` VALUES ('5', 'user0@zttc.net', '陈北', '123', '0', 'user0', '4');
INSERT INTO `t_user` VALUES ('6', 'user1@zttc.net', '张立集', '123', '0', 'user1', '5');
INSERT INTO `t_user` VALUES ('7', '', '', '', '0', '1', '1');
INSERT INTO `t_user` VALUES ('8', '', '', '', '0', '2', '1');
INSERT INTO `t_user` VALUES ('9', '', '', '', '0', '3', '1');
INSERT INTO `t_user` VALUES ('10', '', '', '', '0', '4', '1');
INSERT INTO `t_user` VALUES ('11', '', '', '', '0', '5', '1');
INSERT INTO `t_user` VALUES ('12', '', '', '', '0', '6', '1');
INSERT INTO `t_user` VALUES ('13', '', '', '', '0', '7', '1');
INSERT INTO `t_user` VALUES ('14', '', '', '', '0', '8', '1');
INSERT INTO `t_user` VALUES ('15', '', '', '', '0', '9', '1');
INSERT INTO `t_user` VALUES ('16', '', '', '', '0', '10', '1');
INSERT INTO `t_user` VALUES ('17', '', '', '', '0', '11', '1');
INSERT INTO `t_user` VALUES ('18', '', '', '', '0', '12', '1');
INSERT INTO `t_user` VALUES ('19', '', '', '', '0', '13', '1');

-- ----------------------------
-- Table structure for t_user_email
-- ----------------------------
DROP TABLE IF EXISTS `t_user_email`;
CREATE TABLE `t_user_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `protocol` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2FE79AD355109AF4` (`user_id`),
  CONSTRAINT `FK2FE79AD355109AF4` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_email
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_user_msg`;
CREATE TABLE `t_user_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_read` int(11) DEFAULT NULL,
  `msg_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA6CF587855109AF4` (`user_id`),
  KEY `FKA6CF5878A6D97F46` (`msg_id`),
  CONSTRAINT `FKA6CF587855109AF4` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKA6CF5878A6D97F46` FOREIGN KEY (`msg_id`) REFERENCES `t_msg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_msg
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_read_doc
-- ----------------------------
DROP TABLE IF EXISTS `t_user_read_doc`;
CREATE TABLE `t_user_read_doc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK580CA478E410B6B7` (`doc_id`),
  KEY `FK580CA47855109AF4` (`user_id`),
  CONSTRAINT `FK580CA47855109AF4` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK580CA478E410B6B7` FOREIGN KEY (`doc_id`) REFERENCES `t_document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_read_doc
-- ----------------------------
