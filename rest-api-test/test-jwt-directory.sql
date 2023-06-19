CREATE DATABASE  IF NOT EXISTS `test-jwt`;
USE `test-jwt`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `user`
-- password sudah di enkrip
-- nilai nya ridwan123
--

INSERT INTO `user` VALUES 
	(1,'ahmad.ridwan','$2a$10$EqBB3MysEPWXQgxRGNDQperQEyaN3ZIvtsNTgJqEesHs1RSFkCs/O','ADMIN'),
