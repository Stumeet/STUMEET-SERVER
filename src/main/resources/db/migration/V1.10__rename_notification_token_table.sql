ALTER TABLE notification_token RENAME TO device;

ALTER TABLE device
    CHANGE token notification_token VARCHAR(255) NOT NULL COMMENT '알림 토큰';

ALTER TABLE device
    DROP FOREIGN KEY `fk_registration_token_by_member_id`;

ALTER TABLE device
    ADD CONSTRAINT `fk_device_by_member_id`
        FOREIGN KEY (`member_id`)
            REFERENCES `member` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE;