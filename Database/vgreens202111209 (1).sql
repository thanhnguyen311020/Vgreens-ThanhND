CREATE DATABASE  IF NOT EXISTS `vgreens` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vgreens`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: vgreens
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `username` varchar(255) NOT NULL,
  `address_line` varchar(64) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enable` bit(1) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_changed_time` datetime DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `postal_code` varchar(10) DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `state` varchar(45) NOT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK943irua9hqdyxtlc3v3sm2yox` (`country_id`),
  CONSTRAINT `FK943irua9hqdyxtlc3v3sm2yox` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('ahopcustomer1','348 trần tống',NULL,'ahopcustomer1@gmail.com',_binary '','A','Hop','$2a$10$4iR/myAcw.t35micC9oPGO8bahxi84N1FBPJOU7v1IYII6vVpmdnm',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1639028505/pb3niujm0g7iwjn5pubm.jpg','500000','2021-12-09',NULL,'Hoàng Sa',NULL,1),('hieucustomer1','348 trần tống',NULL,'hieucustomer1@gmail.com',_binary '','Dang','Hieu','$2a$10$KofgFeMd8ggQDDw9EdXxueU7tRChRbxwQwBhoVB3O7Yzth43qPdXC',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1639032559/u642vw3iphwd9mg9mzes.jpg','500000','2021-12-09',NULL,'Sơn Trà',NULL,1),('hieushipper','87 hồ nghinh',NULL,'hieushipper@gmail.com',_binary '','Hieu','Dang','$2a$10$9xjGdHR/yOv3YK3czPwLVOhlyN0VF4UpG8r3typ1DbdPLQkv.nW2a',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1639027244/t8rusrqiwc8ay2l9fzv4.jpg',NULL,'2021-12-09',NULL,'Ngũ Hành Sơn',NULL,1),('hopshipper','01 nghi ninh , tp đà nẵng',NULL,'hopshipper@gmail.com',_binary '','A','Hop','$2a$10$bMEfPVSzbTT.dUbKV7LJgOaQse5zkag5ffnEupoMDJF/JRDNqMVC2',NULL,'0378795129','','500000','2021-12-09',NULL,'Thanh Khê',NULL,1),('lancustomer1','01 nghi ninh , tp đà nẵng',NULL,'lancustomer1r@gmail.com',_binary '','lan','nguyen','$2a$10$k9q9PR7zIE/gPA0lpEVbl.SMhbplHFEKOahvlPqhJMIQLz/mwpgE2',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1639031730/prq36bc8eilxb48au6lx.jpg','500000','2021-12-09',NULL,'Thanh Khê',NULL,1),('lanshipper','08 hà văn tính',NULL,'lanshipper@gmail.com',_binary '','Lan','Nguyen','$2a$10$xYVqaYuheO2PKsyqaRe6vu4SKdGLaBXAJFuRihsZkn8jEkGz69/aG',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1638971362/rmtpjanugar5qwnw9ixl.png','01378','2021-12-08',NULL,'Hải Châu',NULL,1),('lanvunguyen1101@gmail.com','',NULL,'lanvunguyen1101@gmail.com',_binary '','Lan',' Nguyen\n','17d9e06fcf7',NULL,'','','','2021-12-09',NULL,'','',1),('tancustomer1','348 trần tống',NULL,'tancustomer1@gmail.com',_binary '','Nguyen','Tan','$2a$10$rRCOv0hVCeOlytkeVmp5POLzF7Ysc.7RHxzT97.LcGH67upziNGh2',NULL,'0378795129','','500000','2021-12-09',NULL,'Thanh Khê',NULL,1),('tanshipper','01 nghi ninh , tp đà nẵng',NULL,'tanshipper@gmail.com',_binary '','Nguyen','Tan','$2a$10$4bhfbILp077a4vp7h9bEIOcvWMGDeVIo9YppfBFFgobUguIqOm0X2',NULL,'0378795129','','500000','2021-12-09',NULL,'Thanh Khê',NULL,1),('thanhcustomer1','348 nguyễn văn linh',NULL,'thanhcustomer1@gmail.com',_binary '','Nguyen','Thanh','$2a$10$FN4tSv90BVm/st2ompM5KOf/xYoQk9ifOa53.jR4E/xztXBsrPLFW',NULL,'0378795129','','500000','2021-12-09',NULL,'Hòa Vang',NULL,1),('thanhshipper','01 nghi ninh , tp đà nẵng',NULL,'thanhshipper@gmail.com',_binary '','Thanh','Nguyen','$2a$10$JcC7f0RfpEqkzu9s0f7GBeNRcg3kCJxTExe8j1ueG7i.1fVmWFwyy',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1639027370/l4garcwmycmit3sexjiv.jpg','500000','2021-12-09',NULL,'Thanh Khê',NULL,1),('thaoshipper','01 nghi ninh , tp đà nẵng',NULL,'thaoshipper@gmail.com',_binary '','Ngo','Thao','$2a$10$cSg9r/5hjSCn4Vk2d2NRf.MXnE65tkRxhlx6fBLG6eiwZf/4XSDAO',NULL,'0378795129','','500000','2021-12-09',NULL,'Thanh Khê',NULL,1),('vgreensadmin','08 hà văn tính',NULL,'vgreens@gmail.com',_binary '','VGreens','Admin','$2a$10$NdtSTofdTq.t8MvwXjdaxugaIuYSJTF5Rfo3GrfvaIFT1/VNfuXem',NULL,'0378795129','https://res.cloudinary.com/lancloudshop/image/upload/v1638966318/kct75vyhg1ahmfypurzq.jpg',NULL,'2021-12-08',NULL,'Hòa Vang',NULL,1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_default` bit(1) DEFAULT NULL,
  `address_line` varchar(64) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `state` varchar(45) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKemqwcfdcscn97yvng32k5dc14` (`username`),
  KEY `FKn3sth7s3kur1rafwbbrqqnswt` (`country_id`),
  CONSTRAINT `FKemqwcfdcscn97yvng32k5dc14` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`),
  CONSTRAINT `FKn3sth7s3kur1rafwbbrqqnswt` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `roleid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKhtk0tjw6uf67w0vkhy5n3j1oq` (`username`,`roleid`),
  KEY `FKevi9708lm1k06pa4dugc91k7v` (`roleid`),
  CONSTRAINT `FKevi9708lm1k06pa4dugc91k7v` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKlw0sapcjvuxfa1kvyjw7jml88` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (14,'ahopcustomer1','CUS'),(15,'hieucustomer1','CUS'),(6,'hieushipper','SHIP'),(7,'hopshipper','SHIP'),(11,'lancustomer1','CUS'),(5,'lanshipper','SHIP'),(19,'lanvunguyen1101@gmail.com','CUS'),(12,'tancustomer1','CUS'),(8,'tanshipper','SHIP'),(13,'thanhcustomer1','CUS'),(9,'thanhshipper','SHIP'),(10,'thaoshipper','SHIP'),(3,'vgreensadmin','CUS'),(4,'vgreensadmin','DIRE'),(17,'vgreensadmin','SHIP'),(16,'vgreensadmin','STAF');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'https://res.cloudinary.com/lancloudshop/image/upload/v1638967440/vzgmtxff7wrjeifbzbwy.png','Rau củ'),(2,'https://res.cloudinary.com/lancloudshop/image/upload/v1638967465/hzcrp427mx6sbce7eygq.png','Hải sản'),(3,'https://res.cloudinary.com/lancloudshop/image/upload/v1638967486/z1jstmekq6delbpukwgv.png','Thịt Nướng'),(4,'https://res.cloudinary.com/lancloudshop/image/upload/v1638967510/incz3qhkfewc4bjf5adv.png','Trái Cây'),(5,'https://res.cloudinary.com/lancloudshop/image/upload/v1638967524/mqanoctm0zj8sfeml4xp.png','Đồ Khô'),(6,'https://res.cloudinary.com/lancloudshop/image/upload/v1638967538/iycyzyyybep0k7q4i3z2.png','Đồ Uống');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consignments`
--

DROP TABLE IF EXISTS `consignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consignments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_time` datetime NOT NULL,
  `current_quantity` int DEFAULT NULL,
  `expiry_time` datetime NOT NULL,
  `name` varchar(100) NOT NULL,
  `number_of_inputs` int DEFAULT NULL,
  `number_of_returns` int DEFAULT NULL,
  `number_of_sales` int DEFAULT NULL,
  `purchase_price` float DEFAULT NULL,
  `selling_price` float DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `supplier_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKca10sd1rdesmtx5w3rqiqgohh` (`supplier_id`),
  CONSTRAINT `FKca10sd1rdesmtx5w3rqiqgohh` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consignments`
--

