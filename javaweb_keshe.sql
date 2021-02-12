/*
 Navicat Premium Data Transfer

 Source Server         : 啊这
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : javaweb_keshe

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 12/02/2021 23:59:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `admin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '2021-01-01 19:34:43', '1402359854@qq.com', '驼君', 'e10adc3949ba59abbe56e057f20f883e', '13258010895');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评价id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `information` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `type` int(0) NULL DEFAULT NULL COMMENT '评价等级  1 2 3 4 5 由低到高',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_user`(`user_id`) USING BTREE,
  CONSTRAINT `user_id_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 6, 'demoData', 1);
INSERT INTO `comment` VALUES (2, 6, 'demoData', 2);
INSERT INTO `comment` VALUES (3, 6, 'demoData', 1);
INSERT INTO `comment` VALUES (4, 10, 'demoData', 1);
INSERT INTO `comment` VALUES (5, 6, '123', 5);
INSERT INTO `comment` VALUES (6, 6, '啊啊啊', 5);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `room_id` int(0) NULL DEFAULT NULL COMMENT '房间id',
  `in_time` datetime(0) NULL DEFAULT NULL COMMENT '入住时间',
  `leave_time` datetime(0) NULL DEFAULT NULL COMMENT '离开时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `real_price` double(255, 2) NULL DEFAULT NULL COMMENT '实际价格',
  `real_people` int(0) NULL DEFAULT NULL COMMENT '实际入住人数',
  `fapiao` int(0) NULL DEFAULT NULL COMMENT '是否需要发票',
  `flag` int(0) NULL DEFAULT NULL COMMENT '该订单是否被处理  0：未处理  1：办理入住  2：退订',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '房间id',
  `number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间号',
  `type` int(0) NOT NULL COMMENT '类型',
  `state` int(0) NOT NULL COMMENT '状态   0：空闲  1:已用',
  `max_people` int(0) NOT NULL COMMENT '房间最大人数',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间详细介绍',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_type_id`(`type`) USING BTREE,
  CONSTRAINT `type_type_id` FOREIGN KEY (`type`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '201', 1, 0, 1, '面积:14m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (2, '202', 1, 0, 1, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (3, '203', 1, 0, 1, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (4, '204', 1, 0, 1, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (5, '205', 1, 0, 1, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (6, '301', 2, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (7, '302', 2, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (8, '303', 2, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (9, '304', 2, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (10, '305', 2, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (11, '401', 3, 0, 3, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (12, '402', 3, 0, 3, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (13, '403', 3, 0, 3, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (14, '404', 3, 0, 3, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (15, '405', 3, 0, 3, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (16, '501', 4, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (17, '502', 4, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (18, '503', 4, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (19, '504', 4, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (20, '505', 4, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (21, '601', 5, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (22, '602', 5, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (23, '603', 5, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (24, '604', 5, 1, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');
INSERT INTO `room` VALUES (25, '605', 5, 0, 2, '面积:15m²,容纳:2-3人,电脑:有,热水:有,WIFI:有,电视:有,早餐:有');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `room_id` int(0) NULL DEFAULT NULL COMMENT '房间id',
  `in_time` datetime(0) NULL DEFAULT NULL COMMENT '入住时间',
  `leave_time` datetime(0) NULL DEFAULT NULL COMMENT '离开时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `real_price` double(10, 2) NULL DEFAULT NULL COMMENT '实际价格',
  `real_people` int(0) NULL DEFAULT NULL COMMENT '实际入住人数',
  `fapiao` int(0) NULL DEFAULT NULL COMMENT '是否需要发票',
  `flag` int(0) NULL DEFAULT NULL COMMENT '该订单是否被处理  0：未处理  1：办理入住  2：退订  3：订单已完成且过期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_user_id`(`user_id`) USING BTREE,
  INDEX `room_room_id`(`room_id`) USING BTREE,
  CONSTRAINT `room_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (2, 6, 25, '2021-01-04 00:00:00', '2021-01-10 00:00:00', '2021-01-01 15:35:55', 588.00, 1, 1, 0);
INSERT INTO `t_order` VALUES (7, 10, 17, '2021-01-03 00:00:00', '2021-01-04 00:00:00', '2021-01-03 12:55:11', 388.00, 1, 1, 0);
INSERT INTO `t_order` VALUES (8, 6, 21, '2021-01-04 22:24:00', '2021-01-05 22:24:00', '2021-01-04 14:24:56', 588.00, 1, 1, 0);
INSERT INTO `t_order` VALUES (14, 6, 24, '2020-12-30 08:00:00', '2021-01-15 08:00:00', '2021-01-07 10:40:57', 9408.00, 2, 1, 0);
INSERT INTO `t_order` VALUES (15, 6, 1, '2021-01-07 18:41:00', '2021-01-22 08:00:00', '2021-01-07 10:41:35', 2820.00, 1, 1, 0);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '房型id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房型名称',
  `price` double(10, 2) NOT NULL COMMENT '价格',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简介',
  `feature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '特点',
  `cover_image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '封面图',
  `detail_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '详细链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '单人房', 188.00, '14㎡-15㎡', '“舒适、简洁”', 'https://s3.ax1x.com/2021/01/06/sEXDVe.jpg', 'unity.abohelloworld.top');
INSERT INTO `type` VALUES (2, '双人房', 288.00, '20㎡-30㎡', '“舒适、简洁”', 'https://s3.ax1x.com/2021/01/06/sEXc8I.jpg', '../unity/DoublePersonHotel/DoublePersonHotel/index.html');
INSERT INTO `type` VALUES (3, '三人房', 488.00, '30㎡-40㎡', '“舒适、简洁”', 'https://s3.ax1x.com/2021/01/06/sEXWKf.jpg', '../unity/ThreePersonHotel/ThreePersonHotel/index.html');
INSERT INTO `type` VALUES (4, '大床房', 388.00, '20㎡-30㎡', '“舒适、简洁”', 'https://s3.ax1x.com/2021/01/06/sEXhqS.jpg', '../unity/BigBedPersonHotel/BigBedPersonHotel/index.html');
INSERT INTO `type` VALUES (5, '豪华套房', 588.00, '约50㎡', '“舒适、豪华”', 'https://s3.ax1x.com/2021/01/06/sEX7Ps.jpg', '../unity/LuxuryHotel/LuxuryHotel/index.html');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `create_time` datetime(0) NOT NULL COMMENT '用户注册时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` int(0) NOT NULL COMMENT '性别  0：女  1：男',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `jifen` int(0) NOT NULL COMMENT '积分',
  `state` int(0) NOT NULL COMMENT '状态：0：在该店没有消费过  1：在该店消费过\r\n',
  PRIMARY KEY (`id`, `email`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, '2021-01-07 00:39:57', '1402359854@qq.com', '驼君', 'e10adc3949ba59abbe56e057f20f883e', 0, '13258010895', 9408, 1);
INSERT INTO `user` VALUES (10, '2021-01-03 12:55:11', '1066261401@qq.com', '水墨清尘', '202cb962ac59075b964b07152d234b70', 0, '19861407837', 0, 1);
INSERT INTO `user` VALUES (11, '2021-01-04 19:15:05', 'ssdrt0400@qq.com', 'ShawnZhou', '202cb962ac59075b964b07152d234b70', 1, '13053876265', 0, 0);
INSERT INTO `user` VALUES (12, '2021-01-06 13:34:00', 'zx21000412@163.com', 'Test11', 'd9b1d7db4cd6e70935368a1efb10e377', 1, '19861407867', 133, 1);

SET FOREIGN_KEY_CHECKS = 1;
