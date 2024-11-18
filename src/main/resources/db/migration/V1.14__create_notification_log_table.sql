CREATE TABLE IF NOT EXISTS `notification_log`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '알림 기록 ID',
    `member_id`  BIGINT       NOT NULL COMMENT '멤버 ID',
    `title`      VARCHAR(255) NOT NULL COMMENT '제목',
    `body`       TEXT COMMENT '본문',
    `img_url`    VARCHAR(2083) COMMENT '커스텀 뱃지 이미지 URL',
    `data`       TEXT COMMENT '메타 데이터',
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    CONSTRAINT `fk_notification_log_member_id` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
