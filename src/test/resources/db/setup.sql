-- study 1: 유효한 스터디
INSERT INTO study (id, study_field, name, region, intro, rule, image,
                   meeting_time, meeting_repetition, start_date, end_date) VALUES (1, 'PROGRAMMING', 'effective java 스터디', '서울', 'java 스터디 입니다.', '- 장소: 태릉입구역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '18:00:00', 'WEEKLY;월;수;목;', '2024-04-01', '2024-05-01');

INSERT INTO study_tag (study_id, name) VALUES (1, 'springboot');
INSERT INTO study_tag (study_id, name) VALUES (1, 'java');
INSERT INTO study_tag (study_id, name) VALUES (1, '객체지향프로그래밍');

-- study 2: 미래 시작 스터디
INSERT INTO study (id, study_field, name, region, intro, rule, image,
                   meeting_time, meeting_repetition, start_date, end_date)
VALUES (2, 'PROGRAMMING', 'future study', '서울', 'java 스터디 입니다.', '- 장소: 태릉입구역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '18:00:00', 'WEEKLY;월;수;목;', '2999-04-01', '2999-05-01');

INSERT INTO study_member(member_id, study_id, is_admin, is_sent_grape)
VALUES (1, 2, true, false);

-- member id 1: study id 1의 멤버이자 관리자
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at) VALUES (1, 'test1', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO study_member(member_id, study_id, is_admin, is_sent_grape) VALUES (1, 1, true, false);

-- member id 2: study id 1의 멤버
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at) VALUES (2, 'test2', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO study_member(member_id, study_id, is_admin, is_sent_grape) VALUES (2, 1, false, false);

-- member id 3: study id 1의 외부자
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at) VALUES (3, 'test3', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);

-- member id 4: study id 1을 참여했지만 활동은 참여하지 않은 멤버
INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at) VALUES (4, 'test4', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
                                                                                                                               '서울', 1, 'MEMBER', 'OAUTH', 'SEED', 0.0, false, null);
INSERT INTO study_member(member_id, study_id, is_admin, is_sent_grape) VALUES (4, 1, false, false);

-- 스터디 활동 (기본 활동 - 공지)
INSERT INTO activity(id, study_id, author_id, category, title, content, is_notice, start_date, end_date, location) VALUES (1, 1, 1, 'DEFAULT', 'title', 'content', true, '2024-04-01T00:00:00', '2050-05-01T00:00:00', null);
INSERT INTO activity_image(id, activity_id, image) VALUES (1, 1, 'https://example.com/images/image1.png');
INSERT INTO activity_image(id, activity_id, image) VALUES (2, 1, 'https://example.com/images/image2.png');
INSERT INTO activity_image(id, activity_id, image) VALUES (3, 1, 'https://example.com/images/image3.png');
INSERT INTO activity_participant(id, activity_id, member_id, status) VALUES (1, 1, 1, 'NONE');
INSERT INTO activity_participant(id, activity_id, member_id, status) VALUES (2, 1, 2, 'NONE');

-- 스터디 활동 (기본 활동)

-- 스터디 활동 (모임 활동)

-- 스터디 활동 (과제 활동)


