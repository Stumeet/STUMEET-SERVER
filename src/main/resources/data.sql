/**
  PROFESSION
 */

-- 경영사무
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (1, '경영사무', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (2, '기획전략경영', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (3, '경영분석·컨설턴트', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (4, '고객상담·인바운드', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (5, '감리·공무', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (6, '결산', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (7, '경리', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (8, '인사', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (9, '영업', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (10, '영업관리·지원·영업기획', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (11, '재무', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (12, '사무', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (13, '사무보조', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (14, '노무', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (15, '해외영업·무역영업', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (16, '헤드헌터', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (17, '총무', 1);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (18, 'IR', 1);

-- 마케팅·광고·홍보
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (19, '마케팅·광고·홍보', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (20, '광고제작·카피·CF', 19);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (21, '리서치·통계·설문', 19);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (22, '마케팅', 19);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (23, '매장관리', 19);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (24, '상품기획·MD', 19);

-- 디자인
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (25, '디자인', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (26, '광고디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (27, '그래픽디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (28, '의류·패션·잡화디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (29, 'UI/UX 디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (30, '시각디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (31, '실내디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (32, '전시·공간디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (33, '조경디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (34, '제품·산업디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (35, '캐릭터·애니매이션디자인', 25);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (36, 'CG디자인', 25);

-- 무역·유통
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (37, '무역·유통', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (38, '배송·택배·운송', 37);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (39, '유통·물류·재고', 37);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (40, '수출입·무역 사무', 37);

-- 영업·고객상담
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (41, '영업·고객상담', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (42, '직업상담', 41);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (43, '보험·보상', 41);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (44, '아웃바운드·TM', 41);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (45, '부동산·중개·분양·경매', 41);

-- 서비스
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (46, '서비스', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (47, '가사·육아', 46);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (48, '요양보호', 46);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (49, '숙박', 46);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (50, '사회복지', 46);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (51, '설치·장비·A/S', 46);

-- 연구개발·설계
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (52, '연구개발·설계', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (53, '시스템설계', 52);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (54, '시스템분석', 52);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (55, '전기·소방·통신·안전', 52);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (56, '전기·전자·제어', 52);

-- 교육
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (57, '교육', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (58, '강사', 57);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (59, '교사', 57);

-- 건설·건축
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (60, '건설·건축', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (61, '구매·자재', 60);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (62, '기계설계', 60);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (63, '인테리어', 60);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (64, '화물·중장비', 60);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (65, '토목·조경·도시·측량', 60);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (66, '시공·현장', 60);

-- 미디어·문화
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (67, '미디어·문화', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (68, '공연·전시', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (69, '기자', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (70, '연예·엔터테인먼트', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (71, '무대·스텝', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (72, '문화콘텐츠 제작', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (73, '영상 제작', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (74, '영화·배급', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (75, '레저·스포츠', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (76, '아나운서·리포터·성우', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (77, '음악·음향', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (78, '문화콘텐츠 유통·서비스', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (79, '인쇄·출판·편집', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (80, '작가·시나리오', 67);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (81, '전시 기획', 67);

-- 전문·특수·연구직
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (82, '전문·특수·연구직', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (83, '바이오·제약·식품', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (84, '바리스타', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (85, '도서관 사서', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (86, '법률·특허·상표', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (87, '법무', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (88, '법인영업', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (89, '제과제빵사', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (90, '영양사', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (91, '요리사', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (92, '애견 미용', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (93, '뷰티·미용', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (94, '채권·심사', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (95, '외국어·번역·통역', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (96, '항공', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (97, 'CPA', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (98, 'CAD', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (99, 'CAM', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (100, '화학·에너지·환경', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (101, '이벤트·웨딩·도우미', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (102, '연구소·R&D', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (103, '자동차·조선·기계', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (104, '운전·기사', 82);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (105, '보안·경호', 82);

-- IT
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (106, 'IT', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (107, '게임 개발', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (108, '데이터베이스', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (109, '빅데이터', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (110, '네트워크·서버·보안', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (111, '웹프로그래머', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (112, 'PM', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (113, 'QA', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (114, '응용프로그래머', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (115, '반도체·디스플레이', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (116, 'AI(인공지능)', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (117, '프론트엔드', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (118, '하드웨어 관리자', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (119, '통신기술·네트워크구축', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (120, '퍼블리셔', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (121, 'CS관리·강의', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (122, 'DBA', 106);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (123, 'ERP', 106);

-- 의료
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (124, '의료', NULL);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (125, '간호 조무사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (126, '간호사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (127, '치과 의사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (128, '의사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (129, '약무보조', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (130, '약사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (131, '의료기사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (132, '의료직기타', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (133, '병원 코디네이터', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (134, '수의간호사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (135, '수의사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (136, '한약사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (137, '한의사', 124);
INSERT IGNORE INTO `profession` (id, name, parent_id) VALUES (138, '원무', 124);