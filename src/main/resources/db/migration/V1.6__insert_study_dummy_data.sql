INSERT INTO study_domain (id, study_field_id) VALUES (1, 6);

INSERT INTO study (id, study_domain_id, name, region, intro, rule, image, start_date, end_date)
VALUES (1, 1, '[임시] 프로그래밍 스터디', '서울', '프로그래밍 스터디 입니다.', '- 매주 목요일 8시\n- 장소: 안암역\n- 제시간에 제출하기!',
        'https://stumeet.s3.ap-northeast-2.amazonaws.com/study/1/image/2023062711172178420.png',
        '2024-04-01', '2024-05-01');

INSERT INTO study_tag (study_domain_id, name) VALUES (1, 'springboot');
INSERT INTO study_tag (study_domain_id, name) VALUES (1, 'java');
INSERT INTO study_tag (study_domain_id, name) VALUES (1, '객체지향프로그래밍');

