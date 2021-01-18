/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.40-log : Database - oauth-center
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth-center` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `oauth-center`;

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(48) NOT NULL COMMENT '应用标识',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源限定串(逗号分割)',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `client_secret_str` varchar(256) DEFAULT NULL COMMENT '应用密钥(明文)',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) DEFAULT '{}' COMMENT '{}',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动授权 是-true',
  `status` int(1) DEFAULT NULL,
  `if_limit` int(11) NOT NULL DEFAULT '0',
  `limit_count` int(11) NOT NULL DEFAULT '10000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`id`,`client_id`,`resource_ids`,`client_secret`,`client_secret_str`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`,`status`,`if_limit`,`limit_count`) values (1,'app',NULL,'$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO',NULL,'app','password,refresh_token',NULL,NULL,180000,NULL,'{}','true',0,0,10000),(2,'mobile','mobile,test','$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm',NULL,'all','password,refresh_token',NULL,NULL,180000,NULL,'{}','true',1,0,10000),(4,'webApp',NULL,'$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72',NULL,'app','authorization_code,password,refresh_token,client_credentials',NULL,NULL,180000,NULL,'{}','true',1,0,10000),(5,'beck','','$2a$10$56LGyH.2wOFNNp3ScUkspOMdyRnenYhnWEnfI0itIFfsXsd5ZhKh.',NULL,'all','authorization_code,password,refresh_token,client_credentials','http://www.baidu.com','',180000,NULL,'{}','true',1,0,10000),(6,'owen',NULL,'$2a$10$a1ZEXiZQr604LN.wVxet.etPm6RvDs.HIaXP48J2HKRaEnZORTVwe',NULL,'app','authorization_code,password,refresh_token,client_credentials','http://127.0.0.1:9997/dashboard/login',NULL,180000,NULL,'{}','true',1,0,10000),(8,'testOne','','$2a$10$nI9kx19HHJTkJq0kMRPZ6uu/4uW7J9kPIpZ8YjFmbUlvwJmorc5Qa',NULL,'all','authorization_code,password,refresh_token,client_credentials','http://bai.com','',18000,18000,'{}','true',0,0,10000),(9,'gwapi','','$2a$10$l7plpxQk42cuKbB8tbNe8eAA6v2xA6xkPXsjGEezago239102LRL2',NULL,'all','authorization_code,password,refresh_token,client_credentials','https://www.baidu.co','',18000,18000,'{}','true',1,0,10000),(10,'testtwo','','$2a$10$49ESIYmzu1n.cGzwMLRgleQMk0.kBTMOYnW4WUBDKwu9V23qOBovG',NULL,'all','authorization_code,password,refresh_token,client_credentials','locahost:9090/test','',18000,18000,'{}','true',1,0,10000),(11,'uc-app','','$2a$10$8UxEUaT2D2vSTJvTA/7YbODgCbK44bozsNA1kvMFSz8R153Xat7UO',NULL,'all','authorization_code,password,refresh_token,client_credentials','www.baidu.com','',18000,18000,'{}','true',1,0,10000),(12,'testtwo4','','$2a$10$ZHvart9oufCEiuzACIL4ke4AvD0SVZr6JD2yjZGbfVvrRKygL9n3a',NULL,'all','authorization_code,password,refresh_token,client_credentials','locahost:9090/test','',18000,18000,'{}','true',1,0,10000),(13,'jeecp',NULL,'$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72',NULL,'app','authorization_code,password,refresh_token,client_credentials','http://127.0.0.1:9997/dashboard/login',NULL,180000,NULL,'{}','true',1,0,10000);

/*Table structure for table `sys_client_service` */

DROP TABLE IF EXISTS `sys_client_service`;

