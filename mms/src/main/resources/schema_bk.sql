DROP TABLE IF EXISTS `cities`;
DROP TABLE IF EXISTS `states`;
DROP TABLE IF EXISTS `countries`;
DROP TABLE IF EXISTS `subregions`;
DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regions` (
  `region_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(100)  NOT NULL,
  `translations` text ,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(1) NOT NULL DEFAULT '1',
  `wikiDataId` varchar(255)  DEFAULT NULL COMMENT 'Rapid API GeoDB Cities',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7;


DROP TABLE IF EXISTS `subregions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subregions` (
  `subregion_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(100)  NOT NULL,
  `translations` text ,
  `region_id` INT NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(1) NOT NULL DEFAULT '1',
  `wikiDataId` varchar(255)  DEFAULT NULL COMMENT 'Rapid API GeoDB Cities',
  PRIMARY KEY (`subregion_id`),
  KEY `subregion_continent` (`region_id`),
  CONSTRAINT `subregion_continent_final` FOREIGN KEY (`region_id`) REFERENCES `regions` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 ;


DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `country_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(100)  NOT NULL,
  `iso3` char(3)  DEFAULT NULL,
  `numeric_code` char(3)  DEFAULT NULL,
  `iso2` char(2)  DEFAULT NULL,
  `phonecode` varchar(255)  DEFAULT NULL,
  `capital` varchar(255)  DEFAULT NULL,
  `currency` varchar(255)  DEFAULT NULL,
  `currency_name` varchar(255)  DEFAULT NULL,
  `currency_symbol` varchar(255)  DEFAULT NULL,
  `tld` varchar(255)  DEFAULT NULL,
  `native` varchar(255)  DEFAULT NULL,
  `region` varchar(255)  DEFAULT NULL,
  `region_id` INT DEFAULT NULL,
  `subregion` varchar(255)  DEFAULT NULL,
  `subregion_id` INT DEFAULT NULL,
  `nationality` varchar(255)  DEFAULT NULL,
  `timezones` text ,
  `translations` text ,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `emoji` varchar(191)  DEFAULT NULL,
  `emojiU` varchar(191)  DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(1) NOT NULL DEFAULT '1',
  `wikiDataId` varchar(255)  DEFAULT NULL COMMENT 'Rapid API GeoDB Cities',
  PRIMARY KEY (`country_id`),
  KEY `country_continent` (`region_id`),
  KEY `country_subregion` (`subregion_id`),
  CONSTRAINT `country_continent_final` FOREIGN KEY (`region_id`) REFERENCES `regions` (`region_id`),
  CONSTRAINT `country_subregion_final` FOREIGN KEY (`subregion_id`) REFERENCES `subregions` (`subregion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=251 ;



DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `state_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NOT NULL,
  `country_id` INT NOT NULL,
  `country_code` char(2)  NOT NULL,
  `fips_code` varchar(255)  DEFAULT NULL,
  `iso2` varchar(255)  DEFAULT NULL,
  `type` varchar(191)  DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(1) NOT NULL DEFAULT '1',
  `wikiDataId` varchar(255)  DEFAULT NULL COMMENT 'Rapid API GeoDB Cities',
  PRIMARY KEY (`state_id`),
  KEY `country_region` (`country_id`),
  CONSTRAINT `country_region_final` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5324 ;





DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NOT NULL,
  `state_id` INT NOT NULL,
  `state_code` varchar(255)  NOT NULL,
  `country_id`INT NOT NULL,
  `country_code` char(2)  NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '2014-01-01 12:01:01',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(1) NOT NULL DEFAULT '1',
  `wikiDataId` varchar(255)  DEFAULT NULL COMMENT 'Rapid API GeoDB Cities',
  PRIMARY KEY (`city_id`),
  KEY `cities_test_ibfk_1` (`state_id`),
  KEY `cities_test_ibfk_2` (`country_id`),
  CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`state_id`) REFERENCES `states` (`state_id`),
  CONSTRAINT `cities_ibfk_2` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=154475 ;