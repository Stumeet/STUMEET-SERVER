CREATE TABLE IF NOT EXISTS `topic_subscription`
(
    `id`         BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '토픽 구독 ID',
    `topic_id`   BIGINT   NOT NULL COMMENT '토픽 ID',
    `member_id`  BIGINT   NOT NULL COMMENT '멤버 ID',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT `fk_topic_subscription_by_member_id` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;