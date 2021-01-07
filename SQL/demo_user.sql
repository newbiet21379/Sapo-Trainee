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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin'),(2,'ad1','ad1'),(3,'mem1','mem1'),(4,'edit1','edit1'),(7,'admin6','$2a$10$iDxAVHFWY6rOQBRHhQpZO.FcEMEU6i.x0RLRk54lHC1WKz6P5jmSC'),(9,'goddamnthisbugshiet_AAAAAAAAAAAAAAAA','$2a$10$H55NKaMFZqjoT07o2uRbtuvaUzOWFAEYMqqgWk.WuuNOuWcJqdpGK'),(10,'trung123','$2a$10$ufGIQ4ee7.zI30bXoJ9zBujIJiGf81wrwx99RvpT7hmPK1a34KA1K'),(11,'trung123444','$2a$10$AsDwCkcyWkb5ZzlD9oaVL.WvE0GcJ12JiQw3afuZgQVykvU6oLlCa'),(12,'bot','$2a$10$E7BRG7ouqMEFNbOnx7haa.FIW.AUMQxvSgpc2qev.YYOPwo.Eo496'),(13,'botBox','$2a$10$gq/FvkbRWee6Cr0Bwy3QKuOaijwL1.5Nqkae5BL91tSYWDGMTth5q'),(14,'botBoxBox','$2a$10$uOOkXqDvssTRVd1DFztSCu4cRYnzj7xpKFUQjrN0cMfZKgE7hNwgu'),(15,'ad1234','$2a$10$7SAA7w6dL1K6gtxBPO4Mo.FV2DExo3CnfslV0NOKvZ9ZDi8Fvm09G');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
