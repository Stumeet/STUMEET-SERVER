CREATE TABLE IF NOT EXISTS `profession`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '분야 ID',
    `name`       VARCHAR(255) NOT NULL COMMENT '이름',
    `parent_id`  BIGINT       NULL COMMENT '대분류 분야',
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT fk_parent FOREIGN KEY (parent_id)
        REFERENCES profession (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `member`
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '멤버 아이디',
    `profession_id`  BIGINT       NULL COMMENT '분야 ID',
    `name`           VARCHAR(255) NULL COMMENT '멤버 이름',
    `image`          VARCHAR(500) NULL COMMENT '멤버 이미지 URL',
    `sugar_contents` DOUBLE       NOT NULL DEFAULT 0 COMMENT '포도알 당도',
    `region`         VARCHAR(50)  NULL COMMENT '지역',
    `auth_type`      VARCHAR(50)  NOT NULL COMMENT '인증 방법(OAuth, 자체 로그인 등)',
    `role`           VARCHAR(20)  NOT NULL COMMENT '권한(FIRST_LOGIN, MEMBER)',
    `created_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    `is_deleted`     tinyint(1)   NOT NULL COMMENT '삭제 여부',
    `deleted_at`     DATETIME     NULL COMMENT '삭제된 시간',

    CONSTRAINT fk_member_profession FOREIGN KEY (profession_id)
        REFERENCES profession (id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `oauth_login`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'OAuth 로그인 정보 ID',
    `member_id`     BIGINT      NOT NULL COMMENT '멤버 ID',
    `provider_name` VARCHAR(50) NOT NULL COMMENT '제공자 이름(kakao, apple)',
    `provider_id`   VARCHAR(50) NOT NULL COMMENT 'OAuth 사용자 고유 아이디',
    `created_at`    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    `updated_at`    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',

    CONSTRAINT fk_oauth_login_member FOREIGN KEY (member_id)
        REFERENCES member (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

