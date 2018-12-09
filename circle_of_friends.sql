/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : circle_of_friends

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 09/12/2018 19:58:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pids` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0-都可见，1-自己可见，2-删除',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_a_uid` (`uid`),
  CONSTRAINT `fk_a_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) unsigned NOT NULL,
  `uid` bigint(20) unsigned NOT NULL,
  `to` bigint(20) unsigned NOT NULL COMMENT '自连外键（0是独立评论）',
  `comment` varchar(255) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_c_mid` (`mid`),
  KEY `fk_c_uid` (`uid`),
  KEY `fk_c_to` (`to`),
  CONSTRAINT `fk_c_mid` FOREIGN KEY (`mid`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_c_to` FOREIGN KEY (`to`) REFERENCES `comment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_c_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forward
-- ----------------------------
DROP TABLE IF EXISTS `forward`;
CREATE TABLE `forward` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) unsigned NOT NULL,
  `uid` bigint(20) unsigned NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_for_uid` (`uid`),
  KEY `fk_for_mid` (`mid`),
  CONSTRAINT `fk_for_mid` FOREIGN KEY (`mid`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_for_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid1` bigint(20) unsigned NOT NULL,
  `uid2` bigint(20) unsigned NOT NULL,
  `state` int(11) NOT NULL COMMENT '0-申请中，1-已通过，2-拒绝',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_f_uid1` (`uid1`),
  KEY `fk_f_uid2` (`uid2`),
  CONSTRAINT `fk_f_uid1` FOREIGN KEY (`uid1`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_f_uid2` FOREIGN KEY (`uid2`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `src` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` VALUES (13, '/image/1544338442596壁纸1.jpg', '2018-12-09 14:54:03');
INSERT INTO `image` VALUES (19, '/image/1544348331694_ce00749a-3276-4ca1-84e4-ececad735e50.file', '2018-12-09 17:38:52');
INSERT INTO `image` VALUES (20, '/image/1544348396051_d610ca85-40f7-4d51-a196-687f7de1e742.file', '2018-12-09 17:39:56');
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `from` bigint(20) DEFAULT NULL,
  `statue` int(1) NOT NULL COMMENT '0-都可见，1-自己可见，2-删除',
  `createtime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_m_uid` (`uid`),
  CONSTRAINT `fk_m_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
INSERT INTO `message` VALUES (11, 3, '123', '', 0, 0, '2018-12-02 09:00:00', NULL);
INSERT INTO `message` VALUES (12, 3, '321', '', 0, 0, '2018-12-02 13:40:34', NULL);
INSERT INTO `message` VALUES (13, 3, '今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头', '', 0, 0, '2018-12-02 15:01:02', NULL);
INSERT INTO `message` VALUES (14, 3, '今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头', '', 0, 0, '2018-12-02 15:01:15', NULL);
INSERT INTO `message` VALUES (28, 3, '123', '13', 0, 0, NULL, NULL);
INSERT INTO `message` VALUES (29, 3, '有图的', '13', 0, 0, '2018-12-09 16:13:54', NULL);
INSERT INTO `message` VALUES (30, 3, '这个怎么样', '20', 0, 0, '2018-12-09 17:39:56', NULL);
COMMIT;

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) unsigned NOT NULL,
  `uid` bigint(20) unsigned NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_p_mid` (`mid`),
  KEY `fk_p_uid` (`uid`),
  CONSTRAINT `fk_p_mid` FOREIGN KEY (`mid`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_p_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of point
-- ----------------------------
BEGIN;
INSERT INTO `point` VALUES (1, 30, 3, '2018-12-09 18:01:28');
INSERT INTO `point` VALUES (2, 30, 3, '2018-12-09 18:03:06');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `birth` date DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(11) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `img` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (3, 'shaozhengbo', '21232f297a57a5a743894a0e4a801fc3', '1996-12-21', '男', 'shaoISzhengbo@163.com', '17604098640', '计算机科学与技术1', '2018-11-04 14:42:45', 'img/1.jpg');
INSERT INTO `user` VALUES (4, 'test', '098f6bcd4621d373cade4e832627b4f6', '1996-12-21', '女', 'shaoISzhengbo@163.com', '17604098640', '日语', '2018-11-04 14:42:45', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