CREATE TABLE `sys_client_service` (
  `clientId` int(11) NOT NULL COMMENT '应用标识',
  `serviceId` int(11) NOT NULL COMMENT '服务权限标识',
  PRIMARY KEY (`clientId`,`serviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_client_service` */

insert  into `sys_client_service`(`clientId`,`serviceId`) values (4,74),(4,75),(4,76),(4,77),(4,78),(4,79),(4,80),(4,81),(4,82),(4,83),(4,84),(4,85),(4,86),(4,87),(4,88),(4,89),(4,90),(4,91);

/*Table structure for table `sys_gateway_routes` */

DROP TABLE IF EXISTS `sys_gateway_routes`;

CREATE TABLE `sys_gateway_routes` (
  `id` char(32) NOT NULL COMMENT 'id',
  `uri` varchar(100) NOT NULL COMMENT 'uri路径',
  `predicates` varchar(1000) DEFAULT NULL COMMENT '判定器',
  `filters` varchar(1000) DEFAULT NULL COMMENT '过滤器',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `delFlag` int(11) DEFAULT '0' COMMENT '删除标志 0 不删除 1 删除',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务网关路由表';

/*Data for the table `sys_gateway_routes` */

/*Table structure for table `sys_service` */

DROP TABLE IF EXISTS `sys_service`;

CREATE TABLE `sys_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `css` varchar(32) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `isMenu` int(11) DEFAULT NULL COMMENT '是否服务 1 是 2 不是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_service` */

insert  into `sys_service`(`id`,`parentId`,`name`,`url`,`path`,`css`,`sort`,`createTime`,`updateTime`,`isMenu`) values (74,-1,'认证中心',NULL,'/api-auth',NULL,1,'2019-04-09 12:37:57','2019-04-09 12:37:57',1),(75,74,'应用管理',NULL,'/api-user/client**/**',NULL,1,'2019-04-09 12:38:54','2019-04-09 12:38:54',1),(76,74,'认证管理',NULL,'/api-auth/oauth**/**',NULL,2,'2019-04-09 12:39:21','2019-04-09 12:39:21',1),(77,74,'redis监控',NULL,'/api-auth/redis**/**',NULL,3,'2019-04-09 12:40:10','2019-04-09 12:40:10',1),(78,74,'服务管理',NULL,'/api-auth/services**/**',NULL,4,'2019-04-09 12:40:41','2019-04-09 12:40:41',1),(79,-1,'用户中心',NULL,'/api-user',NULL,2,'2019-04-09 12:41:43','2019-04-09 12:41:43',1),(80,79,'用户管理',NULL,'/api-user/users**/**',NULL,1,'2019-04-09 12:42:21','2019-04-09 12:42:21',1),(81,79,'角色管理',NULL,'/api-user/roles**/**',NULL,2,'2019-04-09 12:42:57','2019-04-09 12:42:57',1),(82,79,'菜单管理',NULL,'/api-user/menus**/**',NULL,3,'2019-04-09 12:43:25','2019-04-09 12:43:25',1),(83,79,'权限管理',NULL,'/api-user/permissions**/**',NULL,4,'2019-04-09 12:43:57','2019-04-09 12:43:57',1),(84,-1,'文件中心',NULL,'/api-file',NULL,3,'2019-04-09 12:44:22','2019-04-09 12:44:22',1),(85,84,'文件管理',NULL,'/api-file/files**/**',NULL,1,'2019-04-09 12:45:17','2019-04-09 12:45:17',1),(86,-1,'短信中心',NULL,'/api/sms',NULL,4,'2019-04-09 12:45:40','2019-04-09 12:45:40',1),(87,86,'短信管理',NULL,'/api/sms/sms**/**',NULL,1,'2019-04-09 12:46:28','2019-04-09 12:46:28',1),(88,-1,'日志中心',NULL,'/api-log',NULL,5,'2019-04-09 12:47:11','2019-04-09 12:47:11',1),(89,88,'日志管理',NULL,'/api-log/sysLog**/**',NULL,1,'2019-04-09 12:47:31','2019-04-09 12:47:31',1),(90,-1,'注册中心',NULL,'/api-eureka',NULL,6,'2019-04-09 21:25:10','2019-04-09 21:25:10',1),(91,90,'服务治理','/api-eureka/eureka**/**','/api-eureka/eureka**/**',NULL,1,'2019-04-09 21:25:53','2019-04-09 21:29:53',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
