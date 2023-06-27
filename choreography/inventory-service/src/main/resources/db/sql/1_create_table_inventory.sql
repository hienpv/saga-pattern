create TABLE IF NOT EXISTS `inventory`
(
    `uuid`         VARCHAR(36)  NOT NULL,
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    `product_id`       VARCHAR(150) NULL,
    `quantity`    INT(5) NULL,
    PRIMARY KEY (`uuid`)
);
