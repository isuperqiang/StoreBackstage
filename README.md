## StoreBackstage

Backstage management system for retail store.


----

建表语句：
```
create database `store_backstage`

create table `user`(
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `last_visit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `phone` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8


create table `store`(
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `logo` varchar(100) NOT NULL DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  `category` varchar(20) NOT NULL DEFAULT '',
  `description` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `sale_from` varchar(20) NOT NULL DEFAULT '',
  `sale_to` varchar(20) NOT NULL DEFAULT '',
  `avg_price` varchar(10) NOT NULL DEFATLT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id_` int(11),
  PRIMARY KEY (`store_id`),
  CONSTRAINT `store_user` FOREIGN KEY (`user_id_`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8


create table `category`(
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sequence` int(20) NOT NULL DEFAULT 0,
  `user_id_` int(11),
  PRIMARY KEY (`cat_id`),
  CONSTRAINT `category_user` FOREIGN KEY (`user_id_`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8


```