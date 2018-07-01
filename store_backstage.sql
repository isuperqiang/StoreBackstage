/*
 Navicat Premium Data Transfer

 Source Server         : richie mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : store_backstage

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 07/01/2018 11:23:50 AM
*/

CREATE DATABASE `store_backstage` DEFAULT CHARACTER SET utf8;
use `store_backstage`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sequence` int(20) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `category_name_uindex` (`name`),
  KEY `category_user` (`user_id`),
  CONSTRAINT `category_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `category`
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES ('2', '好吃的', '2018-06-26 11:15:50', '1', '1'), ('5', '电子产品', '2018-06-27 13:20:42', '3', '1'), ('6', '油盐酱醋', '2018-06-27 15:39:25', '4', '1'), ('8', '酒水', '2018-06-27 16:12:57', '6', '1'), ('9', '手机', '2018-06-27 16:13:03', '7', '1'), ('10', '电脑', '2018-06-27 16:13:07', '8', '1'), ('13', '冲饮冰镇', '2018-06-27 16:13:59', '0', '1'), ('14', '服装鞋帽', '2018-06-27 16:14:09', '12', '1'), ('15', '网红零食', '2018-06-27 16:15:37', '13', '1'), ('16', '婴儿用品', '2018-06-27 16:53:05', '14', '1'), ('20', '水果蔬菜', '2018-06-27 17:44:06', '18', '1'), ('21', '副食品', '2018-06-27 17:59:24', '19', '1'), ('22', '数码', '2018-06-27 18:29:37', '20', '1'), ('23', '方便速食', '2018-06-27 19:05:23', '21', '1'), ('25', '夏季蔬菜', '2018-06-29 15:24:17', '22', '1');
COMMIT;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(20) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `specification` varchar(30) NOT NULL DEFAULT '',
  `picture` varchar(100) NOT NULL DEFAULT '',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `cost` decimal(10,2) NOT NULL DEFAULT '0.00',
  `sale_volume` int(10) NOT NULL DEFAULT '0',
  `stock` int(10) NOT NULL DEFAULT '0',
  `on_sale` tinyint(1) NOT NULL DEFAULT '1',
  `user_id` int(11) DEFAULT NULL,
  `cat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  UNIQUE KEY `goods_gname_uindex` (`gname`),
  KEY `goods_user` (`user_id`),
  KEY `goods_category` (`cat_id`),
  CONSTRAINT `goods_category` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goods_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods`
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('2', '饮用水', '2018-06-26 15:22:20', '桶', '/upload/ecb5c4eeb69645e69e16225ca6d8a234.jpg', '6.00', '5.00', '100', '1122', '1', '1', '8'), ('4', '可乐', '2018-06-27 20:36:17', '瓶', '/upload/3e4cff790cd34deaaf987741bae226c6.png', '3.00', '2.00', '20', '220', '1', '1', '6'), ('5', '面包', '2018-06-27 20:37:01', '个', '', '6.00', '3.00', '456', '3330', '1', '1', '20'), ('6', '棒棒糖', '2018-06-27 20:37:35', '个', '', '1.00', '1.00', '2220', '430', '1', '1', '8'), ('7', '鸭脖', '2018-06-27 20:38:57', '个', '/upload/bf339e353d7b4ee484c2e09cd259b869.jpeg', '5.00', '1.00', '4454', '330', '1', '1', '8'), ('8', '冰淇淋', '2018-06-27 20:40:08', '个', '', '3.50', '2.00', '54', '545', '1', '1', '2'), ('10', '大饼', '2018-06-27 20:41:38', '个', '', '4.00', '1.00', '54', '34', '1', '1', '8'), ('11', '鸡蛋', '2018-06-27 20:45:38', '斤', '', '4.00', '3.00', '33', '33', '1', '1', '5'), ('12', '辣条', '2018-06-27 20:46:28', '包', '', '3.00', '2.00', '89', '8', '1', '1', '8'), ('13', '雪碧', '2018-06-27 20:46:53', '瓶', '', '4.00', '2.00', '89', '77', '1', '1', '6'), ('15', '方便面', '2018-06-27 20:48:38', '包', '', '5.00', '2.00', '98', '21', '1', '1', '2'), ('16', '奶茶', '2018-06-29 17:48:49', '杯', '', '2.00', '3.00', '12', '12', '1', '1', '6'), ('18', 'ff', '2018-06-30 17:15:21', 'd', '', '3.00', '-20.00', '33', '33', '1', '1', '8');
COMMIT;

-- ----------------------------
--  Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `card_no` varchar(20) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` varchar(8) NOT NULL DEFAULT '',
  `credit` int(11) NOT NULL DEFAULT '0',
  `order_count` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `member_user` (`user_id`),
  CONSTRAINT `member_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `member`
-- ----------------------------
BEGIN;
INSERT INTO `member` VALUES ('4', '落英坠露', '18328738478', '4387298748598737480', '2018-06-30 17:28:15', '男', '1', '0', '1'), ('5', '飞天将军', '18328348478', '5387298748598737480', '2018-06-30 17:32:37', '男', '20', '0', '1'), ('10', '小飞虾', '18328348478', '5387298748598737480', '2018-06-30 17:38:17', '女', '0', '0', '1'), ('11', '龙的传人', '18328348478', '5387298748598737480', '2018-06-30 17:38:37', '女', '0', '0', '1');
COMMIT;

-- ----------------------------
--  Table structure for `store`
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `logo` varchar(100) NOT NULL DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  `category` varchar(20) NOT NULL DEFAULT '',
  `description` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `sale_from` varchar(20) NOT NULL DEFAULT '',
  `sale_to` varchar(20) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  `avg_price` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `store_name_uindex` (`name`),
  KEY `store_user` (`user_id`),
  CONSTRAINT `store_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `store`
-- ----------------------------
BEGIN;
INSERT INTO `store` VALUES ('4', '世纪超市', '/upload/0ff5c5201ce74e13b7362b34f6065f7b.png', '杭州市西湖区文一西路 12 号', '便利店', '欢迎来到小明超市，祝您购物愉快，么么哒～', '15822022321', '08:00:00', '20:00:00', '2018-06-25 22:03:58', '1', '10');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `last_visit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `phone` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone_uindex` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '', 'e10adc3949ba59abbe56e057f20f883e', '2018-06-30 16:25:52', '2018-06-25 17:32:07', '15812344321'), ('2', '', 'e10adc3949ba59abbe56e057f20f883e', '2018-06-25 17:36:16', '2018-06-25 17:36:16', '13812344321');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
