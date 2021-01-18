/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.40-log : Database - user-center
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user-center` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `user-center`;

/*Table structure for table `order_seq` */

DROP TABLE IF EXISTS `order_seq`;

CREATE TABLE `order_seq` (
  `seq_name` varchar(50) NOT NULL,
  `current_val` int(11) NOT NULL,
  `max_val` int(4) NOT NULL,
  `increment_val` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_seq` */

insert  into `order_seq`(`seq_name`,`current_val`,`max_val`,`increment_val`) values ('oc_user',1,9999,1);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `css` varchar(100) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `isMenu` int(11) DEFAULT '1' COMMENT '是否菜单 1 是 2 不是',
  `hidden` int(11) DEFAULT '0' COMMENT '是否隐藏,0 false 1 true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parentId`,`name`,`url`,`path`,`css`,`sort`,`createTime`,`updateTime`,`isMenu`,`hidden`) values (2,12,'用户管理','#!user','/admin/user','user',2,'2017-11-17 16:56:59','2019-08-31 18:49:51',1,0),(3,12,'角色管理','#!role','/admin/role','user-circle',3,'2017-11-17 16:56:59','2018-09-02 06:12:04',1,0),(4,12,'菜单管理','#!menus','/admin/menu','th-list',4,'2017-11-17 16:56:59','2018-09-03 02:23:47',1,0),(5,12,'权限管理','#!permissions','/admin/auth','universal-access',5,'2017-11-17 16:56:59','2018-09-02 06:12:16',1,0),(7,37,'注册中心','#!register','/admin/eureka','share-alt',2,'2017-11-17 16:56:59','2019-05-25 20:34:50',1,0),(8,37,'监控中心','#!monitor','http://127.0.0.1:9001/#/wallboard','share-alt',11,'2017-11-17 16:56:59','2019-05-25 20:35:20',1,1),(9,37,'文件中心','#!files','/admin/file','share-alt',10,'2017-11-17 16:56:59','2018-08-25 10:43:33',1,0),(10,37,'文档中心','#!swagger','/admin/swagger','buysellads',9,'2017-11-17 16:56:59','2019-03-26 02:32:46',1,0),(11,12,'我的信息','#!myInfo','system/myInfo.html','share-alt',10,'2017-11-17 16:56:59','2018-09-02 06:12:24',1,1),(12,-1,'认证中心','javascript:;','','share-alt',1,'2017-11-17 16:56:59','2019-03-26 06:56:46',1,0),(35,12,'应用管理','#!app','attestation/app.html','share-alt',9,'2017-11-17 16:56:59','2018-08-25 10:57:42',1,1),(36,12,'服务管理','#!services','/admin/services','line-chart',8,'2017-11-17 16:56:59','2019-08-31 17:48:12',1,1),(37,-1,'系统监控','javascript:;','','share-alt',3,'2018-08-25 10:41:58','2018-08-25 10:41:58',1,0),(40,-1,'任务中心','javascript:;','','share-alt',4,'2018-08-28 16:59:44','2018-08-28 17:00:19',1,0),(41,40,'任务管理','#!jobinfo','/admin/jobinfo','share-alt',1,'2018-08-28 17:02:00','2018-08-28 18:24:23',1,0),(42,40,'调度日志','#!joblog','/admin/joblog','share-alt',2,'2018-08-28 18:20:53','2018-08-28 18:24:32',1,0),(43,40,'执行器管理','#!jobgroup','/admin/jobgroup','share-alt',3,'2018-08-28 18:22:04','2018-09-03 08:05:02',1,0),(44,37,'服务治理','#!eureka','/admin/governance','share-alt',1,'2018-08-30 15:30:19','2019-05-25 20:34:40',1,0),(50,37,'代码生成器','#!generator','generator/list.html','share-alt',999,'2018-09-05 13:43:06','2019-05-26 17:01:46',1,1),(52,12,'令牌管理','#!token','attestation/token.html','share-alt',11,'2018-09-08 13:19:56','2019-05-27 09:28:39',1,1),(105,37,'日志中心','#!log','/admin/log','share-alt',18,'2019-03-11 06:30:01','2019-05-25 20:35:35',1,0),(106,37,'grafana监控','#!grafana','http://127.0.0.1:3000/','share-alt',77,'2019-03-12 01:18:09','2019-03-26 02:33:00',1,1),(108,37,'prometheus监控','#!prometheus','http://127.0.0.1:9090','share-alt',1111,'2019-03-27 11:23:31','2019-05-25 21:13:08',1,1);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `menuId` int(50) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1156502143352848387 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`permission`,`name`,`menuId`,`createTime`,`updateTime`) values (1,'permission:post/permissions','保存权限标识',5,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(2,'permission:put/permissions','修改权限标识',5,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(3,'permission:delete/permissions/{id}','删除权限标识',5,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(4,'permission:get/permissions','查询权限标识',5,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(5,'role:post/roles','添加角色',3,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(6,'role:put/roles','修改角色',3,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(7,'role:delete/roles/{id}','删除角色',3,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(8,'role:post/roles/{id}/permissions','给角色分配权限',3,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(9,'role:get/roles','查询角色',3,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(10,'role:get/roles/{id}/permissions','获取角色的权限',3,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(11,'user:post/users/{id}/roles','给用户分配角色',2,'2018-01-18 17:06:39','2019-08-31 18:49:58'),(12,'user:post/users/{id}/resetPassword','用户重置密码',2,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(13,'user:get/users/{id}','用户查询',2,'2018-01-18 17:12:00','2018-01-18 17:12:05'),(14,'user:put/users/me','修改用户',2,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(15,'user:get/users/{id}/roles','获取用户的角色',2,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(16,'user:post/users/saveOrUpdate','新增用户',2,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(17,'user:post/users/exportUser','导出用户',2,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(18,'user:get/users/updateEnabled','用户状态修改',2,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(19,'user:put/users/password','修改密码',2,'2018-01-18 17:06:39','2018-01-18 17:06:39'),(20,'menu:get/menus/all','查询菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(21,'menu:post/menus/granted','给角色分配菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(22,'menu:get/menus/tree','树形显示',4,'2018-01-18 17:06:39','2018-01-18 17:06:39'),(23,'menu:get/menus/{roleId}/menus','获取角色的菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(24,'menu:post/menus','添加菜单',4,'2018-01-18 17:06:39','2018-09-04 07:35:29'),(25,'menu:put/menus','修改菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(26,'menu:delete/menus/{id}','删除菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(27,'menu:get/menus/current','当前用户菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(29,'menu:get/menus/findAlls','所有菜单',4,'2018-01-18 17:06:39','2018-01-18 17:06:39'),(30,'client:post/clients','保存应用',35,'2018-01-18 17:06:39','2018-01-18 17:06:39'),(31,'client:get/clients','应用列表',35,'2018-01-18 17:06:39','2018-01-18 17:06:39'),(32,'client:get/clients/{id}','根据id获取应用',35,'2018-01-18 17:06:39','2018-01-18 17:06:39'),(33,'clientdelete/clients','删除应用',35,'2018-01-18 17:06:39','2018-01-18 17:06:42'),(34,'service:get/service/findAlls','查询所有服务',36,'2018-01-18 17:06:39','2018-09-03 08:05:09'),(35,'service:get/service/findOnes','服务树',36,'2018-01-18 17:06:39','2018-09-08 03:23:28'),(36,'menu:get/menus/findOnes','获取菜单以及顶层菜单',4,'2019-05-09 23:48:13','2019-05-09 23:48:13'),(37,'permission:get/permissions/{roleId}/permissions','根据roleId获取权限',5,'2019-05-10 00:02:23','2019-05-10 00:02:23'),(38,'file:query','获取文件列表',9,'2019-05-17 21:34:05','2019-05-17 21:34:08'),(39,'file:del','删除文件',9,'2019-05-17 21:36:46','2019-05-17 21:36:48'),(40,'menu:get/menus/findMenusByRoleId/{roleId}','角色菜单',4,'2019-07-28 15:19:21','2019-07-28 15:19:24'),(41,'role:put/rolesMenus','修改角色菜单',3,'2019-07-28 17:21:56','2019-07-28 17:21:57'),(42,'role:get/menusPermission/current','角色菜单权限',3,'2019-07-28 22:04:44','2019-07-28 22:04:46'),(43,'role:get/menus/findPermissionByRoleId/{roleId}','角色权限',3,'2019-07-28 23:34:14','2019-07-28 23:34:16'),(44,'role:put/rolesPermission','角色菜单权限',3,'2019-07-29 11:13:38','2019-07-29 11:13:41'),(45,'role:findOne/roles/{id}','查询单个角色',3,'2019-07-29 14:34:38','2019-07-29 14:34:39'),(46,'menu:get/menus/findMenusByMenuId/{menuId}','查询单个菜单',4,'2019-07-29 18:27:38','2019-07-29 18:27:40'),(47,'permission:get/menus/preMenus','菜单权限',5,'2019-07-31 15:50:16','2019-07-31 15:50:18'),(1156502143352848386,'user:get/users/severs/{id}','1234',2,'2019-07-31 17:49:32','2019-07-31 18:04:12');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `desc` varchar(500) DEFAULT NULL COMMENT '说明',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`name`,`desc`,`createTime`,`updateTime`) values (1,'ADMIN','管理员',NULL,'2017-11-17 16:56:59','2017-11-17 16:56:59'),(3,'002','普通用户',NULL,'2019-03-27 02:52:00','2019-03-27 02:52:00'),(4,'003','运营管理员','1212','2019-07-29 14:08:06','2019-08-31 18:31:58');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`roleId`,`menuId`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,35),(1,36),(1,37),(1,40),(1,41),(1,42),(1,43),(1,44),(1,50),(1,52),(1,105),(1,106),(1,108),(3,2),(3,3),(3,4),(3,5),(3,7),(3,11),(3,12),(3,35),(3,36),(3,44),(3,50),(3,52),(4,2),(4,3),(4,4),(4,5),(4,11),(4,12),(4,35),(4,36),(4,52),(5,2),(5,3),(5,4),(5,5),(5,11),(5,12),(5,35),(5,36),(5,52);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`roleId`,`permissionId`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(3,1),(3,2),(3,3),(3,4),(3,37),(4,1),(4,3),(4,37);

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `userId` bigint(20) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_user` */

insert  into `sys_role_user`(`userId`,`roleId`) values (1,1),(1,3),(2,1),(3,1),(4,1),(7,3),(8,1),(9,1),(10,1),(11,1),(12,3),(13,1),(14,3),(15,3),(16,3),(17,4),(18,3);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(1024) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(16) NOT NULL DEFAULT 'BACKEND',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`headImgUrl`,`phone`,`email`,`sex`,`enabled`,`type`,`createTime`,`updateTime`) values (1,'admin','$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG','管理员','http://payo7kq4i.bkt.clouddn.com/耳机.jpg','13106975707',NULL,1,1,'BACKEND','2017-11-17 16:56:59','2018-09-15 03:12:44'),(2,'owen','$2a$10$4WkpmB1jHncBCrzJ7hJRq.SsiEFiyE/FdgPF26hLs8vzPyoNpZjta','欧文','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','18579068166',NULL,1,1,'APP','2017-11-17 16:56:59','2019-07-26 15:24:40'),(3,'user','$2a$10$fL/AfD4RDS0LxLJS7zpaZ.YUMfjNWKVvUn7oiA75L1K6PXazSTJPi','体验用户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','13147890045',NULL,1,0,'APP','2017-11-17 16:56:59','2018-09-07 13:38:34'),(4,'test','$2a$10$RD18sHNphJMmcuLuUX/Np.IV/7Ngbjd3Jtj3maFLpwaA6KaHVqPtq','测试账户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','13851539156',NULL,0,0,'APP','2017-11-17 16:56:59','2019-07-26 15:24:29'),(7,'useruser','$2a$10$Oar0D2I0yRaGocx71mP2zeKJPEec0bddQRrQsXM3ue52NPKrn5KQq','useruser',NULL,'18571111111',NULL,1,1,'APP','2018-09-03 09:57:12','2019-03-27 02:52:22'),(8,'abc','$2a$10$RII9blAhenwoFLjL1Y7kNOgq8xqUR/.o6SCDmfPbb6IAnZng/HsKa','abc',NULL,'13322332123',NULL,0,1,'APP','2018-09-03 03:32:52','2019-07-26 15:24:38'),(9,'jay','$2a$10$og3NMep2E4sJF90IzoyVre53A37APaNvbXXTJDhcjQkDuTHIe.wvO','jay',NULL,'15151515151',NULL,0,1,'APP','2018-09-06 02:30:51','2019-03-27 03:31:07'),(10,'testpre','$2a$10$ep9ukU/DELSKJHb6vbhUC.CJHFMQAgWCuWMAGLr2vZmIt8yar5EAa','testpre',NULL,'17791907897',NULL,1,1,'APP','2018-09-07 02:48:44','2019-03-26 07:12:10'),(11,'1','$2a$10$lQ5w8eRYFx4JYfS/zV6OM.IzIRf0rbyevUHFu.xQJtL7Bobc8AuY.','1',NULL,'13530151800',NULL,1,1,'APP','2018-09-07 14:20:51','2019-07-26 20:24:57'),(12,'12','$2a$10$cgRGZ0uuIAoKuwBoTWmz7eJzP4RUEr688VlnpZ4BTCz2RZEt0jrIe','12',NULL,'17587132062',NULL,0,1,'APP','2018-09-08 04:52:25','2019-07-26 15:24:35'),(13,'abc1','$2a$10$pzvn4TfBh2oFZJbtagovFe56ZTUlTaawPnx0Yz2PeqGex0xbddAGu','abc',NULL,'12345678901',NULL,0,1,'APP','2018-09-11 08:02:25','2019-07-26 15:24:36'),(14,'ceshis','$2a$10$wh0d8dn67WXCH6oNeDW3Q.NnJHiVUjEvLBOVUqjA2F/pn7cIpfjLG','ceshis',NULL,'13613088340',NULL,0,0,'APP','2018-09-12 13:50:57','2019-08-30 15:50:15'),(15,'w2121','$2a$10$NaUk9I7XfcE5x2hkJHhJF.YxWi/pUM51KTYiP1Y74dh5v70SczcLC','122',NULL,'15854556993',NULL,0,1,'APP','2018-09-13 09:35:15','2019-08-29 21:36:06'),(16,'admin1023','$2a$10$DMoP36vp/v8KUC.8swgVKOy0O0AQ1VuEkuHqUn8z1twfZPzmjGL6S','ewdc3e',NULL,'13148706499',NULL,1,1,'BACKEND','2019-07-26 16:07:56','2019-08-31 12:23:17'),(17,'jack','$2a$10$EcvW.D0TnRSzeLvG2OoSyubsv7eixASMGPEI0AauwjJd4jx6y6A1m','jack',NULL,'13612399765','909376644@qq.com',0,1,'BACKEND','2019-08-31 14:32:52','2019-08-31 23:39:14'),(18,'linlev','$2a$10$hTVB0VPn3Cdo.YNvsaFpauTcYJU1vqHzM0nwAJ5Iz10mDayd1Hq/W','linlev',NULL,'13613988765','9087823@qq.com',0,0,'BACKEND','2019-08-31 14:47:02','2019-08-31 23:45:21');

/*Table structure for table `u_apply` */

DROP TABLE IF EXISTS `u_apply`;

CREATE TABLE `u_apply` (
  `apply_no` varchar(100) NOT NULL COMMENT '应用编号',
  `apply_name` varchar(50) NOT NULL COMMENT '应用名称',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `logo` varchar(255) NOT NULL COMMENT 'logo图片',
  `label` varchar(50) NOT NULL COMMENT '标签',
  `type` varchar(255) NOT NULL COMMENT '分类',
  `url` varchar(100) NOT NULL COMMENT '链接',
  `customer` varchar(20) DEFAULT NULL COMMENT '客户',
  `traffic_settle` enum('UV','CPA','CPS') DEFAULT 'UV' COMMENT '流量结算方式',
  `price` decimal(3,2) DEFAULT '0.00' COMMENT '单价',
  `state` enum('VERIFY','ONLINE','OFFLINE') DEFAULT 'VERIFY' COMMENT '状态',
  `vip` tinyint(1) DEFAULT '0' COMMENT '是否VIP通道',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`apply_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_apply` */

/*Table structure for table `u_channel` */

DROP TABLE IF EXISTS `u_channel`;

CREATE TABLE `u_channel` (
  `channel_no` varchar(100) NOT NULL COMMENT '渠道编号',
  `channel_name` varchar(20) NOT NULL COMMENT '渠道名称',
  `state` enum('VERIFY','ONLINE','OFFLINE') DEFAULT 'VERIFY' COMMENT '状态',
  `type` enum('CASH','MARKET') DEFAULT 'CASH' COMMENT '渠道流控',
  `traffic_settle` enum('UV','CPS','CPA') DEFAULT 'UV' COMMENT '成本结算方式',
  `price` decimal(3,2) DEFAULT '0.00' COMMENT '单价',
  `cost_uv` decimal(3,2) DEFAULT '0.00' COMMENT 'uv 成本',
  `cost_num` int(50) DEFAULT '0' COMMENT '来源uv',
  `and_register` int(50) DEFAULT '0' COMMENT '安卓设备注册数',
  `ios_register` int(20) DEFAULT '0' COMMENT '苹果设备注册数',
  `active_num` int(50) DEFAULT '0' COMMENT '激活数',
  `and_active` int(50) DEFAULT '0' COMMENT '安卓激活数',
  `ios_active` int(50) DEFAULT '0' COMMENT 'ios激活数',
  `settlement_num` int(50) DEFAULT '0' COMMENT '结算数量',
  `produce_uv` int(50) DEFAULT '0' COMMENT '产生uv',
  `new_uv` int(50) DEFAULT '0' COMMENT '新客uv',
  `pv` int(50) DEFAULT '0' COMMENT 'pv',
  `new_pv` int(50) DEFAULT '0' COMMENT '新客pv',
  `rate` decimal(3,2) DEFAULT '1.00' COMMENT '智能比例',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`channel_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_channel` */

/*Table structure for table `u_channel_user` */

DROP TABLE IF EXISTS `u_channel_user`;

CREATE TABLE `u_channel_user` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(100) NOT NULL COMMENT '渠道编号',
  `username` varchar(50) NOT NULL COMMENT '登录账号',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `public_key` varchar(255) DEFAULT NULL COMMENT '公钥',
  `private_key` varchar(1000) DEFAULT NULL COMMENT '私钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_channel_user` */

