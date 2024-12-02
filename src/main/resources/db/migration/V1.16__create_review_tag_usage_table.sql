CREATE TABLE IF NOT EXISTS `review_tag_usage`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰 태그 사용 ID',
    `review_id`  BIGINT      NOT NULL COMMENT '리뷰 ID',
    `review_tag` VARCHAR(50) NOT NULL COMMENT '리뷰 태그',
    CONSTRAINT `fk_review_tag_usage_review_id` FOREIGN KEY (`review_id`)
        REFERENCES `review` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;