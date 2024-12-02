CREATE TABLE IF NOT EXISTS report
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    category    VARCHAR(50) NOT NULL,
    reason      VARCHAR(50) NOT NULL,
    reported_id BIGINT      NOT NULL,
    reporter_id BIGINT      NOT NULL,
    content     TEXT        NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (reporter_id) REFERENCES member (id)
) ENGINE = InnoDB;