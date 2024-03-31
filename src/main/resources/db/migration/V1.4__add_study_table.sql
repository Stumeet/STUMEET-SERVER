CREATE TABLE `study_field`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '스터디 분야 ID',
    `name` VARCHAR(50) NOT NULL COMMENT '분야명'
) ENGINE = InnoDB
      DEFAULT CHARSET = utf8mb4
      COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `study_domain`
(
    `id`             BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '스터디 도메인 ID',
    `study_field_id` BIGINT NULL COMMENT '스터디 분야 ID',

    CONSTRAINT fk_study_domain_with_field FOREIGN KEY (study_field_id)
        REFERENCES study_field (id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB
      DEFAULT CHARSET = utf8mb4
      COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `study`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '스터디 ID',
    `study_domain_id`    BIGINT       NOT NULL COMMENT '스터디 도메인 ID',
    `name`               VARCHAR(255) NOT NULL COMMENT '스터디명',
    `region`             VARCHAR(50)  NOT NULL COMMENT '활동 지역',
    `intro`              VARCHAR(200) NOT NULL COMMENT '소개',
    `rule`               VARCHAR(200) NULL COMMENT '규칙',
    `image`              VARCHAR(500) NOT NULL COMMENT '스터디 이미지의 URL',
    `headcount`          TINYINT(100) NOT NULL DEFAULT 1 COMMENT '인원 수',
    `start_date`         DATETIME     NOT NULL COMMENT '시작일',
    `end_date`           DATETIME     NOT NULL COMMENT '종료일',
    `meeting_time`       TIME         NOT NULL COMMENT '정기모임 시간',
    `meeting_repetition` VARCHAR(100) NOT NULL COMMENT '정기모임 반복',
    `created_at`         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    `is_finished`        TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '스터디 완료 여부',
    `is_deleted`         TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '스터디 삭제 여부',
    `deleted_at`         DATETIME NULL COMMENT '스터디 삭제 일자',

    CONSTRAINT fk_study_domain_with_study FOREIGN KEY (study_domain_id)
        REFERENCES study_domain (id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
      DEFAULT CHARSET = utf8mb4
      COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `study_tag`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '스터디 태그 ID',
    `study_domain_id` BIGINT      NOT NULL COMMENT '스터디 관련 ID',
    `name`            VARCHAR(20) NOT NULL COMMENT '태그명',

    CONSTRAINT fk_study_domain_with_tag FOREIGN KEY (study_domain_id)
        REFERENCES study_domain (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
      DEFAULT CHARSET = utf8mb4
      COLLATE = utf8mb4_unicode_ci;