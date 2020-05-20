create database seckill character set utf8mb4;

-- table `user`
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(30) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(50) default null,
  `age` int(11) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `password_salt` varchar(200) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
      `id` bigint(20) NOT NULL,
      `goods_name` varchar(255) DEFAULT '',
      `goods_title` varchar(255) DEFAULT '',
      `goods_img` varchar(255) DEFAULT '',
      `goods_detail` longtext,
      `goods_price` decimal(10, 2),
      `goods_stock` int(255),
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'iPhoneX', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', 'https://img10.360buyimg.com/n1/s450x450_jfs/t7297/154/3413903491/65679/45ae4902/59e42830N9da56c41.jpg', '超视网膜显示屏，两款大作一起登场。 它们经过精心定制，是 iPhone 色彩精准度最高的 OLED 显示屏，支持 HDR 显示，并能呈现真实深邃的黑色。其中，iPhone XS Max 更拥有 iPhone 迄今最大的显示屏', 8765.00, 10000);
INSERT INTO `goods` VALUES (2, 'Mate 20', '华为 HUAWEI Mate 20 Pro (UD)屏内指纹版麒麟980芯片全面屏超大广角徕卡三摄8GB+128GB亮黑色全网通双4G手机', 'https://img14.360buyimg.com/n0/jfs/t25288/359/1939640863/406912/ff77e158/5bbf17f0N428d505a.jpg', '屏内指纹版麒麟980芯片全面屏超大广角徕卡三摄8GB+128GB亮黑色全网通双4G手机', 5999.00, 8000);

-- ----------------------------
-- Table structure for miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods`  (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `goods_id` bigint(20),
      `miaosha_price` decimal(10, 2),
      `stock_count` int(11),
      `start_date` datetime(0),
      `end_date` datetime(0),
      PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of miaosha_goods
-- ----------------------------
INSERT INTO `miaosha_goods` VALUES (1, 1, 0.01, 298, '2019-01-23 11:54:41', '2019-03-07 11:54:45');
INSERT INTO `miaosha_goods` VALUES (2, 2, 0.03, 263, '2019-03-05 10:43:53', '2019-03-08 10:44:02');

-- ----------------------------
-- Table structure for miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
      `user_id` bigint(20),
      `goods_id` bigint(20),
      `order_id` bigint(20),
      PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of miaosha_order
-- ----------------------------
INSERT INTO `miaosha_order` VALUES (00000000000000000007, 18912341234, 2, 6);
INSERT INTO `miaosha_order` VALUES (00000000000000000006, 18912341234, 2, 5);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
   `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) NULL DEFAULT NULL,
   `goods_id` bigint(20) NULL DEFAULT NULL,
   `delivery_addr_id` bigint(20) NULL DEFAULT NULL,
   `goods_name` varchar(255) DEFAULT NULL,
   `goods_count` int(255) NULL DEFAULT NULL,
   `goods_price` decimal(10, 2) NULL DEFAULT NULL,
   `order_channel` tinyint(255) NULL DEFAULT NULL,
   `status` tinyint(255) NULL DEFAULT NULL,
   `create_date` datetime(0) NULL DEFAULT NULL,
   `pay_date` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (00000000000000000002, 18912341234, 1, NULL, 'iPhoneX', 1, 0.01, 1, 0, '2019-03-05 08:28:50', NULL);
INSERT INTO `order_info` VALUES (00000000000000000003, 18912341234, 1, NULL, 'iPhoneX', 1, 0.01, 1, 0, '2019-03-05 08:33:52', NULL);
INSERT INTO `order_info` VALUES (00000000000000000004, 18912341234, 2, NULL, 'Mate 20', 1, 0.03, 1, 0, '2019-03-06 02:46:05', NULL);
INSERT INTO `order_info` VALUES (00000000000000000005, 18912341234, 2, NULL, 'Mate 20', 1, 0.03, 1, 0, '2019-03-06 03:05:13', NULL);
INSERT INTO `order_info` VALUES (00000000000000000006, 18912341234, 2, NULL, 'Mate 20', 1, 0.03, 1, 0, '2019-03-06 09:09:38', NULL);
