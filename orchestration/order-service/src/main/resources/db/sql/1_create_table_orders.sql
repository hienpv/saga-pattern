create TABLE IF NOT EXISTS `orders`
(
    `uuid`         VARCHAR(36)  NOT NULL,
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    `user_id`       VARCHAR(50) NOT NULL,
    `product_id`       VARCHAR(150) NULL,
    `price`    BIGINT(20) NULL,
    `quantity`     BIGINT(20) NULL,
    `order_status`     VARCHAR(100) NULL,
    `payment_status`     VARCHAR(100) NULL,
    `inventory_status`     VARCHAR(100) NULL,
    PRIMARY KEY (`uuid`)
);