/*
 Navicat Premium Data Transfer

 Source Server         : AliYun
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : 39.105.127.244:3306
 Source Schema         : cet6

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 25/05/2023 16:33:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gender` bit(1) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `authority` int(0) NOT NULL DEFAULT 1,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `exam_id` int(0) NOT NULL AUTO_INCREMENT,
  `register_time` datetime(0) NOT NULL,
  `test_time` datetime(0) NOT NULL,
  `score_time` datetime(0) NOT NULL,
  `paper_a` int(0) NOT NULL,
  `paper_b` int(0) NOT NULL,
  `paper_c` int(0) NOT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE,
  INDEX `paper_a`(`paper_a`) USING BTREE,
  INDEX `paper_b`(`paper_b`) USING BTREE,
  INDEX `paper_c`(`paper_c`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `paper_id` int(0) NOT NULL,
  `question_id` int(0) NOT NULL,
  `question` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`paper_id`, `question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration`  (
  `registration_id` int(0) NOT NULL,
  `exam_id` int(0) NOT NULL,
  `paper_id` int(0) NOT NULL,
  `stu_id` int(0) NOT NULL,
  `register_time` datetime(0) NOT NULL,
  `paid` bit(1) NOT NULL DEFAULT b'0',
  `score_listen` int(0) NULL DEFAULT 0,
  `score_read` int(0) NULL DEFAULT NULL,
  `score_write` int(0) NULL DEFAULT NULL,
  `writing` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `translation` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  PRIMARY KEY (`registration_id`) USING BTREE,
  INDEX `stu_id`(`stu_id`) USING BTREE,
  INDEX `exam_id`(`exam_id`) USING BTREE,
  CONSTRAINT `exam_id` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stu_id` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gender` bit(1) NOT NULL,
  `id_card` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `school` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `cet4` int(0) NULL DEFAULT NULL,
  `cet6` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`) USING BTREE,
  INDEX `school`(`school`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gender` bit(1) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
