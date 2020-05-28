-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: seckill
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB-0+deb9u1

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
                         `id` bigint(20) NOT NULL,
                         `goods_name` varchar(255) DEFAULT '',
                         `goods_title` varchar(255) DEFAULT '',
                         `goods_img` varchar(255) DEFAULT '',
                         `goods_detail` longtext,
                         `goods_price` decimal(10,2) DEFAULT NULL,
                         `goods_stock` int(255) DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `goods`
--
INSERT INTO `goods` VALUES (1,'iPhoneX','Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机','https://img10.360buyimg.com/n1/s450x450_jfs/t7297/154/3413903491/65679/45ae4902/59e42830N9da56c41.jpg','超视网膜显示屏，两款大作一起登场。 它们经过精心定制，是 iPhone 色彩精准度最高的 OLED 显示屏，支持 HDR 显示，并能呈现真实深邃的黑色。其中，iPhone XS Max 更拥有 iPhone 迄今最大的显示屏',8765.00,10000),
                           (2,'Mate 20','华为 HUAWEI Mate 20 Pro (UD)屏内指纹版麒麟980芯片全面屏超大广角徕卡三摄8GB+128GB亮黑色全网通双4G手机','https://img14.360buyimg.com/n0/jfs/t25288/359/1939640863/406912/ff77e158/5bbf17f0N428d505a.jpg','屏内指纹版麒麟980芯片全面屏超大广角徕卡三摄8GB+128GB亮黑色全网通双4G手机',5999.00,8000);


--
-- Table structure for table `miaosha_goods`
--

DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `goods_id` bigint(20) DEFAULT NULL,
                                 `miaosha_price` decimal(10,2) DEFAULT NULL,
                                 `stock_count` int(11) DEFAULT NULL,
                                 `start_date` datetime DEFAULT NULL,
                                 `end_date` datetime DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `miaosha_goods`
--
INSERT INTO `miaosha_goods` VALUES (1,1,0.01,298,'2019-01-23 11:54:41','2019-03-07 11:54:45'),
                                   (2,2,0.03,263,'2019-03-05 10:43:53','2019-03-08 10:44:02');

--
-- Table structure for table `miaosha_order`
--

DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                 `user_id` int(11) DEFAULT NULL,
                                 `goods_id` bigint(20) DEFAULT NULL,
                                 `order_id` bigint(20) DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `miaosha_order`
--
INSERT INTO `miaosha_order` VALUES (6,2,2,5),(7,2,2,6);


--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
                              `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                              `user_id` int(11) DEFAULT NULL,
                              `goods_id` bigint(20) DEFAULT NULL,
                              `delivery_addr_id` int(20) DEFAULT NULL,
                              `goods_name` varchar(255) DEFAULT NULL,
                              `goods_count` int(255) DEFAULT NULL,
                              `goods_price` decimal(10,2) DEFAULT NULL,
                              `order_channel` tinyint(255) DEFAULT NULL,
                              `status` tinyint(255) DEFAULT NULL,
                              `create_date` datetime DEFAULT NULL,
                              `pay_date` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_info`
--

INSERT INTO `order_info` VALUES (2,2,1,NULL,'iPhoneX',1,0.01,1,0,'2019-03-05 08:28:50',NULL),
                                (3,2,1,NULL,'iPhoneX',1,0.01,1,0,'2019-03-05 08:33:52',NULL),
                                (4,2,2,NULL,'Mate 20',1,0.03,1,0,'2019-03-06 02:46:05',NULL),
                                (5,2,2,NULL,'Mate 20',1,0.03,1,0,'2019-03-06 03:05:13',NULL),
                                (6,2,2,NULL,'Mate 20',1,0.03,1,0,'2019-03-06 09:09:38',NULL);

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `nick_name` varchar(30) DEFAULT NULL,
                        `phone` varchar(11) DEFAULT NULL,
                        `age` int(11) DEFAULT NULL,
                        `password` varchar(200) DEFAULT NULL,
                        `password_salt` varchar(200) DEFAULT NULL,
                        `create_date` datetime NOT NULL,
                        `email` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--
INSERT INTO `user` VALUES (2,'耿','18401170921',0,'o0P7mVO3AkuhPHTdKlFDfg==','3RLzQIPYOe+yS6eD5ucxgg==','2020-05-09 07:13:55',NULL),
                          (3,'123','18401170920',0,'KMlVGF3WGNEIRmzsjqOClw==','hT6DlMCRVs8zkA+o09WNlA==','2020-05-09 07:18:00',NULL);

