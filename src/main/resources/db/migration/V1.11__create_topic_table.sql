CREATE TABLE IF NOT EXISTS `topic`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '토픽 ID',
    `name`        VARCHAR(100) NOT NULL COMMENT '토픽 이름',
    `description` VARCHAR(255) NULL COMMENT '토픽 설명',
    `type`  VARCHAR(50)  NOT NULL COMMENT '토픽 유형',
    `refer_id`    BIGINT COMMENT '참조 id',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;