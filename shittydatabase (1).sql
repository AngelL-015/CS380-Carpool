CREATE DATABASE  IF NOT EXISTS `carpooldatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carpooldatabase`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: carpooldatabase
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `carpoolpassenger`
--

DROP TABLE IF EXISTS `carpoolpassenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carpoolpassenger` (
  `carpoolId` int NOT NULL,
  `passengerId` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpoolpassenger`
--

LOCK TABLES `carpoolpassenger` WRITE;
/*!40000 ALTER TABLE `carpoolpassenger` DISABLE KEYS */;
INSERT INTO `carpoolpassenger` VALUES (1,102),(2,105),(1,107),(1,107),(2,105),(3,107),(3,106),(3,105),(3,104),(3,101),(4,107),(4,106),(5,108),(6,109);
/*!40000 ALTER TABLE `carpoolpassenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carpools`
--

DROP TABLE IF EXISTS `carpools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carpools` (
  `carpoolId` int NOT NULL,
  `driverId` int DEFAULT NULL,
  `passengerLimit` int DEFAULT NULL,
  `pickupLocation` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `pickupDate` varchar(10) DEFAULT NULL,
  `pickupTime` varchar(45) DEFAULT NULL,
  `carpoolStatus` tinyint DEFAULT NULL,
  PRIMARY KEY (`carpoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpools`
--

LOCK TABLES `carpools` WRITE;
/*!40000 ALTER TABLE `carpools` DISABLE KEYS */;
INSERT INTO `carpools` VALUES (1,101,4,'Location A','Destination A','06-17-2021','09:00 AM',1),(2,102,2,'Location B','Destination B','07-20-2022','12:00 PM',1),(3,103,10,'Location C','Destination C','12-24-2020','1:00 PM',1),(4,103,3,'Location D','Destination D','02-12-2021','5:00 PM',1),(5,104,3,'Location E','Destination E','03-05-2023','2:30 PM',1),(6,105,5,'Location F','Destination F','04-10-2023','11:00 AM',1);
/*!40000 ALTER TABLE `carpools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profiles` (
  `passengerId` int NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (101,'John','Doe','johndoey@yahoo.com','5098001212','johndoe','password123','Address 1'),(102,'Jane','Smith','janesmith@gmail.com','5107803492','janesmith','password456','Address 2'),(103,'Dwayne','Johnson','ledwayne@yahoo.com','5087801342','rockjohnson','password789','Address 3'),(104,'Lester','Green','lestergreen@gmail.com','5072983134','beetlejuice','password101','Address 4'),(105,'Michael','Keaton','keatMichy@gmail.com','5090909999','manperson','password233','Address 4'),(106,'Eric','Andre','liverpool@gmail.com','5082990002','nightmare','password121','Address 5'),(107,'Aubrey','Graham','simple@gmail.com','5078886776','thefunnyfella','password345','Address 6'),(108,'Jennifer','Lee','jenniferlee@gmail.com','5070912877','jennylee','password336','Address 7'),(109,'Robert','Johnson','robertjohnson@yahoo.com','5091222121','robjohn','password129','Address 8'),(101,'John','Doe','johndoey@yahoo.com','5098001212','johndoe','password123','Address 1'),(102,'Jane','Smith','janesmith@gmail.com','5107803492','janesmith','password456','Address 2'),(103,'Dwayne','Johnson','ledwayne@yahoo.com','5087801342','rockjohnson','password789','Address 3'),(104,'Lester','Green','lestergreen@gmail.com','5072983134','beetlejuice','password101','Address 4'),(105,'Michael','Keaton','keatMichy@gmail.com','5090909999','manperson','password233','Address 4'),(106,'Eric','Andre','liverpool@gmail.com','5082990002','nightmare','password121','Address 5'),(107,'Aubrey','Graham','simple@gmail.com','5078886776','thefunnyfella','password345','Address 6'),(108,'Jennifer','Lee','jenniferlee@gmail.com','5070912877','jennylee','password336','Address 7'),(109,'Robert','Johnson','robertjohnson@yahoo.com','5091222121','robjohn','password129','Address 8');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `driverId` int NOT NULL,
  `passengerId` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  `comment` varchar(166) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (101,107,5,'06-17-2021','Silly driver!'),(102,105,1,'07-20-2022','He can do better'),(103,104,2,'12-24-2020','Smelled like a used car'),(104,108,4,'03-05-2023','Great driver!'),(101,107,5,'06-17-2021','Silly driver!'),(102,105,1,'07-20-2022','He can do better'),(103,104,2,'12-24-2020','Smelled like a used car'),(104,108,4,'03-05-2023','Great driver!'),(104,109,4,'03-05-2023','Smells Strange, but was Fun!'),(105,109,5,'04-10-2023','Very friendly and punctual.'),(101,107,5,'06-19-2021','Silly driver!'),(102,105,1,'07-21-2022','He can do better'),(103,104,2,'02-14-2021','Smelled like a used car'),(104,108,4,'03-05-2023','Great driver!'),(104,109,4,'03-05-2023','Smells Strange, but was Fun!'),(105,109,5,'04-10-2023','Very friendly and punctual.');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-06  2:23:57