insert  into `u_channel_user`(`id`,`channel_no`,`username`,`password`,`public_key`,`private_key`,`create_time`,`update_time`) values (1,'CN123456789','李四','123456','MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCF3pR0w3SXA3V6TPZAQQLLsTQZ4qDdhvtJoehJETjmf5ivBDYEfyClcc9xoVH1pGlhvHNuOB1Umu3K8BE2ILqItfYO0+zWcFKlAauboFK1INFq1ujWEj9iBWvxNM69YyXam2XJVDouV0xFRbvn7wuPGpoBaQAEK4a52AlA2tMTkwIDAQAB','MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIXelHTDdJcDdXpM9kBBAsuxNBnioN2G+0mh6EkROOZ/mK8ENgR/IKVxz3GhUfWkaWG8c244HVSa7crwETYguoi19g7T7NZwUqUBq5ugUrUg0WrW6NYSP2IFa/E0zr1jJdqbZclUOi5XTEVFu+fvC48amgFpAAQrhrnYCUDa0xOTAgMBAAECgYAjBoB7KGSJ2mrTS+2mFBxiJQQHvcbvDxESS91VuL7VeA7/w69Kb+SfxO5MwbOK/LBh1Qeyj9YEu+B5l2Q5cEi9deoFF3tiIa+Jvi68sqW9Kwhda8dCN+VStoBSskHeC5lLrGvIk8uaja04hTyl+w5rqX3hRClpfr4y9DmpgqCJYQJBAPFW7FlyH+n+MCKNEi7IsZf8+4LV4arAKCVuDtPpMLZT6lci+Tcpz2rKcWN9mw8urD/3gnoQ5vz1UhkhZlBFUWMCQQCOAGMG7l1J6gvFPSjXqrZw2EAHZlZXSkazxPRM6YvRLw0ZpQzxioVGLa5IrHam+X7mnX98MGB/asG4iVKWOmQRAkB6GMPSLM7AjvzDPiOoz5gpkK+d7Z8Rx82Hwjp2M0+VrrasBuGWauyo1mqvLui3saEra4jzQIHcEDDMzfz5N1iJAkAZAZ5uuQk6m/moqUWI/SoVoy9bXj00MANVYwi2hqY1lziSXItz+bXQjvjTGXPnoaYkTHYH06kapx0oNx6REAVRAkEAqiLpTInqIHoDBNEz1CKGuBEA38PW63/R5pK8JziyIgLFKWugZricazeAHDE+zLgZEVGTQQyNLvNZ7hxe4C+5Hg==','2019-08-07 19:13:03','2019-08-07 19:13:05');

