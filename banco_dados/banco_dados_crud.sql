-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: crud
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `agendamentos`
--

DROP TABLE IF EXISTS `agendamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agendamentos` (
  `idAgendamento` int NOT NULL AUTO_INCREMENT,
  `data_` date NOT NULL,
  `dataCancelada` date DEFAULT NULL,
  `idFuncionario` int DEFAULT NULL,
  `idCliente` int DEFAULT NULL,
  PRIMARY KEY (`idAgendamento`),
  KEY `idFuncionario` (`idFuncionario`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `agendamentos_ibfk_1` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionarios` (`id`),
  CONSTRAINT `agendamentos_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agendamentos`
--

LOCK TABLES `agendamentos` WRITE;
/*!40000 ALTER TABLE `agendamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `agendamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacoes`
--

DROP TABLE IF EXISTS `avaliacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacoes` (
  `idAvaliacao` int NOT NULL AUTO_INCREMENT,
  `qntdMassaMuscular` decimal(10,2) NOT NULL,
  `IMC` decimal(10,2) NOT NULL,
  `peso` decimal(10,2) NOT NULL,
  `altura` decimal(10,2) NOT NULL,
  `idade` int NOT NULL,
  `percentualGordura` decimal(10,2) NOT NULL,
  `idAgendamento` int DEFAULT NULL,
  PRIMARY KEY (`idAvaliacao`),
  KEY `idAgendamento` (`idAgendamento`),
  CONSTRAINT `avaliacoes_ibfk_1` FOREIGN KEY (`idAgendamento`) REFERENCES `agendamentos` (`idAgendamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacoes`
--

LOCK TABLES `avaliacoes` WRITE;
/*!40000 ALTER TABLE `avaliacoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `pagamento` enum('PENDENTE','PAGO','ATRASADO') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fichas`
--

DROP TABLE IF EXISTS `fichas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fichas` (
  `idFicha` int NOT NULL AUTO_INCREMENT,
  `nomeAluno` varchar(255) NOT NULL,
  `nomeTreinador` varchar(255) NOT NULL,
  `pesoInicial` decimal(10,2) NOT NULL,
  `dataCriacao` date NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `exerciciosInferiores` varchar(255) NOT NULL,
  `exerciciosPosteriores` varchar(255) NOT NULL,
  `exerciciosSuperiores` varchar(255) NOT NULL,
  `exerciciosCore` varchar(255) NOT NULL,
  `idCliente` int DEFAULT NULL,
  PRIMARY KEY (`idFicha`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `fichas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fichas`
--

LOCK TABLES `fichas` WRITE;
/*!40000 ALTER TABLE `fichas` DISABLE KEYS */;
/*!40000 ALTER TABLE `fichas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamentocredito`
--

DROP TABLE IF EXISTS `pagamentocredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamentocredito` (
  `idPagamentoCredito` int NOT NULL AUTO_INCREMENT,
  `nomeTitular` varchar(255) NOT NULL,
  `numeroCartao` bigint NOT NULL,
  `CVV` int NOT NULL,
  `validade` date NOT NULL,
  `idCliente` int DEFAULT NULL,
  PRIMARY KEY (`idPagamentoCredito`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `pagamentocredito_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamentocredito`
--

LOCK TABLES `pagamentocredito` WRITE;
/*!40000 ALTER TABLE `pagamentocredito` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamentocredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'crud'
--

--
-- Dumping routines for database 'crud'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-10 19:51:56
