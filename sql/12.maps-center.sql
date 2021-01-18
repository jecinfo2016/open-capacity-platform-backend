/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.29 : Database - gisdata
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gisdata` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gisdata`;

/*Table structure for table `gis_config` */

DROP TABLE IF EXISTS `gis_config`;

CREATE TABLE `gis_config` (
  `app_id` int(11) NOT NULL COMMENT '应用ID',
  `amap_key` varchar(55) DEFAULT NULL COMMENT '高德地图key',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `gis_config` */

insert  into `gis_config`(`app_id`,`amap_key`) values (1,'a3905d3985de06d3cf69aa47435a96f8');

/*Table structure for table `gis_pipeline_style` */

DROP TABLE IF EXISTS `gis_pipeline_style`;

CREATE TABLE `gis_pipeline_style` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pipe_line_color` varchar(20) DEFAULT NULL COMMENT '管道颜色',
  `diameter_show` int(11) DEFAULT NULL COMMENT '直径',
  `material_show` int(11) DEFAULT NULL COMMENT '材质',
  `level` int(11) DEFAULT NULL COMMENT '显示缩放级别',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `gis_pipeline_style` */

insert  into `gis_pipeline_style`(`id`,`pipe_line_color`,`diameter_show`,`material_show`,`level`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'#0000',1,1,2,'lkq','2020-05-12 15:11:09','lkq','2020-05-15 11:45:03','测试数据');

/*Table structure for table `position_data` */

DROP TABLE IF EXISTS `position_data`;

CREATE TABLE `position_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `longitude` decimal(16,12) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(16,12) DEFAULT NULL COMMENT '纬度',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `position_data` */

insert  into `position_data`(`id`,`type`,`name`,`longitude`,`latitude`,`create_time`) values (1,1,'测试数据1','26.000000000000','45.000000000000','2020-05-12 16:40:52'),(2,2,'测试数据2','36.000000000000','10.000000000000','2020-05-12 16:41:11');

/*Table structure for table `position_type` */

DROP TABLE IF EXISTS `position_type`;

CREATE TABLE `position_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `position_type` */

insert  into `position_type`(`id`,`name`) values (1,'测试类型1'),(2,'测试类型2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*[12:07:00][142 ms]*/ INSERT INTO `user-center`.`sys_permission` (`permission`, `name`, `createTime`, `updateTime`) VALUES ('gispipelinestyle:get/PipeLineStyle', '获取管线显示样式', '2020-05-16 12:06:56', '2020-05-16 12:06:58');
/*[12:20:21][124 ms]*/ INSERT INTO `user-center`.`sys_permission` (`permission`, `name`, `createTime`, `updateTime`) VALUES ('gispipelinestyle:post/gispipelinestyles', '保存管线样式', '2020-05-16 12:20:08', '2020-05-16 12:20:10');
/*[12:20:46][126 ms]*/ INSERT INTO `user-center`.`sys_permission` (`permission`, `name`, `createTime`, `updateTime`) VALUES ('gispipelinestyle:put/gispipelinestyles', '修改管线样式', '2020-05-16 12:20:31', '2020-05-16 12:20:33');
/*[12:20:59][125 ms]*/ INSERT INTO `user-center`.`sys_permission` (`permission`, `name`, `createTime`, `updateTime`) VALUES ('gispipelinestyle:delete/gispipelinestyle/{id}', '根据id删除管线样式', '2020-05-16 12:20:54', '2020-05-16 12:20:55');