LOCK TABLES `consignments` WRITE;
/*!40000 ALTER TABLE `consignments` DISABLE KEYS */;
INSERT INTO `consignments` VALUES (1,'2021-12-08 19:31:00',109,'2021-12-11 19:31:00','LH01',123,0,15,12000,13000,_binary '','2021-12-08 19:31:00',4),(2,'2021-12-08 19:32:00',34,'2021-12-15 19:32:00','Lô hàng Nho',34,0,3,34000,40000,_binary '','2021-12-08 19:32:00',5),(3,'2021-12-08 19:33:00',45,'2021-12-15 19:33:00','Lô hàng thực phẩm',45,0,0,12000,25000,_binary '','2021-12-08 19:33:00',5),(4,'2021-12-08 19:58:00',13,'2021-12-11 19:58:00','Lô Ổi',13,0,0,15000,16000,_binary '','2021-12-08 19:58:00',4),(5,'2021-12-08 19:58:00',34,'2021-12-11 19:59:00','Lô Bơ',34,0,0,22000,30000,_binary '','2021-12-08 19:59:00',4),(6,'2021-12-08 20:00:00',74,'2021-12-22 20:00:00','Lô Bưởi',74,0,0,35000,40000,_binary '','2021-12-15 20:00:00',5),(7,'2021-12-08 20:00:00',42,'2021-12-22 20:00:00','Lô Nhãn',42,0,0,37000,40000,_binary '','2021-12-15 20:00:00',4),(8,'2021-12-08 20:02:00',145,'2021-12-31 20:02:00','Lô Thanh Long',145,0,0,42000,45000,_binary '','2021-12-08 20:02:00',4),(9,'2021-12-08 21:32:00',123,'2021-12-25 21:32:00','Lô Thơm',123,0,0,14500,16000,_binary '','2021-12-08 21:32:00',7),(10,'2021-12-08 21:33:00',43,'2021-12-15 21:33:00','Lô Mít',43,0,0,17000,19000,_binary '','2021-12-08 21:33:00',8),(11,'2021-12-08 21:34:00',23,'2021-12-25 21:34:00','Lô Kiwi',23,0,0,34000,40000,_binary '','2021-12-08 21:34:00',9),(12,'2021-12-08 21:40:00',50,'2021-12-18 21:40:00','Lô Tôm',50,0,0,57000,78000,_binary '','2021-12-08 21:40:00',10),(13,'2021-12-08 21:40:00',22,'2021-12-25 21:40:00','Lô Than Bò Mỹ',22,0,0,100000,130000,_binary '','2021-12-08 21:40:00',11),(14,'2021-12-08 21:45:00',14,'2021-12-25 21:45:00','Lô Thị Ba Chỉ Bò Mỹ',14,0,0,55000,80000,_binary '','2021-12-08 21:45:00',11),(15,'2021-12-08 21:47:00',46,'2021-12-25 21:47:00','Thịt Bò Nạt Vai',46,0,0,100000,150000,_binary '','2021-12-08 21:47:00',14),(16,'2021-12-08 21:54:00',33,'2021-12-25 21:54:00','Cá Ánh Vũ Tiên',33,0,0,67000,69000,_binary '','2021-12-08 21:54:00',14),(17,'2021-12-08 21:55:00',33,'2021-12-25 21:55:00','Lô Cá Ba Sa',35,0,3,89000,100000,_binary '','2021-12-08 21:55:00',11),(18,'2021-12-08 21:56:00',12,'2021-12-25 21:56:00','Lô cá trác vàng',12,0,0,300000,350000,_binary '','2021-12-08 21:56:00',14),(19,'2021-12-08 21:58:00',46,'2021-12-18 21:58:00','Lô Gẩu Bò',46,0,0,460000,470000,_binary '','2021-12-08 21:58:00',13),(20,'2021-12-08 21:59:00',100,'2021-12-25 21:59:00','Lô khô bò',100,0,0,56000,56000,_binary '','2021-12-08 21:59:00',14),(21,'2021-12-08 21:59:00',90,'2021-12-25 21:59:00','Lô khô gà',90,0,1,56000,60000,_binary '','2021-12-08 21:59:00',11),(22,'2021-12-08 21:59:00',299,'2021-12-25 21:59:00','Lô bánh tráng',300,0,5,56000,60000,_binary '','2021-12-08 21:59:00',11),(23,'2021-12-08 21:59:00',300,'2021-12-25 21:59:00','Lô Bột Bạt Hà',300,0,0,100000,110000,_binary '','2021-12-08 21:59:00',11),(24,'2021-12-08 21:59:00',299,'2021-12-25 21:59:00','Lô Bột Cà rốt',300,0,2,100000,110000,_binary '','2021-12-08 21:59:00',11),(25,'2021-12-08 21:59:00',200,'2021-12-25 21:59:00','Lô bổ cỏ lúa mì',200,0,1,100000,135000,_binary '','2021-12-08 21:59:00',14),(26,'2021-12-08 21:59:00',47,'2021-12-25 21:59:00','Lô bột lá sen',47,0,0,100000,135000,_binary '','2021-12-08 21:59:00',13),(27,'2021-12-08 21:59:00',47,'2021-12-25 21:59:00','Lô bột tía tô',47,0,1,100000,360000,_binary '','2021-12-08 21:59:00',10),(28,'2021-12-08 21:59:00',46,'2021-12-25 21:59:00','Lô bún gác thiên nhiên',47,0,2,50000,70000,_binary '','2021-12-08 21:59:00',10),(29,'2021-12-08 21:59:00',97,'2021-12-25 21:59:00','Lô cơm cá',99,0,3,50000,70000,_binary '','2021-12-08 21:59:00',15),(30,'2021-12-08 21:59:00',132,'2021-12-25 21:59:00','Lô cơm cháy',133,0,2,60000,75000,_binary '','2021-12-08 21:59:00',12),(31,'2021-12-08 21:59:00',133,'2021-12-25 21:59:00','Lô chó đỏ',133,0,0,62000,76000,_binary '','2021-12-08 21:59:00',8),(32,'2021-12-08 21:59:00',162,'2021-12-25 21:59:00','Lô đậu lăng',162,0,1,62000,79000,_binary '','2021-12-08 21:59:00',11),(33,'2021-12-08 21:59:00',162,'2021-12-25 21:59:00','Lô đậu gà',162,0,1,62000,79000,_binary '','2021-12-08 21:59:00',11),(34,'2021-12-08 21:59:00',162,'2021-12-25 21:59:00','Lô hạt kê',162,0,1,62000,80000,_binary '','2021-12-08 21:59:00',11),(35,'2021-12-08 21:59:00',162,'2021-12-25 21:59:00','Lô hạt dẻ',162,0,1,62000,81000,_binary '','2021-12-08 21:59:00',11),(36,'2021-12-08 21:59:00',162,'2021-12-25 21:59:00','Lô hoa đu đủ đực khô',162,0,0,62000,82000,_binary '','2021-12-08 21:59:00',11),(37,'2021-12-08 21:59:00',16,'2021-12-25 21:59:00','Lô lá chanh sấy khô',16,0,1,62000,83000,_binary '','2021-12-08 21:59:00',8),(38,'2021-12-08 22:44:00',45,'2021-12-25 22:44:00','Lô Mướp Hương',45,0,1,3700,5000,_binary '','2021-12-08 22:44:00',13),(39,'2021-12-08 22:44:00',45,'2021-12-25 22:44:00','Lô cải bó xôi',45,0,0,3700,7000,_binary '','2021-12-08 22:44:00',13),(41,'2021-12-08 22:44:00',47,'2021-12-25 22:44:00','Lô cải ngóng',47,0,0,3700,9000,_binary '','2021-12-08 22:44:00',5),(42,'2021-12-08 22:50:00',14,'2021-12-18 22:50:00','Lô đậu trạch',14,0,0,6000,9000,_binary '','2021-12-08 22:50:00',8),(43,'2021-12-08 22:50:00',14,'2021-12-18 22:50:00','Lô rau dền',14,0,0,6000,9000,_binary '','2021-12-08 22:50:00',8),(44,'2021-12-08 22:50:00',14,'2021-12-18 22:50:00','Lô rau hữu cơ PGS',14,0,0,6000,10000,_binary '','2021-12-08 22:50:00',8),(45,'2021-12-08 22:50:00',14,'2021-12-18 22:50:00','Lô su hào',14,0,0,6000,10000,_binary '','2021-12-08 22:50:00',8),(46,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô bắp cải RA',34,0,0,34000,35000,_binary '','2021-12-08 23:03:00',13),(47,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô khoai tây RA',34,0,1,34000,35000,_binary '','2021-12-08 23:03:00',13),(48,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô bí xanh',34,0,1,34000,35000,_binary '','2021-12-08 23:03:00',13),(49,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô cà rốt',34,0,0,34000,42000,_binary '','2021-12-08 23:03:00',13),(50,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô củ cải trắng RA',34,0,0,29000,32000,_binary '','2021-12-08 23:03:00',13),(51,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô rau ngót',34,0,0,29000,49000,_binary '','2021-12-08 23:03:00',13),(52,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô hàng lá',34,0,0,29000,55000,_binary '','2021-12-08 23:03:00',9),(53,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô cả xanh',34,0,1,29000,37000,_binary '','2021-12-08 23:03:00',9),(54,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô xà lách RA',34,0,0,55000,65000,_binary '','2021-12-08 23:03:00',9),(55,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô rau ngót AT',34,0,0,3300,4900,_binary '','2021-12-08 23:03:00',12),(56,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô rau thơm',34,0,0,55000,70000,_binary '','2021-12-08 23:03:00',10),(57,'2021-12-08 23:03:00',34,'2021-12-25 23:03:00','Lô rau thì là',34,0,0,70000,120000,_binary '','2021-12-08 23:03:00',16),(58,'2021-12-09 11:21:00',45,'2021-12-19 11:22:00','Lô các hồi fillet nauy',45,0,1,500000,578000,_binary '','2021-12-09 11:22:00',13),(59,'2021-12-09 11:26:00',34,'2021-12-19 11:26:00','Lô tôm sú tôm nõn thẻ',34,0,1,160000,175000,_binary '','2021-12-09 11:26:00',8),(60,'2021-12-09 11:26:00',34,'2021-12-19 11:26:00','Lô sứa hộp tĩnh gia',34,0,0,30000,40000,_binary '','2021-12-09 11:26:00',8),(61,'2021-12-09 11:43:00',34,'2021-12-26 11:43:00','Lô bắp bò úc tươi',35,0,2,330000,349000,_binary '','2021-12-09 11:43:00',11),(62,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô mỡ lợn',35,0,0,100000,105000,_binary '','2021-12-09 11:43:00',14),(63,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô chân giò lợn',35,0,1,50000,55000,_binary '','2021-12-09 11:43:00',14),(64,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô thịt lợn thăn chuột',35,0,0,150000,155000,_binary '','2021-12-09 11:43:00',14),(65,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô thịt lợn nạc vai đầu giòn',35,0,1,40000,46000,_binary '','2021-12-09 11:43:00',10),(66,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô thăn nội bò úc tươi',35,0,0,270000,280000,_binary '','2021-12-09 11:43:00',10),(67,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô thăn nội bò úc đông lạnh',35,0,0,370000,390000,_binary '','2021-12-09 11:43:00',10),(68,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô sườn non bò mỹ không xương',35,0,0,270000,290000,_binary '','2021-12-09 11:43:00',14),(69,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô thịt bồ câu',35,0,0,100000,105000,_binary '','2021-12-09 11:43:00',13),(70,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô gà ri sạch',35,0,0,100000,150000,_binary '','2021-12-09 11:43:00',8),(71,'2021-12-09 11:43:00',35,'2021-12-26 11:43:00','Lô xương đuôi lợn',35,0,0,30000,35000,_binary '','2021-12-09 11:43:00',16);
/*!40000 ALTER TABLE `consignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(5) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'DN','Đà Nẵng'),(2,'QNam','Quảng Nam');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `coupon_id` int NOT NULL AUTO_INCREMENT,
  `coupon_percent` int NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(500) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (1,10,'2021-12-08 20:04:00','10BANMOI2021','mừng giảm giá bạn mới',NULL),(3,20,'2021-12-08 20:12:31','20THANHVIENVIP','giảm giá thành viên mới',NULL);
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount_description` varchar(1000) DEFAULT NULL,
  `discount_image` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `name_discount` varchar(255) NOT NULL,
  `percent_discount` int NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES (1,'giảm giá mùa noel',NULL,'2021-12-25 19:49:00','Noel',5,'2021-12-08 19:49:00',_binary '',NULL),(2,'sale sập sàn',NULL,'2021-12-13 19:50:00','Sale 12/12',12,'2021-12-12 19:50:00',_binary '',NULL);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_tracks`
--

DROP TABLE IF EXISTS `order_tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_tracks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_time` datetime NOT NULL,
  `description` varchar(256) NOT NULL,
  `title` varchar(256) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1fdor1bwwupi7hiw4howp4ifo` (`order_id`),
  CONSTRAINT `FK1fdor1bwwupi7hiw4howp4ifo` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tracks`
--

LOCK TABLES `order_tracks` WRITE;
/*!40000 ALTER TABLE `order_tracks` DISABLE KEYS */;
INSERT INTO `order_tracks` VALUES (1,'2021-12-08 20:43:37','Đang thực hiện','Shipper vgreensadmin đang đến cửa hàng để lấy hàng','2021-12-08 20:43:37',1),(2,'2021-12-08 20:43:44','Đang thực hiện','Shipper vgreensadmin đang đến cửa hàng để lấy hàng','2021-12-08 20:43:44',2),(3,'2021-12-08 20:43:49','Đang thực hiện','Shipper vgreensadmin đang đến cửa hàng để lấy hàng','2021-12-08 20:43:49',3),(4,'2021-12-08 20:44:38','Đang thực hiện','Shipper vgreensadmin đang giao hàng đến người mua','2021-12-08 20:44:38',1),(5,'2021-12-08 20:44:42','Đã giao hàng','Shipper vgreensadmin đã giao hàng thành công','2021-12-08 20:44:42',1),(6,'2021-12-08 20:45:16','Đang thực hiện','Shipper vgreensadmin đang giao hàng đến người mua','2021-12-08 20:45:16',2),(7,'2021-12-08 20:45:21','Đang thực hiện','Shipper vgreensadmin đang giao hàng đến người mua','2021-12-08 20:45:21',3),(8,'2021-12-08 20:45:31','Đã giao hàng','Shipper vgreensadmin đã giao hàng thành công','2021-12-08 20:45:31',2),(9,'2021-12-08 20:45:35','Đã giao hàng','Shipper vgreensadmin đã giao hàng thành công','2021-12-08 20:45:35',3),(10,'2021-12-08 20:45:41','Đã nhận lại hàng','Người mua không nhận hàng, trả hàng Shipper vgreensadmin nhận hàng','2021-12-08 20:45:41',1),(11,'2021-12-08 20:52:40','Đã nhận lại hàng','Người mua không nhận hàng, trả hàng Shipper lanshipper nhận hàng','2021-12-08 20:52:40',2),(12,'2021-12-09 13:45:13','Đang thực hiện','Shipper lanshipper đang đến cửa hàng để lấy hàng','2021-12-09 13:45:13',4),(13,'2021-12-09 13:45:16','Đang thực hiện','Shipper lanshipper đang giao hàng đến người mua','2021-12-09 13:45:16',4),(14,'2021-12-09 13:45:19','Đã giao hàng','Shipper lanshipper đã giao hàng thành công','2021-12-09 13:45:19',4),(15,'2021-12-09 13:45:23','Đang thực hiện','Shipper lanshipper đang đến cửa hàng để lấy hàng','2021-12-09 13:45:23',5),(16,'2021-12-09 13:45:26','Đang thực hiện','Shipper lanshipper đang giao hàng đến người mua','2021-12-09 13:45:26',5),(17,'2021-12-09 13:45:55','Đang thực hiện','Shipper hieushipper đang đến cửa hàng để lấy hàng','2021-12-09 13:45:55',6),(18,'2021-12-09 13:45:58','Đang thực hiện','Shipper hieushipper đang giao hàng đến người mua','2021-12-09 13:45:58',6),(19,'2021-12-09 13:46:01','Đã giao hàng','Shipper hieushipper đã giao hàng thành công','2021-12-09 13:46:01',6),(20,'2021-12-09 13:46:04','Đã nhận lại hàng','Người mua không nhận hàng, trả hàng Shipper hieushipper nhận hàng','2021-12-09 13:46:04',6),(21,'2021-12-09 13:46:08','Đang thực hiện','Shipper hieushipper đang đến cửa hàng để lấy hàng','2021-12-09 13:46:08',8),(22,'2021-12-09 13:46:11','Đang thực hiện','Shipper hieushipper đang giao hàng đến người mua','2021-12-09 13:46:11',8),(23,'2021-12-09 13:46:14','Đã giao hàng','Shipper hieushipper đã giao hàng thành công','2021-12-09 13:46:14',8),(24,'2021-12-09 13:46:44','Đang thực hiện','Shipper tanshipper đang đến cửa hàng để lấy hàng','2021-12-09 13:46:44',9);
/*!40000 ALTER TABLE `order_tracks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `unit_price` double NOT NULL,
  `orderid` int DEFAULT NULL,
  `productid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj4gc2ja2otvwemf4rho2lp3s8` (`orderid`),
  KEY `FKaltatpxipsjtcih4d1h6bn0xr` (`productid`),
  CONSTRAINT `FKaltatpxipsjtcih4d1h6bn0xr` FOREIGN KEY (`productid`) REFERENCES `products` (`id`),
  CONSTRAINT `FKj4gc2ja2otvwemf4rho2lp3s8` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,3,13000,1,1),(2,3,40000,1,4),(3,3,13000,1,2),(4,1,13000,2,2),(5,2,13000,2,3),(6,1,13000,2,1),(7,1,13000,3,2),(8,1,13000,4,2),(9,1,60000,4,15),(10,1,349000,4,51),(11,1,35000,4,40),(12,1,110000,4,17),(13,1,110000,5,17),(14,2,13000,5,2),(15,2,70000,5,22),(16,1,100000,5,10),(17,1,13000,6,1),(18,1,37000,6,46),(19,1,175000,6,49),(20,1,46000,6,55),(21,1,349000,7,51),(22,1,70000,7,21),(23,1,360000,7,20),(24,1,75000,7,23),(25,1,79000,7,26),(26,1,55000,7,53),(27,4,60000,8,15),(28,1,578000,9,48),(29,1,70000,9,22),(30,1,100000,9,10),(31,1,83000,10,30),(32,1,35000,10,39),(33,1,60000,10,14),(34,1,100000,11,10),(35,1,135000,11,18),(36,1,70000,11,21),(37,1,75000,11,23),(38,1,79000,11,25),(39,1,80000,11,27),(40,1,81000,11,28),(41,1,5000,11,31);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_line` varchar(64) NOT NULL,
  `amount` float DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) NOT NULL,
  `coupon_id` int DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `deliver_date` datetime DEFAULT NULL,
  `deliver_days` int DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `payment_id` varchar(100) DEFAULT NULL,
  `payment_method` varchar(45) DEFAULT NULL,
  `payment_status` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `shipping_cost` float DEFAULT NULL,
  `state` varchar(45) NOT NULL,
  `status` int DEFAULT NULL,
  `sub_total` float DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk3cjfcgb621qhahps1tre43e4` (`username`),
  CONSTRAINT `FKk3cjfcgb621qhahps1tre43e4` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'08 hà văn tính',181738,NULL,'Đà Nẵng',1,'2021-12-08 20:23:43','2021-12-09 20:23:19',1,'dat hang tai day',1950,'VGreens','Admin',NULL,'COD','Đã Thanh Toán','0378795129','LAncode',5293.35,'Sơn Trà',5,176445,'vgreensadmin'),(2,'08 hà văn tính',59052.5,NULL,'Đà Nẵng',NULL,'2021-12-08 20:27:13','2021-12-14 20:24:37',6,'lan dat',650,'VGreens','Admin','1GY470780J333615E','PAYPAL','Đã Thanh Toán','0378795129','adb',7702.5,'Hòa Vang',5,51350,'vgreensadmin'),(3,'08 hà văn tính',14950,NULL,'Đà Nẵng',NULL,'2021-12-08 20:35:00','2021-12-14 20:27:33',6,'avb',0,'VGreens','Admin','62P65690GH973211M','PAYPAL','Đã Thanh Toán','0378795129','nb',1950,'Hòa Vang',4,13000,'vgreensadmin'),(4,'348 trần tống',678300,NULL,'Đà Nẵng',NULL,'2021-12-09 12:40:49','2021-12-14 12:40:42',5,'hop dat',1750,'A','Hop',NULL,'COD','Đã Thanh Toán','0378795129','500000',113050,'Hoàng Sa',4,565250,'ahopcustomer1'),(5,'348 trần tống',445200,NULL,'Đà Nẵng',NULL,'2021-12-09 12:42:04','2021-12-14 12:41:58',5,'hop dat 2',5000,'A','Hop',NULL,'COD','Chưa Thanh Toán','0378795129','500000',74200,'Hoàng Sa',3,371000,'ahopcustomer1'),(6,'348 trần tống',311160,NULL,'Đà Nẵng',NULL,'2021-12-09 12:48:58','2021-12-14 12:47:17',5,'hot dat',11700,'A','Hop','7WJ82756NC7608645','PAYPAL','Đã Thanh Toán','0378795129','500000',51860,'Hoàng Sa',5,259300,'ahopcustomer1'),(7,'348 trần tống',1176900,NULL,'Đà Nẵng',NULL,'2021-12-09 12:50:54','2021-12-14 12:49:35',5,'hop dat 2',7250,'A','Hop','41H0025166281411B','PAYPAL','Đã Thanh Toán','0378795129','500000',196150,'Hoàng Sa',0,980750,'ahopcustomer1'),(8,'01 nghi ninh , tp đà nẵng',252000,NULL,'Đà Nẵng',NULL,'2021-12-09 13:34:08','2021-12-11 13:32:12',2,'lan dat hang',0,'lan','nguyen','88L930210K7416544','PAYPAL','Đã Thanh Toán','0378795129','500000',12000,'Thanh Khê',4,240000,'lancustomer1'),(9,'01 nghi ninh , tp đà nẵng',780150,NULL,'Đà Nẵng',NULL,'2021-12-09 13:35:01','2021-12-11 13:34:33',2,'lan dat credit card',5000,'lan','nguyen','1RE53593VU049732D','PAYPAL','Đã Thanh Toán','0378795129','500000',37150,'Thanh Khê',2,743000,'lancustomer1'),(10,'348 nguyễn văn linh',184966,NULL,'Đà Nẵng',NULL,'2021-12-09 13:37:04','2021-12-15 13:36:35',6,'thanh toan paypal',17160,'Nguyen','Thanh','75T58352AR4680206','PAYPAL','Đã Thanh Toán','0378795129','500000',24126,'Hòa Vang',0,160840,'thanhcustomer1'),(11,'348 trần tống',623438,NULL,'Đà Nẵng',NULL,'2021-12-09 13:38:03','2021-12-11 13:37:57',2,'dat cod',31250,'Nguyen','Tan',NULL,'COD','Chưa Thanh Toán','0378795129','500000',29687.5,'Thanh Khê',0,593750,'tancustomer1');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_reviews`
--

DROP TABLE IF EXISTS `product_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(500) NOT NULL,
  `created_time` datetime NOT NULL,
  `rating` float NOT NULL,
  `title` varchar(100) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `username` varchar(24) NOT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK35kxxqe2g9r4mww80w9e3tnw9` (`product_id`),
  CONSTRAINT `FK35kxxqe2g9r4mww80w9e3tnw9` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reviews`
--

LOCK TABLES `product_reviews` WRITE;
/*!40000 ALTER TABLE `product_reviews` DISABLE KEYS */;
INSERT INTO `product_reviews` VALUES (1,'Ngon','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','ahopcustomer1',1),(2,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',2),(3,'Sản phẩm sạch sẽ','2021-12-09 13:26:00',5,'Rating','2021-12-09 13:26:00','hieucustomer1',1),(4,'quá chất lượng','2021-12-09 13:26:00',3,'Rating','2021-12-09 13:26:00','lancustomer1',1),(5,'Sản phẩm sạch sẽ','2021-12-09 13:26:00',5,'Rating','2021-12-09 13:26:00','hieucustomer1',2),(6,'giao hàng chập, ghét cho 1 sao','2021-12-09 13:26:00',1,'Rating','2021-12-09 13:26:00','thanhcustomer1',2),(7,'Đồ ngon quá ò, love shop','2021-12-09 13:26:00',5,'Rating','2021-12-09 13:26:00','tancustomer1',2),(8,'đồ chất lượng lắm shop','2021-12-09 13:26:00',5,'Rating','2021-12-09 13:26:00','lancustomer1',2),(9,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',3),(10,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',3),(11,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',3),(12,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',3),(13,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',3),(14,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',4),(15,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',4),(16,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',4),(17,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',4),(18,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',4),(19,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',5),(20,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',5),(21,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',5),(22,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',5),(23,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',5),(24,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',6),(25,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',6),(26,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',6),(27,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',6),(28,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',6),(29,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',7),(30,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',7),(31,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',7),(32,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',7),(33,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',7),(34,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',8),(35,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',8),(36,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',8),(37,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',8),(38,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',8),(39,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',9),(40,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',9),(41,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',9),(42,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',9),(43,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',9),(44,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',10),(45,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',10),(46,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',10),(47,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',10),(48,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',10),(49,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',11),(50,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',11),(51,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',11),(52,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',11),(53,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',11),(54,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',12),(55,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',12),(56,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',12),(57,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',12),(58,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',12),(59,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',13),(60,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',13),(61,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',13),(62,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',13),(63,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',13),(64,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',14),(65,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',14),(66,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',14),(67,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',14),(68,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',14),(69,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',15),(70,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',15),(71,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',15),(72,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',15),(73,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',15),(74,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',16),(75,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',16),(76,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',16),(77,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',16),(78,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',16),(79,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',17),(80,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',17),(81,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',17),(82,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',17),(83,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',17),(84,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',18),(85,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',18),(86,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',18),(87,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',18),(88,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',18),(89,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',19),(90,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',19),(91,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',19),(92,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',19),(93,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',19),(94,'Đồ Ngon sạch lắm shop ạ','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','ahopcustomer1',20),(95,'Shop tư vấn không nhiệt tình ghét cho 1 sao','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','lancustomer1',20),(96,'Sản phẩm rẻ quá ò','2021-12-09 11:26:00',4,'Rating','2021-12-09 11:26:00','hieucustomer1',20),(97,'nghe tên shop là thấy xịn rồi nên cho 5 sao nhé','2021-12-09 11:26:00',5,'Rating','2021-12-09 11:26:00','tancustomer1',20),(98,'Shop gì kỳ lạ','2021-12-09 11:26:00',1,'Rating','2021-12-09 11:26:00','thanhcustomer1',20);
/*!40000 ALTER TABLE `product_reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `available` bit(1) NOT NULL,
  `available_discount` bit(1) DEFAULT NULL,
  `created_date` date NOT NULL,
  `description` varchar(10000) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `update_date` date DEFAULT NULL,
  `categoryid` int NOT NULL,
  `consignment_id` int NOT NULL,
  `discount_id` int DEFAULT NULL,
  `unit_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1krrsjgcawsfg8k8u4hm5gi8q` (`categoryid`),
  KEY `FKser2mgff3ntdle013j17nx058` (`consignment_id`),
  KEY `FK5cyj7v7fvm60i2ubciqfo2wxm` (`discount_id`),
  KEY `FKeex0i50vfsa5imebrfdiyhmp9` (`unit_id`),
  CONSTRAINT `FK1krrsjgcawsfg8k8u4hm5gi8q` FOREIGN KEY (`categoryid`) REFERENCES `categories` (`id`),
  CONSTRAINT `FK5cyj7v7fvm60i2ubciqfo2wxm` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`),
  CONSTRAINT `FKeex0i50vfsa5imebrfdiyhmp9` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`),
  CONSTRAINT `FKser2mgff3ntdle013j17nx058` FOREIGN KEY (`consignment_id`) REFERENCES `consignments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,_binary '',NULL,'2021-12-08','Xoài cát Hòa Lộc loại đặc biệt là loại quả có hình dáng thuôn dài, mình tròn và đỉnh quả hơi nhọn. Loại quả này có eo rốn quả khá rõ ràng, hơi bầu ở khu vực gần cuống. Về màu sắc khi quả chín có màu vàng tươi, thịt dày, độ mịn cao, ít xơ và có độ dẻo lý tưởng.','https://res.cloudinary.com/lancloudshop/image/upload/v1638967973/ifl4fwq0902gcuujjfz0.png','Xoài','2021-12-09',4,1,2,1),(2,_binary '',NULL,'2021-12-08','Bơ sáp 034 Lâm Đồng có da màu xanh bóng, vỏ mỏng, cơm vàng dày, chiều dài quả từ 20cm đến 25cm. Khi chín, bơ sáp 034 có màu xanh, vị ngọt béo, dẻo, rất ít xơ và không bị nước như những loại bơ sáp truyền thống. Bơ sáp 034 chứa rất nhiều chất dinh dưỡng, đặc biệt thích hợp cho bà bầu và những người bị bệnh đái tháo đường.\n\nGiống bơ sáp 034 được trồng đầu tiên tại Lâm Đồng, sau đó được trồng tại một số tỉnh khác như Đăk Lăk, Gia Lai, Bình Phước…Tuy nhiên, chỉ khi trồng ở Lâm Đồng, bơ sáp 034 mới đạt chất lượng cao nhất, quả bơ cho trái dài, to, cơm vàng, béo và dẻo thơm.','https://res.cloudinary.com/lancloudshop/image/upload/v1638968153/dyezlvsshtjgvrx50ghj.png','Bơ Bơ','2021-12-08',4,1,NULL,1),(3,_binary '',NULL,'2021-12-08','Khế chua nhiều','https://res.cloudinary.com/lancloudshop/image/upload/v1638968183/t6cekmjnxo8pqfowwtyt.png','Quả Khế','2021-12-08',4,1,NULL,1),(4,_binary '',NULL,'2021-12-08','Ai đã có dịp về mảnh đất đầy nắng và gió Hương Khê - Hà Tĩnh chắc chắn sẽ không quên được vị ngọt thơm của trái bưởi phúc trạch. Trái Bưởi Phúc Trạch là một trong 7 loại trái cây quý hiếm của Việt Nam. Ngày nay, Bưởi phúc trạch đã và đang trở thành thương hiệu nổi tiếng mà bất kỳ ai cũng muốn thưởng thức.','https://res.cloudinary.com/lancloudshop/image/upload/v1638968252/ayrlzqitfccj4vmxhxoe.png','Bưởi','2021-12-08',4,2,NULL,1),(5,_binary '',NULL,'2021-12-08','Nhãn  có tên khoa học là Dimocarpus longan, tiếng hán việt gọi là “long nhãn”. Đây là một loại trái cây điển hình của vùng nhiệt đới thuộc thân gỗ, sống lâu năm. Theo tổng hợp từ các địa phương, hiện nay Hưng Yên là nơi có diện tích trồng lớn nhất và cho chất lượng nhãn ngon nhất, hiện nay Hưng Yên có gần 4 nghìn ha nhãn, trong đó, diện tích cho thu hoạch khoảng 3 nghìn ha.\n\nHưng Yên được coi là cái nôi của nhãn lồng tiến vua nổi tiếng. Nhãn lồng Hưng Yên thường đạt sản lượng trên 30 nghìn tấn, tập trung chủ yếu ở xã Hồng Nam và huyện Khoái Châu.  Nơi đây vẫn giữ được giống nhãn lồng gốc, cho hương vị ngon nhất, cùi dầy giòn, vị ngọt sắc, hương thơm khó quên. Hạt nhãn nhỏ và dóc, sắc đen ánh nâu đỏ.','https://res.cloudinary.com/lancloudshop/image/upload/v1638975096/j4yrfm5huqbppsj8ucis.png','Nhãn','2021-12-08',4,7,2,1),(6,_binary '',NULL,'2021-12-08','Trên thị trường hiện nay, ngoài thịt lợn ra thì thịt bò cũng khá phổ biến với người tiêu dùng. Nhưng thịt bò cũng có quá nhiều lựa chọn: thịt mông, thịt vai, thịt thăn, nạm, sườn… Vậy bạn có biết đâu là phần thịt bò ngon nhất chưa?\n\nĐể trả lời câu hỏi trên Bác Tôm xin được đưa ra ngay câu trả lời đó là “Thịt bò thăn”. Đây là phần thịt bò được ưa chuộng nhất bởi thịt mềm và nhiều nạc nhất. Chính vì vậy giá thành thịt cũng cao nhất và quy trình để chế biến thịt thăn cũng đòi hỏi nhiều công phu, tỉ mỉ hơn.','https://res.cloudinary.com/lancloudshop/image/upload/v1638975172/oxmesx5z0tbazznm8d0f.jpg','Than Bò Mỹ','2021-12-08',3,13,NULL,1),(7,_binary '',NULL,'2021-12-08','Thịt ba chỉ lợn quế là phần thịt bụng của lợn quế, bao gồm các lớp thịt và mỡ phần xếp xen kẽ lên nhau thành nhiều lớp. Thịt ba chỉ quế là loại phổ biến và được ưa chuộng nhất nên cũng dễ dàng chế biến ba chỉ thành nhiều món như chiên, luộc, quay giòn, kho...','https://res.cloudinary.com/lancloudshop/image/upload/v1638975243/ne67m7g3vmvu1er09uid.jpg','Thịt Ba Chỉ Bò Mỹ','2021-12-08',3,14,NULL,1),(8,_binary '',NULL,'2021-12-08','Thịt bò là một loại thực phẩm tươi sống với nhiều giá trị dinh dưỡng và có thể dùng để chế biến rất nhiều món ăn thơm ngon, bổ dưỡng khác nhau. \n\nThịt bò nạc vai có tên tiếng Anh là  Beef Chuck Eyeroll, đây là phần thịt nằm ở phía trong cùng của vùng ức của con bò. Ở phần thịt này, tỷ lệ nạc, mỡ phân bố khá đều, thịt chắc mềm, mỡ trắng xen lẫn với các mô thịt nạc. Thịt ăn ngọt, đậm và có hương vị đặc trưng của thịt bò. Thịt bò nạc vai chế biến món gì ngon nhất?','https://res.cloudinary.com/lancloudshop/image/upload/v1638976642/qp67zv7jwacmejtes0ub.jpg','Thị Bò Nạt Vai','2021-12-08',3,15,NULL,1),(9,_binary '',NULL,'2021-12-08','cá ngon','https://res.cloudinary.com/lancloudshop/image/upload/v1638976727/yilmgnou9ollllwwemmb.jpg','Cá Ánh Vũ Tiên','2021-12-08',2,16,NULL,1),(10,_binary '',NULL,'2021-12-08','cá ba sa','https://res.cloudinary.com/lancloudshop/image/upload/v1638976812/k5dljynwa70ochtewems.jpg','Cá Ba Sa','2021-12-08',2,17,1,1),(11,_binary '',NULL,'2021-12-08','cá trác vàng','https://res.cloudinary.com/lancloudshop/image/upload/v1638976851/oiipplygczpz1cl2mwyq.jpg','Cá Trác Vàng Biển','2021-12-08',2,18,NULL,1),(12,_binary '',NULL,'2021-12-08','gẩu bò thái lát','https://res.cloudinary.com/lancloudshop/image/upload/v1638976910/kraysqxmhuenhyhpgzuj.jpg','Gẩu bò thái lát','2021-12-08',3,19,NULL,1),(13,_binary '',NULL,'2021-12-08','khô bò','https://res.cloudinary.com/lancloudshop/image/upload/v1638977027/iatxtzf6gsi7pjs43dyz.png','Khô Bò','2021-12-08',5,20,NULL,4),(14,_binary '',NULL,'2021-12-08','khô gà ngon','https://res.cloudinary.com/lancloudshop/image/upload/v1638977062/klvifbmu6wa3i7szzjbb.png','Khô Gà','2021-12-08',5,21,2,4),(15,_binary '',NULL,'2021-12-08','Bánh tráng hay còn được gọi là bánh đa nem. Nhắc đến nguyên liệu này là nhắc đến các món ăn hấp dẫn như chả ram, bánh tráng muối tôm, món cuốn,... Vậy loại bánh tráng nào ngon? Làm món gì cho mới lạ? Hãy cùng vgreens tìm hiểu trong bài viết dưới đây nhé.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977141/ofwlbtka4qfbzui4urlk.jpg','Bánh Tráng Đá Nem','2021-12-08',5,22,NULL,5),(16,_binary '',NULL,'2021-12-08','Bột bạc hàkhá an toàn với những lợi ích sức khỏe của nó, vì nó không có tác dụng độc hại và sẵn có. Dưới đây là một số lợi ích của chúng:\n\nLàm giảm các vấn đề về đường hô hấp như hen suyễn, ho, tắc nghẽn và cảm lạnh.\nTốt cho các vấn đề về dạ dày như khó tiêu, chua, nhiễm trùng, đầy hơi, đau bụng, tiêu chảy, v.v.\nGiảm nguy cơ chuột rút có lợi trong việc giảm đau bụng và đau bụng kinh\nNó có tác dụng thư giãn - giảm đau đầu, đau nửa đầu và giảm căng thẳng và căng thẳng\nĐiều trị buồn nôn và nôn\nXơ vữa động mạch, bệnh tăng trương lực, bệnh thận và gan\nBột lá bạc hà còn hỗ trợ chăm sóc răng miệng vì nó có tác dụng diệt khuẩn và làm hơi thở thơm mát\nĐặc tính chữa lành vết thương tuyệt vời\nChất chống oxy hóa trong bạc hà giúp ngăn ngừa ung thư','https://res.cloudinary.com/lancloudshop/image/upload/v1638977188/ruviqcbqnvggbkgd6fzg.jpg','Bột Bạt Hà','2021-12-08',5,23,NULL,4),(17,_binary '',NULL,'2021-12-08','Bột cà rốt của Nông Sản Vgreens được chế biến hoàn toàn từ 100% cà rốt tự nhiên, không chất tạo màu hay hương liệu, đạt tiêu chuẩn an toàn vệ sinh thực phẩm. Sản phẩm có dạng bột mịn, màu vàng cam và đỏ, giữ được vị ngọt đặc trưng của cà rốt. So với cà rốt tươi, bột cà rốt có thời gian bảo quản lâu hơn, dễ bảo quản và vận chuyển hơn.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977252/mlykkpavi6bslasxjqij.jpg','Bột Cà Rốt','2021-12-08',5,24,NULL,4),(18,_binary '',NULL,'2021-12-08','Bột cỏ lúa mì là sản phẩm được chế biến từ cây cỏ lúa mì khoảng 12 đến 14 ngày tuổi khi hàm lượng chất diệp lục và chất chống oxy hóa cao nhất. Người ta sẽ cắt sát gốc cây, phơi khô dưới nhiệt độ thấp rồi xay thành bột mịn. Cuối cùng, để không làm mất đi các thành phần dinh dưỡng, bột được đóng gói trong bao bì cản sáng và không oxy hóa.\n\nGiá trị dinh dưỡng của bột cỏ lúa mì\nTrong 100g bột cỏ lúa mì có chứa các thành phần dinh dưỡng sau:\n\nVitamin B2 - 0,156 mg\nVitamin C - 2,5 mg\nNatri - 15 mg\nPhốt pho - 201 mg\nSắt - 2,15 mg\nChất xơ - 1g\nChất béo - 1,25g\nNăng lượng - 200 kilocalories\nVitamin B6 - 0,266 mg\nVitamin B1 - 0,224 mg\nKẽm - 1,66 mg\nKali - 170 mg\nMagiê - 81 mg\nCanxi - 30 mg\nTinh bột - 43g\nChất đạm - 7,5g\nNước - 48g','https://res.cloudinary.com/lancloudshop/image/upload/v1638977320/y0m1bofilw0v9fefpsux.jpg','Bột Cỏ Lúa Mì','2021-12-08',5,25,1,4),(19,_binary '',NULL,'2021-12-08','Hoa sen là biểu tượng của dân tộc Việt Nam. Ngoài những biểu tượng mà nó mang lại, hoa sen còn được sử dụng như một loại dược liệu quý. Tất cả các bộ phận của sen đều có những công dụng chữa bệnh tuyệt vời.\n\nChúng ta đã quen thuộc với hạt sen và tâm sen. Nhưng với lá sen thì ít ai biết được công dụng của nó. Vậy bột lá sen có những tác dụng tuyệt vời như thế nào đối với sức khỏe?','https://res.cloudinary.com/lancloudshop/image/upload/v1638977380/yq7ktywkshy2w3gezpgl.jpg','Bột Lá Sen','2021-12-08',5,26,NULL,4),(20,_binary '',NULL,'2021-12-08','Cây tía tô có tên khoa học là Perilla frutescens L. Britt thuộc họ hoa môi - Lamiaceae, chúng ta vẫn gọi là tử tô, tô ngạnh, tô diệp hay é tía. Cây được trồng khắp nơi trên các tỉnh thành Việt Nam, lấy lá làm gia vị và làm thuốc là chủ yếu.\n\nlá tía tô\n\nTrong Đông y, lá tía tô có vị cay, tính bình, được dùng vào hai kinh phế và tỳ. Tía tô được biết đến với công dụng thông mũi, giải cảm, chống dị ứng, tiêu độc đạm cá, trị ho, hen suyễn, an thai, .... \n\nNgày nay, qua nghiên cứu, lá tía tô đã được chứng minh là có tác dụng chữa bệnh gút, giảm béo, trị rối loạn mỡ máu, chống viêm, chống dị ứng, có tác dụng tốt trong điều trị ung thư, ... \n\nChúng có giá trị dinh dưỡng cao, giàu vitamin A, C, giàu Ca, Fe, P ... tía tô có tác dụng làm đẹp da với giá thành rẻ nhưng hiệu quả. Lá tía tô thường được dùng tươi trực tiếp hoặc phơi khô, sấy khô có thể nghiền thành bột tía tô để dùng dần.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977454/kux0lkv2kt4kkypeurhn.jpg','Bột Tía Tô','2021-12-08',5,27,NULL,4),(21,_binary '',NULL,'2021-12-08','Bún gấc được làm từ bột gạo Bao Thai Cao Bằng - là giống gạo chỉ sinh trưởng tốt trong thời tiết se lạnh của Cao Bằng. Gạo Bao Thai tuy không dẻo nhưng ăn rất ngọt. Thành phần gấc trong bún chứa nhiều chất chống oxy hóa và có tác dụng tốt với bệnh nhân mắc các bệnh tim mạch, ung thư, đục thủy tinh thể, thoái hóa điểm vàng.\n\nSự kết hợp này tạo ra sản phẩm Bún Gấc Thiên Nhiên. Sợi bún đặc trưng thơm mùi gấc, có vị ngậy tự nhiên pha chút ngọt thanh và đặc biệt giàu dinh dưỡng.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977525/vkhbqvtgyoargwvogdlg.jpg','Bún Gấc Thiên Nhiên','2021-12-08',5,28,1,6),(22,_binary '',NULL,'2021-12-08','Cá cơm rất giàu vitamin A, axit béo, canxi, vitamin E, vitamin A, giúp sáng mắt, ngăn ngừa bệnh về mắt, duy trì làn da khỏe mạnh.\n\nĂn cá cơm giúp giảm cholesterol trong máu và ngăn ngừa bệnh tim mạch.\n\nCá cơm cung cấp một lượng lớn chất đạm và chất đạm nên còn được dùng để làm mắm.\n\nCá cơm tẩm mè, cá cơm kho và cá cơm khô là những món ăn từ cá cơm tưởng chừng đơn giản nhưng lại làm tăng giá trị sản phẩm cũng như giá trị kinh tế cho cư dân vùng biển xung quanh.\n\n3 loại cá cơm chế biến hiện đang được Nông sản Vgreens phân phối.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977581/iiszlq2pqje39w58xft6.jpg','Cá Cơm Chế Biến','2021-12-08',5,29,NULL,4),(23,_binary '',NULL,'2021-12-08','Cơm cháy Sài Gòn là thương hiệu được tin cậy trong nhiều năm. Chuyên sản xuất chà bông với công thức gia truyền, nguyên liệu sạch, đảm bảo về độ giòn, thơm, không ngấy. Tươi ngon, đảm bảo vệ sinh an toàn thực phẩm, thịt chà bông 100%, không pha trộn.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977631/lmn1pu6gp4glbvxgxj7p.jpg','Cơm Cháy','2021-12-08',5,30,1,7),(24,_binary '',NULL,'2021-12-08','Nhân óc chó đỏ, được tách ra từ quả óc chó đỏ, có chứa rất nhiều chất dinh dưỡng tốt cho cơ thể nên chúng đã được người tiêu dùng rất ưa chuộng. Hãy cùng tìm hiểu kĩ hơn về công dụng của loại hạt này nhé.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977684/zgwbbwcm5ttqif6azbfx.jpg','Óc Chó Đỏ','2021-12-08',5,31,NULL,5),(25,_binary '',NULL,'2021-12-08','Chúng ta đã quá quen thuộc với các loại hạt như đậu xanh, đậu đỏ, đậu Hà Lan ,..vv là những loại hạt vừa thơm ngon lại bổ dưỡng. Vậy bạn đã bao giờ nghe tới hạt đậu lăng chưa?\n\nĐậu lăng là một cái tên còn khá mới mẻ ở thị trường Việt Nam, trong bài viết này, Nông Sản Vgreens sẽ giúp bạn tìm hiểu về hạt đậu lăng và những lợi ích của nói với sức khỏe con người nhé!','https://res.cloudinary.com/lancloudshop/image/upload/v1638977745/fvxnfpkvuxpjris8toin.jpg','Đậu Lăng','2021-12-08',5,32,1,1),(26,_binary '',NULL,'2021-12-08','Đậu gà','https://res.cloudinary.com/lancloudshop/image/upload/v1638977790/modmdprroxnclpsynmwf.jpg','Đậu Gà','2021-12-08',5,33,NULL,4),(27,_binary '',NULL,'2021-12-08','Hạt kê là một trong những loại hạt ngũ cốc mang giá trị dinh dưỡng rất cao, được các chuyên gia dinh dưỡng khuyên dùng. Ngoài giá thành vừa phải, lại còn được chế biến thành nhiều món ăn, nên hạt kê rất được ưa dùng.\n\nSau đây qua bài viết này chúng ta hãy cùng tìm hiểu về nguồn gốc và những tác dụng của hạt kê nhé.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977857/bba67xtba9trbeozn7pd.jpg','Hạt Kê','2021-12-08',5,34,1,4),(28,_binary '',NULL,'2021-12-08','“Cao Bằng cao thật cao- rồi dần dần bằng xuống” đúng như cái tên, vùng đất Cao Bằng không những đặc biệt về địa lý mà sản vật nơi đây cũng đặc sắc vô cùng. Một trong số đó chúng ta phải kể đến đó chính là hạt dẻ Cao Bằng ( hay còn gọi là hạt dẻ Trùng Khánh).\n\nThức quà thơm bùi ấy có gì hấp dẫn mà du khách ai đến nơi đây cũng phải mua về làm quà','https://res.cloudinary.com/lancloudshop/image/upload/v1638977917/fgkndda68876chh8zeab.jpg','Hạt Dẻ','2021-12-08',5,35,1,5),(29,_binary '',NULL,'2021-12-08','Cây đu đủ còn được nhiều người gọi bằng cái tên phan qua thụ hay cây cà lao,... Đây là giống cây đa tính bởi nó có cả cây đực, cây cái và cây lưỡng tính.\n\nHoa đu đủ đực là một bộ phận được thu hái từ cây đu đủ đực. Bảng thông tin dưới đây là một vài điều cần biết về loại cây này.','https://res.cloudinary.com/lancloudshop/image/upload/v1638977994/dzap660b1qjvmrffqjqu.jpg','Hoa đu đủ đực sấy khô','2021-12-08',5,36,2,5),(30,_binary '',NULL,'2021-12-08','Lá chanh là một nguyên liệu không thể thiếu khi ăn các món như thịt gà, làm sốt chấm. Lá chanh cũng được cho là tiên dược chữa được nhiều bệnh.','https://res.cloudinary.com/lancloudshop/image/upload/v1638978107/f6aurjdpdabozrjw9z5x.jpg','Lá chanh sấy khô','2021-12-08',5,37,2,4),(31,_binary '',NULL,'2021-12-08','Mướp là một loại cây thảo dạng dây leo, được trồng để lấy quả xanh. Với nhiều chất dinh dưỡng và công dụng đặc biệt tốt cho sức khỏe, mướp được nhiều người lựa chọn trong bữa ăn gia đình.Mướp hương có vị thơm ngát, ăn ngọt mát ####Công dụng của mướpTất cả bộ phận của mướp đều là thảo dược trị n','https://res.cloudinary.com/lancloudshop/image/upload/v1638978736/asjdc0ewwtxgn924jziz.jpg','Mướp Hương','2021-12-08',1,38,1,1),(32,_binary '',NULL,'2021-12-08','cải','https://res.cloudinary.com/lancloudshop/image/upload/v1638978770/uce3noxa9pk3bicremap.jpg','Cải Bó Xôi','2021-12-08',1,39,NULL,5),(33,_binary '',NULL,'2021-12-08','Thêm một loại rau nữa mà bạn không nên bỏ qua trong khu vườn nhỏ xinh của mình, đó là cải ngồng. Loại cải này khá dễ ăn, rất nhiều người “ghiền” món canh cải ngồng, cải ngồng xào. Không chỉ có vậy cải ngồng còn cung cấp nhiều dưỡng chất thiết yếu cho cơ thể.Cũng giống như một số loại cải khác, giống','https://res.cloudinary.com/lancloudshop/image/upload/v1638978819/avtrqck6fsaw9fhxjyne.jpg','Cải Ngóng','2021-12-08',1,41,NULL,5),(34,_binary '',NULL,'2021-12-08','Đậu trạch Bác Tôm có xuất xứ từ Bắc Hà - Lào Cai, được trồng hữu cơ theo hướng 7 KHÔNGKhông thuốc trừ sâuKhông thuốc diệt cỏKhông phân bón hóa họcKhông chất kích thích tăng trưởngKhông biến đổi genKhông chất bảo quảnKhông tưới nước ô nhiễm####Đậu trạch Bác Tôm có xuất xứ từ Bắc Hà - Lào Cai','https://res.cloudinary.com/lancloudshop/image/upload/v1638978864/tbtsjsfpuv0pwshzwsqf.jpg','Đậu Trạch','2021-12-08',1,42,NULL,5),(35,_binary '',NULL,'2021-12-08','Rau dền là một những loại rau có rất nhiều lợi ích cho cơ thể.Ngăn ngừa ung thưCác hợp chất chống oxy hóa như vitamin E, C, sắt, magiê, phốt pho hay lysine có trong rau dền sẽ chống lại các gốc tự do có hại và ngăn chặn sự hình thành của các tế bào ác tính gây ra ung thư.Giảm cholesterol','https://res.cloudinary.com/lancloudshop/image/upload/v1638978893/kyvxoetc1cbbyxo9wwcj.jpg','Rau Dền','2021-12-08',1,43,NULL,5),(36,_binary '',NULL,'2021-12-08','Có các loại rau theo mùa Mùa hè thu: Rau muống, rau dền, rau mồng tơi, rau đay,....Mùa đông xuân: Rau cải, rau cần, ..','https://res.cloudinary.com/lancloudshop/image/upload/v1638978934/ijjbmccxk1buvrzkh5x0.jpg','Rau hữu cơ PGS','2021-12-08',1,44,NULL,5),(37,_binary '',NULL,'2021-12-08','########Miễn phí vận chuyển cho hóa đơn từ 200.000đ (Trong bán kính 5km)####Đổi trả trong vòng 24h kể từ khi nhận hàngCam kết đổi trả nếu ko hài lòng với chất lượng sản phẩm do lỗi:- Chất lượng sản phẩm không đúng như cam kết- Dập nát trong quá trình vận chuyển- Giao hàng sai hoặc không đúng hẹ','https://res.cloudinary.com/lancloudshop/image/upload/v1638978968/asdtrtxtdpzhkhrvhbme.jpg','Su Hào','2021-12-08',1,45,NULL,5),(38,_binary '',NULL,'2021-12-08','Bắp cải hay được gọi là cải bắp, là một loại rau chủ lực trong dòng họ Cải, có xuất xứ từ vùng Địa Trung Hải. Bắp cải là một thực vật có hoa thuộc nhóm hai lá mầm với các lá tạo thành một cụm đặc hình gần giống như hình cầu.Trong bắp cải có chứa lượng vitamin cao hơn nhiều so với các loại rau củ khá','https://res.cloudinary.com/lancloudshop/image/upload/v1638979793/hqbp1y5h44mnama0tb4s.jpg','Bắp cải RA','2021-12-08',1,46,NULL,5),(39,_binary '',NULL,'2021-12-08','Xuất xứ : - Đà LạtKhoai tây bở, thơm, dùng nấu canh xương, súp hay chiên đều ngon########Miễn phí vận chuyển cho hóa đơn từ 200.000đ (Trong bán kính 5km)####Đổi trả trong vòng 24h kể từ khi nhận hàngCam kết đổi trả nếu ko hài lòng với chất lượng sản phẩm do lỗi:- Chất lượng sản phẩm không đúng','https://res.cloudinary.com/lancloudshop/image/upload/v1638979830/ctwpqrbdqpu34hxrlph1.jpg','Khoai tây RA','2021-12-08',1,47,NULL,1),(40,_binary '',NULL,'2021-12-08','Bí xanh còn gọi là bí đao, bí phấn, đông qua, bạch qua, chẩm qua… Đây thực phẩm quen thuộc trong bữa ăn hằng ngày này rất tốt cho sức khỏe, nó có tác dụng thanh nhiệt làm mát ruột, giúp da đẹp dáng thon. Thành phần chủ yếu của bí là nước, nhiều chất xơ, không chứa lipid. Cứ 100g bí có 0,4g','https://res.cloudinary.com/lancloudshop/image/upload/v1638979861/f5poe3vfgwplkcuh4dvi.jpg','Bí Xanh','2021-12-08',1,48,1,5),(41,_binary '',NULL,'2021-12-08','- Xuất xứ: Bắc Hà - Lào Cai- Đặc điểm: Giòn, ngọt, thanh mát- Trọng lượng: ~5 -10 quả/kg####Rau an toàn là một khái niệm chung để chỉ các loại rau, củ được sản xuất cung cấp đến người dùng đảm bảo tiêu chuẩn an toàn thực phẩm.Những sản phẩm rau tươi có chất lượng đúng như đặc tính giống của nó, hàm','https://res.cloudinary.com/lancloudshop/image/upload/v1638979932/nyfdjvesrtomgneyhhlt.jpg','Cà Rốt','2021-12-08',1,49,NULL,5),(42,_binary '',NULL,'2021-12-08','Một loại cây sinh trưởng tốt ở những nơi có khí hậu mát mẻ và được trồng ở khắp các tỉnh miền Bắc - Củ cải. Tuy nhiên, thiên nhiên đã trù phú cho vùng đất Bắc Hà có được loại củ cải với chất lượng tuyệt vời, vị ngọt thanh mát và còn có những công dụng không ngờ tới:Giảm nguy cơ ung thư','https://res.cloudinary.com/lancloudshop/image/upload/v1638980020/jjgktp5xrwsndcpcolf5.jpg','Củ Cải Trắng RA','2021-12-08',1,50,NULL,5),(43,_binary '',NULL,'2021-12-08','- Xuất xứ: Bắc Hà - Lào CaiRau ngót là loại rau quen thuộc đối với người Việt chúng ta.  Lá cây rau ngót hình bầu dục, mọc so le; sắc lá màu lục thẫm. Khi hái ăn, chọn lá non.####Rau an toàn là một khái niệm chung để chỉ các loại rau, củ được sản xuất cung cấp đến người dùng đảm bảo tiêu chuẩn','https://res.cloudinary.com/lancloudshop/image/upload/v1638980127/bjc2fxh1xq8gcjtrqith.jpg','Rau Ngót','2021-12-08',1,51,NULL,5),(44,_binary '',NULL,'2021-12-08','Rau ngót là loại rau quen thuộc, gần gũi với mỗi chúng ta, nó mang lại rất nhiều lợi ích thiết thực cho sức khỏe.Theo đông y, rau ngót vị ngọt, tanh, tính bình. Không chỉ là loại rau ngon, bổ mà nó còn có tác dụng chữa bệnh rất tốt.Theo y học hiện đại thì rau ngót có chứa hàm lượng vitamin B1, B2, B','https://res.cloudinary.com/lancloudshop/image/upload/v1638980157/uwly863tobbhj5yiupwz.jpg','Rau ngót AT','2021-12-08',1,55,NULL,5),(45,_binary '',NULL,'2021-12-08','khác','https://res.cloudinary.com/lancloudshop/image/upload/v1638980246/z559q3ltcflqfhx9jzcl.jpg','Rau thơm','2021-12-08',1,56,NULL,5),(46,_binary '',NULL,'2021-12-08','khác','https://res.cloudinary.com/lancloudshop/image/upload/v1638980339/jggzvwftobwwfew8ps6k.jpg','Xà Lách','2021-12-08',1,53,NULL,5),(47,_binary '',NULL,'2021-12-08','khác','https://res.cloudinary.com/lancloudshop/image/upload/v1638980478/x8u9opdh5ttpogifmpqn.jpg','Rau thì là','2021-12-08',1,57,NULL,5),(48,_binary '',NULL,'2021-12-09','Cá hồi fillet được Bác Tôm nhập trực tiếp từ Nauy qua đường hàng không. Với tên tuổi đã được tạo dựng uy tín từ lâu đời đảm bảo được chất lượng cũng như độ tươi ngon trong từng thớ cá hồi.Để được những miếng cá hồi thơm ngon nhất thì luôn phải đảm bảo các tiêu chuẩn nghiêm ngặt trong quá trình nuôi','https://res.cloudinary.com/lancloudshop/image/upload/v1639023805/xxbpfi8k2mjf5h8bu5ki.jpg','Cá hoogi fillet Na Uy','2021-12-09',2,58,NULL,5),(49,_binary '',NULL,'2021-12-09','Tôm Sú Biển \nTôm sú biển tự nhiên là một loại tôm sú loại to được đánh bắt ở những vùng khơi xa, nên nguồn cung cấp của tôm sú loại to không được dồi dào. \n\nTôm sú có nhiều chất dinh dưỡng, thịt săn chắc, rất ngon, hơn nữa lại rất dễ chế biến mang lại những hương thơm ngon đặc trưng của biển. Đồng thời tôm sú là một loại hải sản được yêu thích nhất trong nhiều nhà hàng cao cấp.','https://res.cloudinary.com/lancloudshop/image/upload/v1639024186/wwipmrdk5z0hfdqdeyrc.jpg','Tôm sú nõn thẻ','2021-12-09',2,59,1,1),(50,_binary '',NULL,'2021-12-09','Sứa biển là loại động vật sống tại các vùng biển hay những nơi nước mặn, sứa là loài động vật không có xương sống. Sứa biển chứa nhiều độc tố nên nếu vô tình chạm phải thì sẽ bị dị ứng. Sản phẩm sứa biển đóng hộp được làm từ sứa biển, sứa biển sau khi đánh bắt được đem đi bảo quản bằng muối sau đó được đem đi ngâm xả mặn rồi tiếp đến là quy trình đóng hộp. Sau khi đóng gói xong thì có thể sử dụng được luôn mà không mất đi thời gian và công sức để ngâm xả mặn và làm sạch. Sản phẩm sứa biển đóng hộp phù hợp để chế biến nhiều món ăn.','https://res.cloudinary.com/lancloudshop/image/upload/v1639024230/yfqkzyehr2x7rhkxekfp.jpg','Sứa hộp tĩnh gia 650g','2021-12-09',2,60,NULL,4),(51,_binary '',NULL,'2021-12-09','Thịt bò là món ăn ngày càng phổ biến trong bữa ăn của các gia đình Việt Nam. Trong thịt bò có chưa lượng protein cao, cung cấp năng lượng hữu ích cho con người. Thịt bò được phân chia thành những phần khác nhau với nhiều nguồn gốc xuất xứ. Hãy cùng nông sản Dũng Hà tìm hiểu những kiến thức cơ bản về thịt bắp bò Úc nhé.\n\nThịt bắp bò là gì?\nBắp bò hay còn gọi là thịt chân giò bò là phần thịt được xẻ ra từ bắp đùi của con bò. Loại thịt này thường dai và có nhiều mô nối hơn các phần khác của thịt bò. Thịt bắp bò được chia thành hai loại: Bắp rùa là phần bắp nhỏ nằm giữa lõi cái bắp đùi to 2 chân sau của con bò. Bắp hoa là phần bắp nhỏ ở chân trước của con bò. Những người sành ăn sẽ biết bắp rùa mềm hơn bắp hoa và giá thịt bò bắp rùa cũng cao hơn thịt bò bắp hoa.','https://res.cloudinary.com/lancloudshop/image/upload/v1639025643/l8secvyhkyh1qg0m0lmx.jpg','Bắp bò úc tơi nguyên khối','2021-12-09',3,61,NULL,1),(52,_binary '',NULL,'2021-12-09','Mỡ lợn là gì?\nThịt mỡ được sử dụng rộng rãi trong bữa ăn hằng ngày của mỗi gia đình Việt Nam. Thịt mỡ là phần mô thịt lợn có màu trắng, nằm ngay bên dưới lớp da.','https://res.cloudinary.com/lancloudshop/image/upload/v1639025687/vaik2jhdqcb8pdiugghu.jpg','Mỡ lợn','2021-12-09',3,62,NULL,1),(53,_binary '',NULL,'2021-12-09','chân giò được lấy trực tiếp từ lợn','https://res.cloudinary.com/lancloudshop/image/upload/v1639025893/f6sziz1rffch89y7rw1x.jpg','Chân giò lợn','2021-12-09',3,63,NULL,7),(54,_binary '',NULL,'2021-12-09','Thịt lợn thăn chuột thảo dược là gì?\nThịt lợn thăn chuột hay còn gọi là thăn nội được cắt ra từ con lợn được nuôi bằng các loại thảo dược. Thăn chuột lợn là phần thịt gồm nhiều nạc, có ít mỡ xen lẫn và thịt rất mềm.\n\nNông sản Dũng Hà cung cấp cho người tiêu dùng thịt thăn chuột chất lượng tốt nhất','https://res.cloudinary.com/lancloudshop/image/upload/v1639025946/restduuca35gcre2mocy.jpg','Thị lợn thăn chuột','2021-12-09',3,64,NULL,5),(55,_binary '',NULL,'2021-12-09','Thịt vai là phần thịt nằm ở giữa cổ và vai lợn, đã được lọc bỏ xương, mỡ và da thừa. Giống như tên gọi, vì thịt nằm ở vị trí trên vai của con lợn nên có độ dai và giòn hơn. Có thể chế biến thành các món kho, chiên, nướng,.. Đây là phần thịt khá ngon vì có tỷ lệ nạc và mỡ đều nhau, cân bằng hài hòa. Thịt vai không quá khô mà cũng không thấy quá mỡ.','https://res.cloudinary.com/lancloudshop/image/upload/v1639026104/kdnqphgoc2vn5jdqa2rf.jpg','Thịt vai lợn thảo dược','2021-12-09',3,65,1,5),(56,_binary '',NULL,'2021-12-09','Thịt thăn nội là phần mềm và ngon nhất của con bò. Đây là phần thịt được cắt ra từ phần lưng phía trong và cuối thắt lưng. Thịt thăn nội phù hợp để chế biến các món bít tết và chỉ nên dùng để làm các món ăn khô như nướng vỉ hoặc hấp.','https://res.cloudinary.com/lancloudshop/image/upload/v1639026173/jpb3oxvxu1hhk3zudrqm.jpg','Thịt thăn nội bò úc tơi','2021-12-09',3,66,NULL,5),(57,_binary '',NULL,'2021-12-09','Thăn ngoại bò Mỹ là phần thịt nằm ở phần cuối của dẻ sườn bò. Phần thịt này có đặc trưng là mềm, nạc và mỡ đan xen hợp lý','https://res.cloudinary.com/lancloudshop/image/upload/v1639026212/xnow6qu0dbyaveswmjfb.jpg','Thăn nội bò úc đông lạnh','2021-12-09',3,67,NULL,5),(58,_binary '',NULL,'2021-12-09','Là tín đồ của thịt bò nhập khẩu chắc chắn bạn không thể bỏ qua các món ăn hấp dẫn từ sườn bò Mỹ. Hãy cùng nông sản Dũng Hà bổ sung thêm các kiến thức về món ăn này nhé.','https://res.cloudinary.com/lancloudshop/image/upload/v1639026251/bpmxj9enimsyanhw4ksx.jpg','Sường non bò mỹ không xương','2021-12-09',3,68,NULL,5),(59,_binary '',NULL,'2021-12-09','Là tín đồ của thịt bò nhập khẩu chắc chắn bạn không thể bỏ qua các món ăn hấp dẫn từ sườn bò Mỹ. Hãy cùng nông sản Dũng Hà bổ sung thêm các kiến thức về món ăn này nhé.','https://res.cloudinary.com/lancloudshop/image/upload/v1639026364/hgahcqt1l5aujoarlk3t.jpg','Thị bồ câu','2021-12-09',3,69,NULL,9),(60,_binary '',NULL,'2021-12-09','Gà ri là một loại gà đặc sản được nhiều người ưa chuộng và săn lùng hiện nay. Gà ri đã có từ lâu đời, gà ri thuần chủng này nuôi nhiều nhất ở khu vực miền Bắc và miền Trung của Việt Nam. Chúng được người dân chủ yếu nuôi dùng để lấy trứng hoặc thịt. Gì ri có đặc điểm là nhỏ nhưng thịt gà ri rất thơm, ngon, thịt mềm và ngọt chứ không giống như những giống gà công nghiệp siêu thịt nhưng nhạt.','https://res.cloudinary.com/lancloudshop/image/upload/v1639026427/qiagpvkdqcskcthqgnby.jpg','Gà gari sạch','2021-12-09',3,70,NULL,9),(61,_binary '',NULL,'2021-12-09','Thông tin chung về Xương đuôi lợn \nXương đuôi lợn có vị ngọt đậm đà và rất giàu chất dinh dưỡng như: Protein, glucid, lipid, sắt,... Đây là lựa chọn tuyệt với cho các món hầm rau củ, chẳng hạn như bí xanh, bí đỏ,...','https://res.cloudinary.com/lancloudshop/image/upload/v1639026467/hj10xze1kplsam33qbxr.jpg','Xương đuôi lợn','2021-12-09',3,71,2,4);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('CUS','CUSTOMER'),('DIRE','DIRECTOR'),('SHIP','SHIPPER'),('STAF','STAFF');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_rates`
--

DROP TABLE IF EXISTS `shipping_rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_rates` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cod_supported` bit(1) DEFAULT NULL,
  `days` int NOT NULL,
  `rate` float NOT NULL,
  `state` varchar(45) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKef7sfgeybt3xn13nlt2j6sljw` (`country_id`),
  CONSTRAINT `FKef7sfgeybt3xn13nlt2j6sljw` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_rates`
--

LOCK TABLES `shipping_rates` WRITE;
/*!40000 ALTER TABLE `shipping_rates` DISABLE KEYS */;
INSERT INTO `shipping_rates` VALUES (1,_binary '',1,4,'Hải Châu',1),(2,_binary '',2,5,'Thanh Khê',1),(3,_binary '',1,3,'Sơn Trà',1),(4,_binary '\0',3,7,'Ngũ Hành Sơn',1),(5,_binary '\0',4,9,'Liên Chiểu',1),(6,_binary '',6,15,'Hòa Vang',1),(7,_binary '',2,13,'Cẩm Lệ',1),(8,_binary '',5,20,'Hoàng Sa',1),(9,_binary '\0',2,12,'Thành phố Tam Kỳ',2),(10,_binary '\0',2,12,'Huyện Tây Giang',2),(11,_binary '\0',2,12,'Huyện Đông Giang',2),(13,_binary '\0',2,12,'Huyện Đại Lộc',2),(14,_binary '\0',2,12,'Thị xã Điện Bàn',2),(15,_binary '\0',2,12,'Huyện Duy Xuyên',2),(16,_binary '\0',2,12,'Huyện Quế Sơn',2),(18,_binary '\0',2,12,'Huyện Nam Giang',2),(19,_binary '\0',2,12,'Huyện Phước Sơn',2),(20,_binary '\0',2,12,'Huyện Hiệp Đức',2),(21,_binary '\0',2,12,'Huyện Thăng Bình',2),(22,_binary '\0',2,12,'Huyện Tiên Phước',2),(23,_binary '\0',2,12,'Huyện Bắc Trà My',2),(24,_binary '\0',2,12,'Huyện Nam Trà My',2),(25,_binary '\0',2,12,'Huyện Núi Thành',2),(26,_binary '\0',2,12,'Huyện Phú Ninh',2);
/*!40000 ALTER TABLE `shipping_rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKskkdphjml9vjlrqn4m5hi251y` (`country_id`),
  CONSTRAINT `FKskkdphjml9vjlrqn4m5hi251y` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,'Hải Châu',1),(2,'Thanh Khê',1),(3,'Sơn Trà',1),(4,'Ngũ Hành Sơn',1),(5,'Liên Chiểu',1),(6,'Hòa Vang',1),(7,'Cẩm Lệ',1),(8,'Hoàng Sa',1),(9,'Thành phố Tam Kỳ',2),(11,'Huyện Tây Giang',2),(12,'Huyện Đông Giang',2),(13,'Huyện Đại Lộc',2),(14,'Thị xã Điện Bàn',2),(15,'Huyện Duy Xuyên',2),(16,'Huyện Quế Sơn',2),(17,'Huyện Nam Giang',2),(18,'Huyện Phước Sơn',2),(19,'Huyện Hiệp Đức',2),(20,'Huyện Thăng Bình',2),(21,'Huyện Tiên Phước',2),(22,'Huyện Bắc Trà My',2),(23,'Huyện Nam Trà My',2),(24,'Huyện Núi Thành',2),(25,'Huyện Phú Ninh',2);
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic_revenue`
--

DROP TABLE IF EXISTS `statistic_revenue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistic_revenue` (
  `year` int NOT NULL,
  `mouth` int DEFAULT NULL,
  `revenue` double DEFAULT NULL,
  PRIMARY KEY (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic_revenue`
--

LOCK TABLES `statistic_revenue` WRITE;
/*!40000 ALTER TABLE `statistic_revenue` DISABLE KEYS */;
/*!40000 ALTER TABLE `statistic_revenue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(256) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (4,'378 đồng nai','2021-12-08 19:29:59','Nhà vườn Đồng Nai','+84378795129','2021-12-08 19:29:59'),(5,'huỳnh thúc kháng','2021-12-08 19:30:26','Nhà vường Quảng Nam','+84378795129','2021-12-08 19:30:26'),(7,'09 Nguyễn thị tập, đại lộc , quảng nam','2021-12-08 21:25:25','Nhà Vường Xuân Lan','0789445577','2021-12-08 21:25:25'),(8,'64 Trần Quốc Toản, Q. Hải Châu, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:27:36','Công Ty TNHH MTV Thực Phẩm & Đầu Tư Fococev','02363822720','2021-12-08 21:27:36'),(9,'LÔ B1.5, KDC Bình An, P.Hòa Cường Bắc, Q.Hải Châu, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:28:19','Công Ty Cổ Phần Ngon Sạch Bổ','02363611229','2021-12-08 21:28:19'),(10,'Số 75 Lê Ngân, P. Khuê Trung, Q. Cẩm Lệ, Tp. Đà Nẵng, Việt Na','2021-12-08 21:28:54','Công Ty TNHH DASUN','05116568668','2021-12-08 21:28:54'),(11,'Tổ 6, Thôn Dương Sơn,X. Hòa Châu, H. Hòa Vang, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:29:31','Đam San - Công Ty TNHH Một Thành Viên Đam San','02363621686','2021-12-08 21:29:31'),(12,'16A Lý Thường Kiệt, Q. Hải Châu, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:30:12','Công Ty Cổ Phần Lương Thực Đà Nẵng','02363821036','2021-12-08 21:30:12'),(13,'91 Trần Quốc Toản, Q. Hải Châu, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:30:46','Trung Tiến - Công Ty TNHH Trung Tiến','02363873277','2021-12-08 21:30:46'),(14,'Khu Công Nghiệp Hòa Khánh, Lô 2,Q. Liên Chiểu, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:31:11','Doanh Nghiệp Tư Nhân Toàn Ni','02363738258','2021-12-08 21:31:11'),(15,'129A Phan Thanh, Q. Thanh Khê, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:31:39','Hảo Tường - Công Ty TNHH Hảo Tường','02363650696','2021-12-08 21:31:39'),(16,'11 Nguyễn Thiện Thuật, P. Bình Thuận, Q. Hải Châu, Tp. Đà Nẵng, Việt Nam','2021-12-08 21:32:00','Mê Kông - Công Ty TNHH Mê Kông','02363621772','2021-12-08 21:32:00');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `units` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units`
--

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
INSERT INTO `units` VALUES (1,'2021-12-08 19:46:27','Ki lo gram','Kg','2021-12-08 19:46:27'),(2,'2021-12-08 19:46:39','Hạt','Hạt','2021-12-08 19:46:39'),(4,'2021-12-08 19:47:11','Hộp','Hộp','2021-12-08 19:47:11'),(5,'2021-12-08 19:47:35','gram','Gram','2021-12-08 19:47:35'),(6,'2021-12-08 19:48:15','Thùng','Thùng','2021-12-08 19:48:15'),(7,'2021-12-08 19:48:38','cái','Cái','2021-12-08 19:48:38'),(9,'2021-12-09 12:05:14','tính đơn vị theo con','Nguyên Con','2021-12-09 12:05:14');
/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-09 16:00:57
