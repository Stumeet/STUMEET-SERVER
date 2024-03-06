ALTER TABLE `member`
    DROP COLUMN `sugar_contents`;

ALTER TABLE `member`
    ADD COLUMN `rank` VARCHAR(50) not null comment '등급' AFTER `image`;

ALTER TABLE `member`
    ADD COLUMN `experience` DOUBLE not null comment '경험치' AFTER `rank` ;