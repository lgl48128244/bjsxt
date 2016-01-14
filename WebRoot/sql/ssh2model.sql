/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50129
Source Host           : localhost:3306
Source Database       : ssh2model

Target Server Type    : MYSQL
Target Server Version : 50129
File Encoding         : 65001

Date: 2016-01-14 14:46:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logininfo
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginCode` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT '0' COMMENT '0保密，1男，2女',
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of logininfo
-- ----------------------------
INSERT INTO `logininfo` VALUES ('1', 'ddd', 'ddd', 'ddd', '0', 'ddd', 'ddd', 'dd');
INSERT INTO `logininfo` VALUES ('2', 'trtr', 'rtr', 'rtr', '0', 'trtrt', 'rtrttyty', 'rtrt');
INSERT INTO `logininfo` VALUES ('3', 'ddfdf', 'ffdf', 'fff', '0', 'dfdf', 'fdfdf', 'ffddf');

-- ----------------------------
-- Table structure for vdp_log_export
-- ----------------------------
DROP TABLE IF EXISTS `vdp_log_export`;
CREATE TABLE `vdp_log_export` (
  `pk` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `value` text,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='日志输出类型';

-- ----------------------------
-- Records of vdp_log_export
-- ----------------------------
INSERT INTO `vdp_log_export` VALUES ('1', 'console', '<node type=\"console\">  		\r\r	\r\r</node>');
INSERT INTO `vdp_log_export` VALUES ('2', 'file', '<node type=\"file\">  		\r\n        <param name=\"文件最大存量\" key=\"MaxFileSize\" value=\"1000kb\"/> 		\r\n        <param name=\"文件最大备份个数\" key=\"MaxBackupIndex\" value=\"10\"/> 		\r\n        <param name=\"文件的编码格式\" key=\"Encoding\" value=\"UTF-8\" /> 	\r\n</node>');
INSERT INTO `vdp_log_export` VALUES ('3', '自定义输出', null);
INSERT INTO `vdp_log_export` VALUES ('4', 'email2', '<node type=\"email\"> \r        <param  key=\"Threshold\" name=\"错误级别\" value=\"ERROR\"/> 			\r\r        <param  key=\"BufferSize\"  name=\"缓存大小\" value=\"1024\"/> 			\r\r        <param  name=\"发送日志的邮箱\"  key=\"From\" value=\"mymailhost@163.com\"/> 			\r\r        <param  name=\"发送日志邮箱SMTP\"  key=\"SMTPHost\" value=\"smtp.163.com\"/> 			\r\r        <param  name=\"日志邮件主题\"  key=\"Subject\" value=\"手机软件日志\"/> 	\r\r        <param  name=\"日志邮件接收者\"  key=\"To\" value=\"lixunhui@qq.com\"/> 			\r\r        <param name=\"发送日志的邮箱用户名\" key=\"SMTPUsername\"  value=\"mymailhost\"/> 			\r\r        <param  name=\"发送日志的邮箱密码\"  key=\"SMTPPassword\" value=\"xxxxxxxx\"/> 			\r\r        <param  name=\"发送的日志邮件编码\"  key=\"Encoding\" value=\"UTF-8\" />\r\r</node>');
INSERT INTO `vdp_log_export` VALUES ('7', 'dd', 'dddd');
INSERT INTO `vdp_log_export` VALUES ('8', 'dd', 'dd');
