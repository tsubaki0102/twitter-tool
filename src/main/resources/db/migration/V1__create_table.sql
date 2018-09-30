CREATE TABLE `tweet` (
  `session_time` DATETIME NOT NULL,
  `id` CHAR(19) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `retweet_count` INTEGER NOT NULL,
  `favorite_count` INTEGER NOT NULL,
  PRIMARY KEY (`session_time`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
