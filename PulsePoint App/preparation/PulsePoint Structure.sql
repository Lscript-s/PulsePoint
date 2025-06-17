CREATE DATABASE  IF NOT EXISTS `pulsepoint` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pulsepoint`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: pulsepoint
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `current_medical_condition`
--

DROP TABLE IF EXISTS `current_medical_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `current_medical_condition` (
  `medical_condition_id` int NOT NULL AUTO_INCREMENT,
  `examinee_id` char(10) NOT NULL,
  `medical_condition` varchar(50) NOT NULL,
  `condition_identified_on` date DEFAULT NULL,
  `maintenance_medication` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`medical_condition_id`),
  KEY `medical_condition_examinee_id_idx` (`examinee_id`),
  CONSTRAINT `medical_condition_examinee_id` FOREIGN KEY (`examinee_id`) REFERENCES `examinee` (`examinee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2723 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examinee`
--

DROP TABLE IF EXISTS `examinee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examinee` (
  `examinee_id` char(10) NOT NULL,
  `year_of_exam` year NOT NULL,
  `date_of_exam` date NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `middle_initial` char(1) DEFAULT NULL,
  `age` int NOT NULL,
  `sex` char(1) NOT NULL,
  `birthdate` date NOT NULL,
  `civil_status` char(1) NOT NULL,
  `division` varchar(150) NOT NULL,
  `role` varchar(8) NOT NULL,
  `mobile_number` char(11) NOT NULL,
  `network` varchar(20) NOT NULL,
  `address_in_miagao` varchar(100) DEFAULT NULL,
  `landlord_name` varchar(50) DEFAULT NULL,
  `landlord_contact_number` varchar(11) DEFAULT NULL,
  `guardian_name` varchar(100) DEFAULT NULL,
  `guardian_address` varchar(150) DEFAULT NULL,
  `guardian_relation` varchar(30) DEFAULT NULL,
  `guardian_mobile_number` varchar(11) DEFAULT NULL,
  `guardian_network` varchar(20) DEFAULT NULL,
  `family_history_illness` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`examinee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `immunization_background`
--

DROP TABLE IF EXISTS `immunization_background`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immunization_background` (
  `vaccine_id` int NOT NULL AUTO_INCREMENT,
  `examinee_id` char(10) NOT NULL,
  `vaccine_name` varchar(50) NOT NULL,
  `vaccine_given_date` date DEFAULT NULL,
  `vaccine_dose` varchar(15) NOT NULL,
  `vaccine_remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`vaccine_id`),
  KEY `immunization_background_examinee_id_idx` (`examinee_id`),
  CONSTRAINT `immunization_background_examinee_id` FOREIGN KEY (`examinee_id`) REFERENCES `examinee` (`examinee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5393 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-17 23:18:19
