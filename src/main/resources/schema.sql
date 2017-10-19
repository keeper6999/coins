CREATE TABLE IF NOT EXISTS `coins` (
  `user_id` bigint(20) NOT NULL,
  `coins` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;