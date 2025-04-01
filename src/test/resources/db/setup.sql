-- [TABLE: study]
-- study 1: 유효한 스터디
INSERT INTO study (id, study_field, name, region, intro, rule, image, meeting_time, meeting_repetition, start_date, end_date)
VALUES (1, 'PROGRAMMING', 'effective java 스터디', '서울', 'java 스터디 입니다.', '- 장소: 태릉입구역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '18:00:00', 'WEEKLY;월;수;목;', '2024-04-01', '2024-05-01');

INSERT INTO study_tag (study_id, name) VALUES (1, 'springboot');
INSERT INTO study_tag (study_id, name) VALUES (1, 'java');
INSERT INTO study_tag (study_id, name) VALUES (1, '객체지향프로그래밍');

INSERT INTO topic (id, name, description, type, refer_id) VALUES (1, '1_STUDY_NOTICE_dalIAE024kldA', 'ID: 1 스터디 공지 토픽', 'STUDY_NOTICE', 1);

-- study 2: 미래 시작 스터디
INSERT INTO study (id, study_field, name, region, intro, rule, image, meeting_time, meeting_repetition, start_date, end_date)
VALUES (2, 'PROGRAMMING', 'future study', '서울', 'java 스터디 입니다.', '- 장소: 태릉입구역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '18:00:00', 'WEEKLY;월;수;목;', '2999-04-01', '2999-05-01');

INSERT INTO topic (id, name, description, type, refer_id) VALUES (2, '2_STUDY_NOTICE_dalIAE024kldA', 'ID: 2 스터디 공지 토픽', 'STUDY_NOTICE', 2);

-- study 3: 완료된 스터디
INSERT INTO study (id, study_field, name, region, intro, rule, image, meeting_time, meeting_repetition, start_date, end_date, is_finished)
VALUES (3, 'PROGRAMMING', '완료된 스터디', '서울', 'java 스터디 입니다.', '- 장소: 태릉입구역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '18:00:00', 'WEEKLY;월;수;목;', '2024-04-01', '2024-05-01', true);

INSERT INTO study_tag (study_id, name) VALUES (3, 'springboot');
INSERT INTO study_tag (study_id, name) VALUES (3, 'java');
INSERT INTO study_tag (study_id, name) VALUES (3, '객체지향프로그래밍');

INSERT INTO topic (id, name, description, type, refer_id) VALUES (3, '3_STUDY_NOTICE_dalIAE024kldA', 'ID: 3 스터디 공지 토픽', 'STUDY_NOTICE', 3);

-- study 4: 토픽 아직 없는 스터디
INSERT INTO study (id, study_field, name, region, intro, rule, image, meeting_time, meeting_repetition, start_date, end_date)
VALUES (4, 'PROGRAMMING', 'future study', '서울', 'java 스터디 입니다.', '- 장소: 태릉입구역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '18:00:00', 'WEEKLY;월;수;목;', '2999-04-01', '2999-05-01');


-- [TABLE: member, study_member]
-- member id 1: study id 1의 멤버이자 관리자
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at)
VALUES (1, 'test1', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO device (member_id, device_id, notification_token) VALUES (1, 'ALKDJLAFHLH', 'DLAJFOIAJJFLKSDLJ');

INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (1, 1, true, false);
INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (1, 2, true, false);
INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (1, 3, true, false);

INSERT INTO topic_subscription (topic_id, member_id) VALUES (1, 1);
INSERT INTO topic_subscription (topic_id, member_id) VALUES (2, 1);
INSERT INTO topic_subscription (topic_id, member_id) VALUES (3, 1);

INSERT INTO notification_log (member_id, title, body, img_url, data)
VALUES(1, '[자바를 자바]', '공지사항이 올라왔어요!', 'http://example.com/welcome.png', '{"type":"notice"}');
INSERT INTO notification_log (member_id, title, body, img_url, data)
VALUES(1, '[자바를 자바]', '공지사항이 업데이트 되었어요!', 'http://example.com/welcome.png', '{"type":"update"}');

INSERT INTO grape (member_id, study_id, compliment_type, created_at, updated_at)
VALUES (1, 1, 'PASSIONATE', NOW(), NOW());
INSERT INTO grape (member_id, study_id, compliment_type, created_at, updated_at)
VALUES (1, 2, 'DILIGENT', NOW(), NOW());
INSERT INTO grape (member_id, study_id, compliment_type, created_at, updated_at)
VALUES (1, 3, 'OUTSTANDING', NOW(), NOW());


-- member id 2: study id 1의 멤버
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at)
VALUES (2, 'test2', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO device (member_id, device_id, notification_token) VALUES (2, 'AJDLKJGALKJ', 'WEAGEEWXVVVV');

INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (2, 1, false, false);
INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (2, 3, false, false);

INSERT INTO topic_subscription (topic_id, member_id) VALUES (1, 2);

-- member id 3: study id 1의 외부자
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at)
VALUES (3, 'test3', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO device (member_id, device_id, notification_token) VALUES (3, 'GFGHGFJFDX', 'ERAHGFJJGYFY');

-- member id 4: study id 1을 참여했지만 활동은 참여하지 않은 멤버, 멤버 1이 리뷰 작성 완료, 스터디 1 이번주 포도알 전송 완료
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at)
VALUES (4, 'test4', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO device (member_id, device_id, notification_token) VALUES (4, 'ADGERGRGJSJHG', 'AHFGFJHGHKHVV');

INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (4, 1, false, true);
INSERT INTO study_member (member_id, study_id, is_admin, is_sent_grape) VALUES (4, 3, false, false);

INSERT INTO topic_subscription (topic_id, member_id) VALUES (1, 4);


-- [TABLE: activity]
-- 스터디 1 (공지) 기본 활동
INSERT INTO activity(id, study_id, author_id, category, title, content, is_notice, location, link)
VALUES (1, 1, 4, 'DEFAULT', 'title', 'content', true, null, 'https://www.naver.com');

INSERT INTO activity_image (activity_id, image) VALUES (1, 'https://example.com/images/image1.png');
INSERT INTO activity_image (activity_id, image) VALUES (1, 'https://example.com/images/image2.png');
INSERT INTO activity_image (activity_id, image) VALUES (1, 'https://example.com/images/image3.png');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (1, 1, 'NONE');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (1, 2, 'NONE');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (1, 4, 'NONE');

-- 스터디 1 기본 활동
INSERT INTO activity (id, study_id, author_id, category, title, content, is_notice, location)
VALUES (2, 1, 1, 'DEFAULT', 'title', 'content', false, null);

INSERT INTO activity_image (activity_id, image) VALUES (2, 'https://example.com/images/image1.png');
INSERT INTO activity_image (activity_id, image) VALUES (2, 'https://example.com/images/image2.png');
INSERT INTO activity_image (activity_id, image) VALUES (2, 'https://example.com/images/image3.png');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (2, 1, 'NONE');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (2, 2, 'NONE');

-- 스터디 1 모임 활동
INSERT INTO activity (id, study_id, author_id, category, title, content, is_notice, start_date, end_date, location)
VALUES (3, 1, 1, 'MEET', 'title', 'content', false, '2024-04-15T00:00:00', '2024-05-01T00:00:00', '성신여대 카페구월');

INSERT INTO activity_image (activity_id, image) VALUES (3, 'https://example.com/images/image1.png');
INSERT INTO activity_image (activity_id, image) VALUES (3, 'https://example.com/images/image2.png');
INSERT INTO activity_image (activity_id, image) VALUES (3, 'https://example.com/images/image3.png');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (3, 1, 'ATTENDANCE');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (3, 2, 'ACKNOWLEDGED_ABSENCE');

-- 스터디 1 (공지) 모임 활동
INSERT INTO activity (id, study_id, author_id, category, title, content, is_notice, start_date, end_date, location)
VALUES (4, 1, 1, 'MEET', 'title', 'content', true, '2024-04-22T00:00:00', '2024-04-23T00:00:00', '성신여대 카페구월');

INSERT INTO activity_image (activity_id, image) VALUES (4, 'https://example.com/images/image1.png');
INSERT INTO activity_image (activity_id, image) VALUES (4, 'https://example.com/images/image2.png');
INSERT INTO activity_image (activity_id, image) VALUES (4, 'https://example.com/images/image3.png');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (4, 1, 'ATTENDANCE');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (4, 2, 'ACKNOWLEDGED_ABSENCE');

-- 스터디 1 과제 활동
INSERT INTO activity (id, study_id, author_id, category, title, content, is_notice, start_date, end_date, location)
VALUES (5, 1, 1, 'ASSIGNMENT', 'title', 'content', false, '2024-04-01T00:00:00', '2024-04-08T00:00:00', null);

INSERT INTO activity_participant (activity_id, member_id, status) VALUES (5, 1, 'UNPERFORMED');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (5, 2, 'PERFORMED');

-- 스터디 1 (공지) 과제 활동
INSERT INTO activity (id, study_id, author_id, category, title, content, is_notice, start_date, end_date, location)
VALUES (6, 1, 1, 'ASSIGNMENT', 'title', 'content', true, '2024-04-08T00:00:00', '2024-04-15T00:00:00', null);

INSERT INTO activity_participant (activity_id, member_id, status) VALUES (6, 1, 'LATE_SUBMISSION');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (6, 2, 'PERFORMED');

-- 스터디 1 미참여 과제 활동
INSERT INTO activity (id, study_id, author_id, category, title, content, is_notice, start_date, end_date, location)
VALUES (7, 1, 1, 'ASSIGNMENT', 'title', 'content', false, '2024-04-08T00:00:00', '2024-04-15T00:00:00', null);

INSERT INTO activity_participant (activity_id, member_id, status) VALUES (7, 2, 'PERFORMED');

-- 스터디 2 (공지) 기본 활동
INSERT INTO activity(id, study_id, author_id, category, title, content, is_notice, location)
VALUES (8, 2, 1, 'DEFAULT', 'title', 'content', true, '성신여대 카페구월');

INSERT INTO activity_image (activity_id, image) VALUES (8, 'https://example.com/images/image1.png');
INSERT INTO activity_image (activity_id, image) VALUES (8, 'https://example.com/images/image2.png');
INSERT INTO activity_image (activity_id, image) VALUES (8, 'https://example.com/images/image3.png');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (8, 1, 'NONE');
INSERT INTO activity_participant (activity_id, member_id, status) VALUES (8, 2, 'NONE');


-- [TABLE: review]
INSERT INTO review (id, reviewer_id, reviewee_id, study_id, rate, content)
VALUES (1, 1, 4, 3, 5, '리뷰 내용입니다.');

INSERT INTO review_tag (review_id, review_tag)
VALUES (1, 'MAX_RESPONSIBILITY');

INSERT INTO review (id, reviewer_id, reviewee_id, study_id, rate, content)
VALUES (2, 2, 4, 3, 5, '리뷰 내용입니다.');

INSERT INTO review_tag (review_id, review_tag)
VALUES (2, 'MAX_RESPONSIBILITY');
INSERT INTO review_tag (review_id, review_tag)
VALUES (2, 'FAST_RESPONSE');
INSERT INTO review_tag (review_id, review_tag)
VALUES (2, 'MOOD_MAKER');