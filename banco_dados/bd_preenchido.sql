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
  `id` int NOT NULL AUTO_INCREMENT,
  `dia` date NOT NULL,
  `idFuncionario` int NOT NULL,
  `idCliente` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idFuncionario` (`idFuncionario`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `agendamentos_ibfk_1` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionarios` (`id`),
  CONSTRAINT `agendamentos_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agendamentos`
--

LOCK TABLES `agendamentos` WRITE;
/*!40000 ALTER TABLE `agendamentos` DISABLE KEYS */;
INSERT INTO `agendamentos` VALUES (1,'2024-06-13',3,3),(2,'2024-07-22',3,5),(3,'2024-06-18',2,2),(4,'2024-06-14',1,1),(5,'2024-06-15',2,2),(6,'2024-06-16',3,3),(7,'2024-06-17',4,4),(8,'2024-06-18',5,5),(9,'2024-06-19',1,6),(10,'2024-06-20',2,7),(11,'2024-06-21',3,8),(12,'2024-06-22',4,9),(13,'2024-06-23',5,10),(14,'2024-06-24',1,11);
/*!40000 ALTER TABLE `agendamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacoes`
--

DROP TABLE IF EXISTS `avaliacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sexo` enum('M','F') NOT NULL,
  `idade` int NOT NULL,
  `peso` double NOT NULL,
  `altura` double NOT NULL,
  `IMC` double DEFAULT NULL,
  `TMB` double DEFAULT NULL,
  `idAgendamento` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `avaliacoes_ibfk_1` (`idAgendamento`),
  CONSTRAINT `avaliacoes_ibfk_1` FOREIGN KEY (`idAgendamento`) REFERENCES `agendamentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacoes`
--

LOCK TABLES `avaliacoes` WRITE;
/*!40000 ALTER TABLE `avaliacoes` DISABLE KEYS */;
INSERT INTO `avaliacoes` VALUES (2,'M',21,80,1.76,NULL,NULL,1),(3,'F',30,60.5,1.65,22.2,1400,1),(4,'M',25,80,1.75,26.1,1800,2),(5,'F',28,55,1.6,21.5,1350,3),(6,'M',32,90,1.8,27.8,1900,4),(7,'F',26,65,1.7,22.5,1450,5),(8,'M',29,85,1.78,26.8,1850,6),(9,'F',24,62,1.68,22,1420,7),(10,'M',27,88,1.82,26.6,1875,8),(11,'F',31,58,1.62,22.1,1380,9),(12,'M',34,92,1.85,26.9,1920,10),(13,'F',22,64,1.66,23.2,1440,11);
/*!40000 ALTER TABLE `avaliacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartoescredito`
--

DROP TABLE IF EXISTS `cartoescredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartoescredito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `numero` varchar(20) NOT NULL,
  `nomeTitular` varchar(100) NOT NULL,
  `dataValidade` varchar(7) NOT NULL,
  `cvv` varchar(4) NOT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `cartoescredito_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartoescredito`
--

LOCK TABLES `cartoescredito` WRITE;
/*!40000 ALTER TABLE `cartoescredito` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartoescredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `senha` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `pagamento` enum('PENDENTE','PAGO','ATRASADO') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Jose Maria','123456','josemara@gmail.com','PAGO'),(2,'GABRIELLE','123456','gabrielle@gmail.com','PAGO'),(3,'martins','1232','martis@gmail.com','ATRASADO'),(4,'mario','34234','mario2@gmail.com','PENDENTE'),(5,'pedro','12433124','pedro@gmail.com','PAGO'),(6,'maria Fatima','mariazinha','fatimaM@gmail.com','ATRASADO'),(7,'Gabrielle Soares','helloworld2','so251106@gmail.com','PAGO'),(8,'Alice Carvalho','senha123','alice.carvalho@example.com','PAGO'),(9,'Bruno Almeida','senha456','bruno.almeida@example.com','ATRASADO'),(10,'Carla Silva','senha789','carla.silva@example.com','PENDENTE'),(11,'Daniel Ferreira','senha321','daniel.ferreira@example.com','PAGO'),(12,'Eduarda Costa','senha654','eduarda.costa@example.com','ATRASADO'),(13,'Felipe Souza','senha987','felipe.souza@example.com','PENDENTE'),(14,'Gabriela Lima','senha741','gabriela.lima@example.com','PAGO'),(15,'Henrique Oliveira','senha852','henrique.oliveira@example.com','ATRASADO'),(16,'Isabela Rocha','senha963','isabela.rocha@example.com','PENDENTE'),(17,'Júlio Santos','senha159','julio.santos@example.com','PAGO'),(18,'Karina Barbosa','senha753','karina.barbosa@example.com','PAGO'),(19,'Leonardo Martins','senha357','leonardo.martins@example.com','ATRASADO'),(20,'Mariana Vieira','senha951','mariana.vieira@example.com','PENDENTE'),(21,'Nicolas Gomes','senha456','nicolas.gomes@example.com','PAGO'),(22,'Olivia Ribeiro','senha852','olivia.ribeiro@example.com','ATRASADO'),(23,'Paulo Souza','senha753','paulo.souza@example.com','PENDENTE'),(24,'Renata Almeida','senha357','renata.almeida@example.com','PAGO'),(25,'Sergio Castro','senha951','sergio.castro@example.com','ATRASADO'),(26,'Tatiana Costa','senha456','tatiana.costa@example.com','PENDENTE'),(27,'Vitor Moreira','senha852','vitor.moreira@example.com','PAGO');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercicios`
--

DROP TABLE IF EXISTS `exercicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercicios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` text,
  `musculo` varchar(45) DEFAULT NULL,
  `categoria` enum('A','B','C') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercicios`
--

LOCK TABLES `exercicios` WRITE;
/*!40000 ALTER TABLE `exercicios` DISABLE KEYS */;
INSERT INTO `exercicios` VALUES (1,'Supino Reto','3x12','Tórax','A'),(2,'Supino Inclinado','3x12','Tórax','A'),(3,'Crucifixo','3x15','Tórax','A'),(4,'Desenvolvimento com Halteres','3x12','Ombro','A'),(5,'Elevação Lateral','3x15','Ombro','A'),(6,'Desenvolvimento Militar','3x12','Ombro','A'),(7,'Tríceps Pulley','3x15','Tríceps','A'),(8,'Tríceps Francês','3x12','Tríceps','A'),(9,'Mergulho no Banco','3x15','Tríceps','A'),(10,'Puxada Alta','3x12','Costas','B'),(11,'Remada Baixa','3x12','Costas','B'),(12,'Remada Curvada','3x15','Costas','B'),(13,'Abdominal na Máquina','3x20','Abdômen','B'),(14,'Prancha','3x1min','Abdômen','B'),(15,'Elevação de Pernas','3x15','Abdômen','B'),(16,'Rosca Direta','3x12','Bíceps','B'),(17,'Rosca Martelo','3x12','Bíceps','B'),(18,'Rosca Concentrada','3x15','Bíceps','B'),(19,'Agachamento Livre','3x12','Pernas','C'),(20,'Leg Press','3x15','Pernas','C'),(21,'Cadeira Extensora','3x15','Pernas','C'),(22,'Cadeira Flexora','3x15','Pernas','C'),(23,'Levantamento Terra','3x12','Parte Inferior','C'),(24,'Stiff com Halteres','3x12','Parte Inferior','C'),(25,'Elevação de Quadril','3x15','Glúteos','C'),(26,'Afundo','3x12 (cada perna)','Glúteos','C'),(27,'Glúteo na Polia','3x15','Glúteos','C');
/*!40000 ALTER TABLE `exercicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exerciciosfichacliente`
--

DROP TABLE IF EXISTS `exerciciosfichacliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exerciciosfichacliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idFichaCliente` int NOT NULL,
  `idExercicio` int NOT NULL,
  `series` int NOT NULL,
  `repeticoes` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idFichaCliente` (`idFichaCliente`),
  KEY `idExercicio` (`idExercicio`),
  CONSTRAINT `exerciciosfichacliente_ibfk_1` FOREIGN KEY (`idFichaCliente`) REFERENCES `fichascliente` (`id`),
  CONSTRAINT `exerciciosfichacliente_ibfk_2` FOREIGN KEY (`idExercicio`) REFERENCES `exercicios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exerciciosfichacliente`
--

LOCK TABLES `exerciciosfichacliente` WRITE;
/*!40000 ALTER TABLE `exerciciosfichacliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `exerciciosfichacliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fichascliente`
--

DROP TABLE IF EXISTS `fichascliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fichascliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `data` date NOT NULL,
  `notas` text,
  PRIMARY KEY (`id`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `fichascliente_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fichascliente`
--

LOCK TABLES `fichascliente` WRITE;
/*!40000 ALTER TABLE `fichascliente` DISABLE KEYS */;
INSERT INTO `fichascliente` VALUES (1,1,'2024-06-14','Primeiro treino de Alice Carvalho'),(2,2,'2024-06-15','Primeiro treino de Bruno Almeida'),(3,3,'2024-06-16','Primeiro treino de Carla Silva'),(4,4,'2024-06-17','Primeiro treino de Daniel Ferreira'),(5,5,'2024-06-18','Primeiro treino de Eduarda Costa'),(6,6,'2024-06-19','Primeiro treino de Felipe Souza'),(7,7,'2024-06-20','Primeiro treino de Gabriela Lima'),(8,8,'2024-06-21','Primeiro treino de Henrique Oliveira'),(9,9,'2024-06-22','Primeiro treino de Isabela Rocha'),(10,10,'2024-06-23','Primeiro treino de Júlio Santos'),(11,11,'2024-06-24','Primeiro treino de Gabrielle Soares');
/*!40000 ALTER TABLE `fichascliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
INSERT INTO `funcionarios` VALUES (1,'Rosimar','124.567.897-12','123456789','rosimar@gmail.com'),(2,'Maria','233.434.233-12','mariazinha123','maria@gmail.com'),(3,'Jose','152.741.816-35','jose123','jose@gmail.com'),(4,'João Silva','12345678901','senha123','joao.silva@example.com'),(5,'Maria Oliveira','23456789012','senha456','maria.oliveira@example.com'),(6,'Carlos Pereira','34567890123','senha789','carlos.pereira@example.com'),(7,'Ana Souza','45678901234','senha321','ana.souza@example.com'),(8,'Fernanda Lima','56789012345','senha654','fernanda.lima@example.com'),(9,'Pedro Santos','67890123456','senha987','pedro.santos@example.com'),(10,'Lucas Costa','78901234567','senha741','lucas.costa@example.com'),(11,'Juliana Martins','44556677889','senha456','juliana.martins@example.com'),(12,'Gabriel Lima','55667788990','senha852','gabriel.lima@example.com');
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
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

-- Dump completed on 2024-06-13 17:02:35
