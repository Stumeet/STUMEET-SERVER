CREATE TABLE IF NOT EXISTS `activity`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '스터디 활동 ID',
    `study_id`   BIGINT       NOT NULL COMMENT '스터디 ID',
    `author_id`  BIGINT       NOT NULL COMMENT '활동 생성자 ID',
    `category`   VARCHAR(50)  NOT NULL DEFAULT FALSE COMMENT '활동 카테고리',
    `title`      VARCHAR(100) NOT NULL COMMENT '활동 제목',
    `content`    VARCHAR(500) NOT NULL COMMENT '활동 내용',
    `is_notice`  TINYINT(1)   NOT NULL DEFAULT FALSE COMMENT '공지 여부',

    `start_date` DATETIME     NOT NULL COMMENT '활동 시작일',
    `end_date`   DATETIME     NOT NULL COMMENT '활동 종료일',
    `location`   VARCHAR(255) NOT NULL COMMENT '활동 장소',

    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT `fk_activity_by_study_id` FOREIGN KEY (`study_id`)
        REFERENCES `study` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_activity_by_member_id` FOREIGN KEY (`author_id`)
        REFERENCES `member` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `activity_image`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '활동 이미지 ID',
    `activity_id` BIGINT       NOT NULL COMMENT '활동 ID',
    `image`       VARCHAR(500) NOT NULL COMMENT '첨부 이미지',

    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT `fk_activity_image_by_activity_id` FOREIGN KEY (`activity_id`)
        REFERENCES `activity` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `activity_participant`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '활동 참여자 ID',
    `activity_id` BIGINT      NOT NULL COMMENT '활동 ID',
    `member_id`   BIGINT      NOT NULL COMMENT '스터디 ID',
    `status`      VARCHAR(50) NOT NULL DEFAULT FALSE COMMENT '활동 참여자 상태',

    `created_at`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT `fk_activity_participant_by_activity_id` FOREIGN KEY (`activity_id`)
        REFERENCES `activity` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_activity_participant_by_member_id` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

