CREATE TABLE `artists` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year_of_birth` int(11) DEFAULT NULL,
  `year_of_death` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `artists` VALUES (0,'Abbey, Edwin Austin',1852,1911),(1,'Abbott, Lemuel Francis',1760,1803),(2,'Agasse, Jacques Laurent',1767,1849),(3,'Alken, Henry Thomas',1785,1851);
