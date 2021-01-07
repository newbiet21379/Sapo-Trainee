-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.30-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `description` varchar(255) CHARACTER SET latin1 NOT NULL,
  `img_link` varchar(255) CHARACTER SET latin1 NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sale_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_ibfk_1` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,3,'asd','dsa','dsa',123131.00,21,20),(4,6,'A','A','A',12312.00,2,1),(5,3,'You shall not found','Not Found','Girl',1000000.00,10,5),(6,1,'You shall not found','Not Found','Fod',1000000.00,10,5),(8,5,'You shall not found','Not Found','Fod',1000000.00,10,5),(9,4,'You shall not found','Not Found','Fod',1000000.00,10,5),(12,3,'You shall not found','Not Found','Fod',1000000.00,10,5),(15,1,'You shall not found','Not Found','Fod',1000000.00,10,5),(16,1,'You shall not found','Not Found','Fod',1000000.00,10,5),(17,6,'You shall not found','Not Found','Fod',1000000.00,10,5),(18,1,'You shall not found','Not Found','Fod',1000000.00,10,5),(19,20,'You shall not found','Not Found','FDCC',1000000.00,10,5),(20,10,'You shall not found','Not Found','Fod',1000000.00,10,5),(21,8,'You shall not found','Not Found','Fod',1000000.00,10,5),(22,9,'You shall not found','Not Found','Fod',1000000.00,10,5),(23,9,'You shall not found','Not Found','Fod',1000000.00,10,5),(24,6,'You shall not found','Not Found','Fod',1000000.00,10,5),(25,7,'You shall not found','Not Found','Fod',1000000.00,10,5),(26,5,'You shall not found','Not Found','Fod',1000000.00,10,5),(28,3,'You shall not found','Not Found','FDCC',1000000.00,10,5),(29,5,'You shall found me','Found','FBI',200000.00,10,1),(30,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(31,10,'You shall not found','Not Found','FDCC',1000000.00,10,5),(32,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(33,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(34,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(35,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(36,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(37,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(38,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(39,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(40,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(41,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(42,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(43,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(44,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(45,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(46,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(47,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(48,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(49,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(50,9,'You shall not found','Not Found','FDCC',1000000.00,10,5),(51,21,'You shall not found','Not Found','FDCC',1000000.00,10,5),(52,21,'You shall not found','Not Found','FDCC',1000000.00,10,5),(53,21,'You shall not found','Not Found','FDCC',1000000.00,10,5),(54,21,'You shall not found','Not Found','FDCC',1000000.00,10,5),(55,21,'You shall not found','Not Found','FDCC',1000000.00,10,5),(56,21,'You shall not found','Not Found','FDCC',1000000.00,10,5),(57,22,'You shall not found','Not Found','FDCC',1000000.00,10,5),(58,22,'You shall not found','Not Found','FDCC',1000000.00,10,5),(59,22,'You shall not found','Not Found','FDCC',1000000.00,10,5),(60,22,'You shall not found','Not Found','FDCC',1000000.00,10,5),(61,22,'You shall not found','Not Found','FDCC',1000000.00,10,5),(62,22,'You shall not found','Not Found','FDCC',1000000.00,10,5),(63,23,'You shall not found','Not Found','FDCC',1000000.00,10,5),(64,23,'You shall not found','Not Found','FDCC',1000000.00,10,5),(65,23,'You shall not found','Not Found','FDCC',1000000.00,10,5),(66,23,'You shall not found','Not Found','FDCC',1000000.00,10,5),(67,23,'You shall not found','Not Found','FDCC',1000000.00,10,5),(68,23,'You shall not found','Not Found','FDCC',1000000.00,10,5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-11 10:29:59
