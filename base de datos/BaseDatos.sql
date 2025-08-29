-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: api
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `clienteid` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `personaid` bigint DEFAULT NULL,
  PRIMARY KEY (`clienteid`),
  KEY `FKlx9olb9sc6g1jmi2ltpv030y6` (`personaid`),
  CONSTRAINT `FKlx9olb9sc6g1jmi2ltpv030y6` FOREIGN KEY (`personaid`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'1234','True',1),(2,'5678','True',2);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_inicial` decimal(19,2) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `clienteid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpj7ncg765kt4klndu25bwbwe4` (`numero_cuenta`),
  KEY `FKtiah1fi6lm797bbc6bkex91ie` (`clienteid`),
  CONSTRAINT `FKtiah1fi6lm797bbc6bkex91ie` FOREIGN KEY (`clienteid`) REFERENCES `cliente` (`clienteid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'True','478758',2000.00,'Ahorro',1),(6,'True','2254871',1000.00,'Corriente',2);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_disponible` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk10u787s9re28fue9gdscb5kt` (`numero_cuenta`),
  CONSTRAINT `FKk10u787s9re28fue9gdscb5kt` FOREIGN KEY (`numero_cuenta`) REFERENCES `cuenta` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES (1,'2025-08-28',9000,'Retiro de 575',9000,'478758',NULL),(3,'2025-08-28',2000,'Retiro de 575',-575,'478758',2575),(4,'2025-08-28',1000,'Retiro de 575',-575,'478758',1575),(5,'2025-08-28',1,'Retiro de 575',575,'478758',-574),(6,'2025-08-28',1,'Retiro de 575',-575,'478758',576),(7,'2025-08-28',1,'Retiro de 575',-575,'478758',576),(8,'2025-08-28',1,'Retiro de 575',-575,'478758',-574),(9,'2025-08-28',10,'Retiro de 575',575,'478758',585),(10,'2025-08-28',10,'Retiro de 575',-575,'478758',-565),(11,'2025-08-28',10,'Retiro de 575',-575,'478758',-565),(12,'2025-08-28',0,'Retiro de 575',-575,'478758',-575),(13,'2025-08-28',100,'Retiro de 575',-575,'478758',-475),(14,'2025-08-28',1,'Retiro de 575',575,'478758',-574),(15,'2025-08-29',100,'Retiro de 575',-575,'478758',-475),(16,'2025-08-29',1,'Retiro de 575',-575,'478758',-574);
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento_model`
--

DROP TABLE IF EXISTS `movimiento_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento_model` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `saldo` double NOT NULL,
  `tipo_movimiento` varchar(50) NOT NULL,
  `valor` double NOT NULL,
  `cuenta_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmnjbwuoafjh9h4ga31xrkx20c` (`cuenta_id`),
  CONSTRAINT `FKmnjbwuoafjh9h4ga31xrkx20c` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_model`
--

LOCK TABLES `movimiento_model` WRITE;
/*!40000 ALTER TABLE `movimiento_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Otavalo sn y principal','20','Masculino','098254785','Jose Lema','098254785'),(2,'Amazonas y NNUU','16','Femenino','097548965','Marianela Montalvo','097548965');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2025-08-29  9:59:54
