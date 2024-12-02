SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE member;
TRUNCATE TABLE oauth_login;
TRUNCATE TABLE study_member;
TRUNCATE TABLE study_tag;
TRUNCATE TABLE study;
TRUNCATE TABLE activity;
TRUNCATE TABLE activity_participant;
TRUNCATE TABLE activity_image;
TRUNCATE TABLE device;
TRUNCATE TABLE topic_subscription;
TRUNCATE TABLE topic;
TRUNCATE TABLE review;
TRUNCATE TABLE review_tag_usage;
TRUNCATE TABLE notification_log;

SET FOREIGN_KEY_CHECKS = 1;