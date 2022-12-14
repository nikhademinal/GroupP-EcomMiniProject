-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: miniproject
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `purchase_history`
--

DROP TABLE IF EXISTS `purchase_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_history` (
  `Email` varchar(255) DEFAULT NULL,
  `Product_id` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_history`
--

LOCK TABLES `purchase_history` WRITE;
/*!40000 ALTER TABLE `purchase_history` DISABLE KEYS */;
INSERT INTO `purchase_history` VALUES ('poonam@gmail.com',3,4),('poonam@gmail.com',5,4),('nikhademinal@gmail.com',3,2),('nikhademinal@gmail.com',6,1),('nikhademinal@gmail.com',3,4),('nikhademinal@gmail.com',4,3),('nikhademinal@gmail.com',5,1),('nikhademinal@gmail.com',8,2),('nikhademinal@gmail.com',3,2),('nikhademinal@gmail.com',3,1),('nikhademinal@gmail.com',3,2),('nikhademinal@gmail.com',1,2),('nikhademinal@gmail.com',2,1),('nikhademinal@gmail.com',3,1),('swap@outlook.com',4,1),('nikhademinal@gmail.com',1,1),('nikhademinal@gmail.com',2,1),('nikhademinal@gmail.com',2,1),('nikhademinal@gmail.com',3,1),('nikhademinal@gmail.com',3,1),('nikhademinal@gmail.com',2,1),('nikhademinal@gmail.com',3,1),('nikhademinal@gmail.com',3,1),('nikhademinal@gmail.com',4,1),('nikhademinal@gmail.com',10,1),('nikhademinal@gmail.com',1,1),('nikhademinal@gmail.com',2,1),('abhinav@gmail.com',3,1),('abhinav@gmail.com',6,1),('abhinav@gmail.com',4,2),(NULL,4,2),(NULL,2,3),(NULL,4,2),(NULL,3,4),(NULL,2,3),(NULL,2,3),(NULL,5,2),(NULL,3,2),(NULL,3,4),(NULL,3,1);
/*!40000 ALTER TABLE `purchase_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-16 18:01:27
