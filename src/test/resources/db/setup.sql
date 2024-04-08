INSERT INTO study_domain (id, study_field_id) VALUES (1, 6);

INSERT INTO study (id, study_domain_id, name, region, intro, rule, image,
                   meeting_time, meeting_repetition, start_date, end_date)
VALUES (1, 1, '[임시] 프로그래밍 스터디', '서울', '프로그래밍 스터디 입니다.', '- 매주 목요일 8시\n- 장소: 안암역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '21:00:00', 'WEEKLY;월;수;목;', '2024-04-01', '2024-05-01');

INSERT INTO study_tag (study_domain_id, name) VALUES (1, 'springboot');
INSERT INTO study_tag (study_domain_id, name) VALUES (1, 'java');
INSERT INTO study_tag (study_domain_id, name) VALUES (1, '객체지향프로그래밍');

INSERT INTO member (id, name, image, region, profession_id, role, auth_type, tier, experience, is_deleted, deleted_at)
VALUES (1, 'test1', 'http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg',
        '서울', 1, 'FIRST_LOGIN', 'OAUTH', 'SEED', 0.0, false, null);

INSERT INTO study_member(member_id, study_id, is_admin, is_sent_grape)
VALUES (1, 1, true, false);