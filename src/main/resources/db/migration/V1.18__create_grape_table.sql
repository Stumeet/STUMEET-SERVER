CREATE TABLE IF NOT EXISTS `grape`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '포도 ID',
    `member_id`       BIGINT      NOT NULL COMMENT '멤버 ID',
    `study_id`        BIGINT      NULL COMMENT '스터디 ID',
    `compliment_type` VARCHAR(50) NOT NULL COMMENT '칭찬 타입',
    `created_at`      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    CONSTRAINT `fk_grape_linked_study_id` FOREIGN KEY (`study_id`)
        REFERENCES `study` (`id`)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;