/*Table structure for table `u_member` */

DROP TABLE IF EXISTS `u_member`;

CREATE TABLE `u_member` (
  `member_no` varchar(100) NOT NULL COMMENT '用户编号',
  `nick` varchar(50) NOT NULL COMMENT '用户昵称',
  `mobile` varchar(11) NOT NULL COMMENT '用户手机号',
  `source_channel` varchar(50) DEFAULT NULL COMMENT '来源渠道',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `register_ip` varchar(20) DEFAULT NULL COMMENT '注册IP',
  `active_time` datetime DEFAULT NULL COMMENT '活动时间',
  `active_ip` varchar(20) DEFAULT NULL COMMENT '活动IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_member` */

/* Function  structure for function  `currval` */

/*!50003 DROP FUNCTION IF EXISTS `currval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `currval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin        
    declare value integer;         
    set value = 0;         
    select current_val into value from order_seq where seq_name = v_seq_name;   
   return value;   
end */$$
DELIMITER ;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `nextval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin  
		declare max integer;
		select max_val into max from order_seq where seq_name = v_seq_name; 
		
    if currval(v_seq_name)<max then
    		update order_seq set current_val = current_val + increment_val  where seq_name = v_seq_name; 
    else 
    		update order_seq set current_val = 1000  where seq_name = v_seq_name;
  	end if; 
  	
    return currval(v_seq_name);  
end */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
