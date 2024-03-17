CREATE TABLE IF NOT EXISTS `study_member`
(
    `id`            BIGINT     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '스터디 멤버 ID',
    `member_id`     BIGINT     NOT NULL COMMENT '멤버 ID',
    `study_id`      BIGINT     NOT NULL COMMENT '스터디 ID',
    `is_admin`      TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '스터디 관리자 여부',
    `is_sent_grape` TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '포도알 전송 여부',
    `created_at`    DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`    DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT `fk_member_id` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_study_id` FOREIGN KEY (`study_id`)
        REFERENCES `study` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;