create TABLE IF NOT EXISTS `user_transaction`
(
    `uuid`         VARCHAR(36)  NOT NULL,
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    `order_id`       VARCHAR(150) NULL,
    `user_id`       VARCHAR(150) NULL,
    `amount`    BIGINT(20) NULL,
    PRIMARY KEY (`uuid`)
);