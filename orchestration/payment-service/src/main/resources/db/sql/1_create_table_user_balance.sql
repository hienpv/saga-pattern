create TABLE IF NOT EXISTS `user_balance`
(
    `uuid`         VARCHAR(36)  NOT NULL,
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    `user_id`       VARCHAR(150) NULL,
    `balance`    BIGINT(20) NULL,
    PRIMARY KEY (`uuid`)
);
INSERT INTO user_balance (`uuid`,`created_at`,`updated_at`,`user_id`,`balance`) VALUES ('0875d241-29f6-463e-bdce-648184dfc0ab','2023-06-27 03:20:38','2023-06-27 10:21:01','10',100000000000);
INSERT INTO user_balance (`uuid`,`created_at`,`updated_at`,`user_id`,`balance`) VALUES ('0875d241-29f6-463e-bdce-648184dfc0ac','2023-06-27 03:20:38','2023-06-27 03:20:38','1',10000);