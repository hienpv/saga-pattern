create TABLE IF NOT EXISTS `inventory`
(
    `uuid`         VARCHAR(36)  NOT NULL,
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    `product_id`       VARCHAR(150) NULL,
    `quantity`    INT(5) NULL,
    PRIMARY KEY (`uuid`)
);
INSERT INTO inventory (`uuid`,`created_at`,`updated_at`,`product_id`,`quantity`) VALUES ('0875d241-29f6-463e-bdce-648184dfc0ab','2023-06-27 03:20:38','2023-06-27 10:21:01','10',1000);
INSERT INTO inventory (`uuid`,`created_at`,`updated_at`,`product_id`,`quantity`) VALUES ('0875d241-29f6-463e-bdce-648184dfc0ac','2023-06-27 03:20:38','2023-06-27 03:20:38','1',10);