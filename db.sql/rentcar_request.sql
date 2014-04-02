CREATE DATABASE  IF NOT EXISTS `rentcar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rentcar`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: rentcar
-- ------------------------------------------------------
-- Server version	5.5.21

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
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `idrequest` int(11) NOT NULL AUTO_INCREMENT,
  `user_fk` int(11) NOT NULL,
  `car_fk` int(11) NOT NULL,
  `begin_date` varchar(15) NOT NULL DEFAULT '',
  `end_date` varchar(15) NOT NULL DEFAULT '',
  `passport` varchar(10) NOT NULL,
  `request_message` varchar(300) NOT NULL DEFAULT '',
  `response_message` varchar(300) NOT NULL DEFAULT '',
  `cost` int(11) NOT NULL,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`idrequest`),
  UNIQUE KEY `idrequest_UNIQUE` (`idrequest`),
  UNIQUE KEY `all_UNIQUE` (`user_fk`,`car_fk`,`begin_date`,`end_date`,`passport`),
  KEY `user_reference` (`user_fk`),
  KEY `car_reference` (`car_fk`),
  CONSTRAINT `car_reference` FOREIGN KEY (`car_fk`) REFERENCES `car` (`idcar`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_reference` FOREIGN KEY (`user_fk`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (6,2,15,'17/03/2012','17/03/2012','KV12345','get it to Ivanov','Your request was refused!',308,'REFUSED'),(7,2,5,'17/03/2012','17/03/2012','KV12312','get it to Ivanov too','Ivan Ivanov, your request accepted. Pay order please!',120,'ACCEPTED'),(8,3,4,'17/03/2012','17/03/2012','KV8765','I\'d like it','Vasiliy Vasilievich, your request accepted. Pay order please!',172,'ACCEPTED'),(9,2,4,'23/03/2012','23/03/2012','Pass','срочно','Your request accepted. Pay order please!',172,'ACCEPTED');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-09 14:09:51
