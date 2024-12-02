CREATE TABLE IF NOT EXISTS `review`
(
    `id`          BIGINT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰 ID',
    `reviewer_id` BIGINT           NOT NULL COMMENT '리뷰 작성자 ID',
    `reviewee_id` BIGINT           NOT NULL COMMENT '리뷰 대상자 ID',
    `study_id`    BIGINT           NOT NULL COMMENT '스터디 ID',
    `rate`        TINYINT UNSIGNED NOT NULL COMMENT '평점',
    `content`     VARCHAR(1000)    NULL COMMENT '리뷰 내용',
    `created_at`  DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`  DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    CONSTRAINT `fk_review_reviewer_id` FOREIGN KEY (`reviewer_id`)
        REFERENCES `member` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_review_reviewee_id` FOREIGN KEY (`reviewee_id`)
        REFERENCES `member` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_review_study_id` FOREIGN KEY (`study_id`)
        REFERENCES `study` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `review_tag_usage`
(
    `id`         BIGINT  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰 태그 사용 ID',
    `review_id`  BIGINT  NOT NULL COMMENT '리뷰 ID',
    `review_tag` VARCHAR NOT NULL COMMENT '리뷰 태그',
    CONSTRAINT `fk_review_tag_usage_review_id` FOREIGN KEY (`review_id`)
        REFERENCES `review` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;