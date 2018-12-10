-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: circle_of_friends
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forward`
--

DROP TABLE IF EXISTS `forward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forward`
--

LOCK TABLES `forward` WRITE;
/*!40000 ALTER TABLE `forward` DISABLE KEYS */;
/*!40000 ALTER TABLE `forward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `src` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (13,'/image/1544338442596壁纸1.jpg','2018-12-09 14:54:03'),(22,'/image/1544405261001_e4311807-af97-4e17-8516-3fda1b847964.png','2018-12-10 09:28:25'),(23,'/image/1544405413511_54c59e2c-5c01-488d-b581-69d8fbb333ec.png','2018-12-10 09:30:15'),(24,'/image/1544406173714_3abeb4bc-3edf-40ef-8315-09a6088c1fb9.png','2018-12-10 09:42:54'),(25,'/image/1544406294557_2565db9e-fb96-4c4c-a5b9-d2f2c4d6ca36.png','2018-12-10 09:44:55'),(26,'/image/1544406392019_8ae4f725-76d5-46c6-8d9a-19af4c7a5062.png','2018-12-10 09:46:32'),(27,'/image/1544407975104_01efd754-1909-4c43-9c36-fd9395dffab9.png','2018-12-10 10:12:55');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (11,3,'123','23',0,0,'2018-12-02 09:00:00',NULL),(12,3,'321','23',0,0,'2018-12-02 13:40:34',NULL),(13,3,'今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头','23',0,0,'2018-12-02 15:01:02',NULL),(14,3,'今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头今年头','23',0,0,'2018-12-02 15:01:15',NULL),(28,3,'123','13',0,0,NULL,NULL),(29,3,'有图的','13',0,0,'2018-12-09 16:13:54',NULL),(30,3,'这个怎么样','20',0,0,'2018-12-09 17:39:56',NULL),(33,3,'test','23',0,0,'2018-12-10 09:30:15',NULL),(34,3,'test1','24',0,0,'2018-12-10 09:42:54',NULL),(35,3,'123','25',0,0,'2018-12-10 09:44:55',NULL),(36,3,'test model after send success','26',0,0,'2018-12-10 09:46:32',NULL),(37,3,'4321`','27',0,0,'2018-12-10 10:12:55',NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

LOCK TABLES `point` WRITE;
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT INTO `point` VALUES (1,30,3,'2018-12-09 18:01:28'),(2,30,3,'2018-12-09 18:03:06'),(3,34,3,'2018-12-10 10:12:27'),(4,37,3,'2018-12-10 10:13:02');
/*!40000 ALTER TABLE `point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'shaozhengbo','21232f297a57a5a743894a0e4a801fc3','1996-12-21','男','shaoISzhengbo@163.com','17604098640','计算机科学与技术1','2018-11-04 14:42:45','img/1.jpg'),(4,'test','e10adc3949ba59abbe56e057f20f883e','1996-12-21','女','shaoISzhengbo@163.com','17604098640','日语','2018-11-04 14:42:45','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'circle_of_friends'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-10 18:02:32
