-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.11-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 hohodb.config 结构
CREATE TABLE IF NOT EXISTS `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_key` varchar(64) NOT NULL COMMENT 'key',
  `item_value` text NOT NULL COMMENT 'value',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `environment` varchar(16) DEFAULT NULL COMMENT '环境',
  `status` varchar(16) DEFAULT NULL COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '配置描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(16) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(16) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='配置表';

-- 正在导出表  hohodb.config 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`id`, `item_key`, `item_value`, `type`, `environment`, `status`, `description`, `create_time`, `creator`, `update_time`, `updater`) VALUES
	(1, 'gw.addr', '127.0.0.1', 'PRIVATE', 'STABLE', 'OFF', '网关地址', NULL, NULL, NULL, NULL),
	(3, 'server.port', '1002', 'PUBLIC', 'DEV', 'ON', 'port', NULL, NULL, NULL, NULL),
	(5, 'mybtis.location', 'classpath*:/mybatis.xml', 'PUBLIC', 'DEV', 'ON', 'mybatis配置', NULL, NULL, NULL, NULL),
	(6, 'hoho.domain', 'hoho.today', 'PRIVATE', 'DEV', 'ON', 'hoho域名', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;


-- 导出  表 hohodb.project 结构
CREATE TABLE IF NOT EXISTS `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '项目名称',
  `description` varchar(256) DEFAULT NULL COMMENT '项目描述',
  `status` varchar(16) DEFAULT NULL COMMENT '项目状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='项目表';

-- 正在导出表  hohodb.project 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`id`, `name_en`, `name`, `description`, `status`) VALUES
	(1, 'gw1', '网关项目', 'gw', 'ON'),
	(2, 'redis', 'redis', 'redis缓存', 'ON'),
	(3, 'mongodb', 'mongodb', 'mongodb', 'OFF');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


-- 导出  表 hohodb.system 结构
CREATE TABLE IF NOT EXISTS `system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(32) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL COMMENT '系统名称',
  `description` varchar(256) DEFAULT NULL COMMENT '系统描述',
  `project_id` bigint(20) DEFAULT NULL COMMENT '环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统表';

-- 正在导出表  hohodb.system 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
INSERT INTO `system` (`id`, `name_en`, `name`, `description`, `project_id`) VALUES
	(1, 'gw', '网关服务', 'gw+1', 2),
	(3, 'mongoDB', 'NoSQL数据库系统', 'NoSQL', 3);
/*!40000 ALTER TABLE `system` ENABLE KEYS */;


-- 导出  表 hohodb.system_config 结构
CREATE TABLE IF NOT EXISTS `system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `system_id` bigint(20) NOT NULL COMMENT '系统id',
  `config_id` bigint(20) NOT NULL COMMENT '配置id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='系统和配置关联表';

-- 正在导出表  hohodb.system_config 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
INSERT INTO `system_config` (`id`, `system_id`, `config_id`) VALUES
	(3, 1, 3),
	(4, 1, 1),
	(5, 3, 3),
	(7, 1, 5);
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
