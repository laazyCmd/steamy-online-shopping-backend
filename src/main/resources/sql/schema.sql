CREATE TABLE IF NOT EXISTS `accounts` (
	`id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`bio` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_general_ci',
	`is_seller` TINYINT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `name` (`name`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `countries` (
	`id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`shipping_fee` DOUBLE UNSIGNED NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `name` (`name`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `products` (
	`id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_general_ci',
	`description` VARCHAR(2000) NOT NULL COLLATE 'utf8mb3_general_ci',
	`image` LONGBLOB DEFAULT NULL,
	`rating` DOUBLE UNSIGNED NOT NULL,
	`price` DOUBLE UNSIGNED NOT NULL,
	`sales` INT UNSIGNED NOT NULL DEFAULT '0',
	`stocks` SMALLINT(5) UNSIGNED NOT NULL,
	`category` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`brand` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`date_created` DATE NOT NULL DEFAULT curdate(),
	`seller` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`country_origin` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`shipping_origin` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`shipping_fee` DOUBLE UNSIGNED NOT NULL,
	`is_popular` TINYINT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `fk_productshipping` (`shipping_origin`) USING BTREE,
	INDEX `fk_productseller` (`seller`) USING BTREE,
	CONSTRAINT `fk_productseller` FOREIGN KEY (`seller`) REFERENCES `steamy_backend`.`accounts` (`name`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `fk_productshipping` FOREIGN KEY (`shipping_origin`) REFERENCES `steamy_backend`.`countries` (`name`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;