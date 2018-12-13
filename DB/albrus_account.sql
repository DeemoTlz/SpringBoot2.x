/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17 : Database - albrus_account
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`albrus_account` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `albrus_account`;

/*Table structure for table `albrus_consume_cost` */

DROP TABLE IF EXISTS `albrus_consume_cost`;

CREATE TABLE `albrus_consume_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consume_type` int(11) NOT NULL COMMENT '消费内容',
  `consume_money` float NOT NULL COMMENT '消费金额',
  `consume_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消费时间',
  `consume_user` int(11) NOT NULL COMMENT '消费人',
  `deleted` int(2) DEFAULT '0',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `albrus_consume_cost` */

/*Table structure for table `albrus_consume_type` */

DROP TABLE IF EXISTS `albrus_consume_type`;

CREATE TABLE `albrus_consume_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '消费种类',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `level` int(1) NOT NULL DEFAULT '0' COMMENT '级别',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `albrus_consume_type` */

/*Table structure for table `albrus_i18n` */

DROP TABLE IF EXISTS `albrus_i18n`;

CREATE TABLE `albrus_i18n` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '国际化信息 id',
  `key` varchar(150) NOT NULL DEFAULT '' COMMENT '国际化字符串key',
  `val` text NOT NULL,
  `locale` varchar(10) NOT NULL DEFAULT 'zh_CN' COMMENT '格式：语言类型_国家或地区 如zh_CN,en_US',
  `i18n_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '国际化类型 0 UI展现 1 异常信息 2 日志、交互信息',
  `bizmodule` varchar(255) DEFAULT '' COMMENT '业务模块',
  `bizname` varchar(255) DEFAULT '' COMMENT '业务名称',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sys_i18n` (`key`,`locale`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `albrus_i18n` */

/*Table structure for table `albrus_logs` */

DROP TABLE IF EXISTS `albrus_logs`;

CREATE TABLE `albrus_logs` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT '' COMMENT '用户名称',
  `logtype` int(1) DEFAULT '0' COMMENT '日志类型 0 操作日志 1 系统日志',
  `level` int(1) DEFAULT NULL COMMENT '日志级别',
  `bizmodule` varchar(255) DEFAULT '' COMMENT '业务模块',
  `bizname` varchar(255) DEFAULT '' COMMENT '业务名称',
  `classname` varchar(255) DEFAULT '' COMMENT '类名',
  `method` varchar(255) DEFAULT '' COMMENT '方法名',
  `req_uri` varchar(255) DEFAULT '' COMMENT '请求URI',
  `parameters` text COMMENT '请求参数',
  `client_ip` varchar(255) DEFAULT '' COMMENT '客户端IP',
  `status` varchar(255) DEFAULT '' COMMENT '状态',
  `duration` int(11) DEFAULT '0' COMMENT '执行时间(毫秒)',
  `content` text COMMENT '日志内容',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `albrus_logs` */

/*Table structure for table `albrus_password_policy` */

DROP TABLE IF EXISTS `albrus_password_policy`;

CREATE TABLE `albrus_password_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '',
  `desc` varchar(256) NOT NULL DEFAULT '',
  `type` int(11) unsigned NOT NULL DEFAULT '1',
  `time_day` int(11) unsigned DEFAULT '0',
  `enable_min_length` int(11) unsigned NOT NULL DEFAULT '0',
  `min_length` int(11) unsigned DEFAULT '8',
  `enable_uppercase` int(11) unsigned NOT NULL DEFAULT '0',
  `uppercase_count` int(11) unsigned DEFAULT '1',
  `enable_lowercase` int(11) unsigned NOT NULL DEFAULT '0',
  `lowercase_count` int(11) unsigned DEFAULT '1',
  `enable_digit` int(11) unsigned NOT NULL DEFAULT '0',
  `digit_count` int(11) unsigned DEFAULT '1',
  `enable_spec_char` int(11) unsigned NOT NULL DEFAULT '0',
  `spec_char_count` int(11) unsigned DEFAULT '1',
  `predefined` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `albrus_password_policy` */

/*Table structure for table `albrus_resource` */

DROP TABLE IF EXISTS `albrus_resource`;

CREATE TABLE `albrus_resource` (
  `id` int(11) NOT NULL COMMENT '编号',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `url` varchar(2000) DEFAULT NULL COMMENT '链接',
  `system_id` varchar(20) DEFAULT NULL COMMENT '目标系统',
  `level` int(11) DEFAULT NULL COMMENT '标记菜单级数',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ico` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `albrus_resource` */

/*Table structure for table `albrus_role` */

DROP TABLE IF EXISTS `albrus_role`;

CREATE TABLE `albrus_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '描述',
  `predefined` int(11) NOT NULL DEFAULT '0' COMMENT '是否系统预定义',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `albrus_role` */

/*Table structure for table `albrus_role_resource` */

DROP TABLE IF EXISTS `albrus_role_resource`;

CREATE TABLE `albrus_role_resource` (
  `role_id` int(11) NOT NULL COMMENT '角色id，主键',
  `resource_id` int(11) NOT NULL COMMENT '功能id，主键',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态，0为读权限，1为写权限',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色与功能关联表';

/*Data for the table `albrus_role_resource` */

/*Table structure for table `albrus_user` */

DROP TABLE IF EXISTS `albrus_user`;

CREATE TABLE `albrus_user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(128) DEFAULT '',
  `begin_time` datetime NOT NULL DEFAULT '2008-01-01 00:00:00',
  `end_time` datetime NOT NULL DEFAULT '2037-01-01 00:00:00',
  `update_pwd_time` datetime DEFAULT NULL,
  `predefined` int(2) DEFAULT '0',
  `pwd_policy` int(11) DEFAULT NULL COMMENT '密码安全策略',
  `descript` varchar(255) DEFAULT '',
  `deleted` int(2) DEFAULT '0',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `albrus_user` */

insert  into `albrus_user`(`id`,`username`,`password`,`begin_time`,`end_time`,`update_pwd_time`,`predefined`,`pwd_policy`,`descript`,`deleted`,`generate_by`,`generate_time`,`update_by`,`update_time`) values (1,'123','123','2008-01-01 00:00:00','2037-01-01 00:00:00',NULL,0,NULL,'123',0,NULL,NULL,NULL,NULL);

/*Table structure for table `albrus_user_role` */

DROP TABLE IF EXISTS `albrus_user_role`;

CREATE TABLE `albrus_user_role` (
  `admin_id` int(11) NOT NULL COMMENT '用户id，主键',
  `role_id` int(11) NOT NULL COMMENT '角色id，主键',
  `generate_by` int(11) DEFAULT NULL COMMENT '数据产生者',
  `generate_time` datetime DEFAULT NULL COMMENT '数据产生时间',
  `update_by` int(11) DEFAULT NULL COMMENT '数据更新者',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`admin_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='人员与角色关联表';

/*Data for the table `albrus_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
