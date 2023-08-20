<?php

declare(strict_types=1);

namespace Migrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230820123030 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        $this->addSql("
            CREATE TABLE `employees` (
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
            AUTO_INCREMENT=1"
        );
    }

    public function down(Schema $schema): void
    {
        $this->addSql('DROP TABLE employees');
    }
}
