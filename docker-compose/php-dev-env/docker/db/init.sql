CREATE DATABASE IF NOT EXISTS docker_demo;
CREATE TABLE IF NOT EXISTS `employees` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `last_name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `email` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `phone_num` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `created_at` TIMESTAMP NULL DEFAULT NULL,
    `updated_at` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `phone_num` (`phone_num`) USING BTREE,
    UNIQUE INDEX `email` (`email`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1;