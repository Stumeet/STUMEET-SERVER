CREATE TABLE IF NOT EXISTS `notification_token`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '알림 토큰 ID',
    `member_id`  BIGINT       NOT NULL COMMENT '멤버 ID',
    `device_id`  VARCHAR(100) NOT NULL COMMENT '디바이스 ID',
    `token`      VARCHAR(100) COMMENT '토큰',
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT `fk_registration_token_by_member_id` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;