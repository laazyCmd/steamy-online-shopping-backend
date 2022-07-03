CREATE TABLE IF NOT EXISTS `accounts` (
	`acc_id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
	`acc_name` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`acc_bio` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_general_ci',
	`is_seller` TINYINT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (`acc_id`) USING BTREE,
	UNIQUE INDEX `acc_name` (`acc_name`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `countries` (
	`country_id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
	`country_name` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`shipping_fee` DOUBLE UNSIGNED NOT NULL,
	PRIMARY KEY (`country_id`) USING BTREE,
	UNIQUE INDEX `country_name` (`country_name`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `products` (
	`prod_id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
	`prod_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_general_ci',
	`prod_description` VARCHAR(2000) NOT NULL COLLATE 'utf8mb3_general_ci',
	`prod_image` LONGBLOB DEFAULT NULL,
	`prod_rating` DOUBLE UNSIGNED NOT NULL,
	`prod_price` DOUBLE UNSIGNED NOT NULL,
	`prod_sales` INT UNSIGNED NOT NULL DEFAULT '0',
	`prod_stocks` SMALLINT(5) UNSIGNED NOT NULL,
	`prod_category` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`prod_brand` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`date_created` DATE NOT NULL DEFAULT curdate(),
	`seller_name` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`country_origin` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`shipping_origin` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`shipping_fee` DOUBLE UNSIGNED NOT NULL,
	`is_popular` TINYINT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (`prod_id`) USING BTREE,
	INDEX `fk_productshipping` (`shipping_origin`) USING BTREE,
	INDEX `fk_productseller` (`seller_name`) USING BTREE,
	CONSTRAINT `fk_productseller` FOREIGN KEY (`seller_name`) REFERENCES `steamy_backend`.`accounts` (`acc_name`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `fk_productshipping` FOREIGN KEY (`shipping_origin`) REFERENCES `steamy_backend`.`countries` (`country_name`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;