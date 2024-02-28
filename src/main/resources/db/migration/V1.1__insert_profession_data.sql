INSERT INTO `profession` (name, parent_id)
VALUES ('경영사무', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '기획전략경영', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '경영분석·컨설턴트', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '고객상담·인바운드', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '감리·공무', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '결산', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '경리', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '인사', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '영업', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '영업관리·지원·영업기획', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '재무', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '사무', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '사무보조', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '노무', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '해외영업·무역영업', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '헤드헌터', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT '총무', id FROM profession WHERE name = '경영사무';
INSERT INTO `profession` (name, parent_id) SELECT 'IR', id FROM profession WHERE name = '경영사무';

INSERT INTO profession (name, parent_id)
VALUES ('마케팅·광고·홍보', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '광고제작·카피·CF', id FROM profession WHERE name = '마케팅·광고·홍보';
INSERT INTO `profession` (name, parent_id) SELECT '리서치·통계·설문', id FROM profession WHERE name = '마케팅·광고·홍보';
INSERT INTO `profession` (name, parent_id) SELECT '마케팅', id FROM profession WHERE name = '마케팅·광고·홍보';
INSERT INTO `profession` (name, parent_id) SELECT '매장관리', id FROM profession WHERE name = '마케팅·광고·홍보';
INSERT INTO `profession` (name, parent_id) SELECT '상품기획·MD', id FROM profession WHERE name = '마케팅·광고·홍보';

INSERT INTO profession (name, parent_id)
VALUES ('디자인', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '광고디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '그래픽디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '의류·패션·잡화디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT 'UI/UX 디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '시각디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '실내디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '전시·공간디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '조경디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '제품·산업디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT '캐릭터·애니매이션디자인', id FROM profession WHERE name = '디자인';
INSERT INTO `profession` (name, parent_id) SELECT 'CG디자인', id FROM profession WHERE name = '디자인';

INSERT INTO profession (name, parent_id)
VALUES ('무역·유통', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '배송·택배·운송', id FROM profession WHERE name = '무역·유통';
INSERT INTO `profession` (name, parent_id) SELECT '유통·물류·재고', id FROM profession WHERE name = '무역·유통';
INSERT INTO `profession` (name, parent_id) SELECT '수출입·무역 사무', id FROM profession WHERE name = '무역·유통';

INSERT INTO profession (name, parent_id)
VALUES ('영업·고객상담', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '직업상담', id FROM profession WHERE name = '영업·고객상담';
INSERT INTO `profession` (name, parent_id) SELECT '보험·보상', id FROM profession WHERE name = '영업·고객상담';
INSERT INTO `profession` (name, parent_id) SELECT '아웃바운드·TM', id FROM profession WHERE name = '영업·고객상담';
INSERT INTO `profession` (name, parent_id) SELECT '부동산·중개·분양·경매', id FROM profession WHERE name = '영업·고객상담';

INSERT INTO profession (name, parent_id)
VALUES ('서비스', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '가사·육아', id FROM profession WHERE name = '서비스';
INSERT INTO `profession` (name, parent_id) SELECT '요양보호', id FROM profession WHERE name = '서비스';
INSERT INTO `profession` (name, parent_id) SELECT '숙박', id FROM profession WHERE name = '서비스';
INSERT INTO `profession` (name, parent_id) SELECT '사회복지', id FROM profession WHERE name = '서비스';
INSERT INTO `profession` (name, parent_id) SELECT '설치·장비·A/S', id FROM profession WHERE name = '서비스';

INSERT INTO profession (name, parent_id)
VALUES ('연구개발·설계', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '시스템설계', id FROM profession WHERE name = '연구개발·설계';
INSERT INTO `profession` (name, parent_id) SELECT '시스템분석', id FROM profession WHERE name = '연구개발·설계';
INSERT INTO `profession` (name, parent_id) SELECT '전기·소방·통신·안전', id FROM profession WHERE name = '연구개발·설계';
INSERT INTO `profession` (name, parent_id) SELECT '전기·전자·제어', id FROM profession WHERE name = '연구개발·설계';

INSERT INTO profession (name, parent_id)
VALUES ('교육', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '강사', id FROM profession WHERE name = '교육';
INSERT INTO `profession` (name, parent_id) SELECT '교사', id FROM profession WHERE name = '교육';

INSERT INTO profession (name, parent_id)
VALUES ('건설·건축', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '구매·자재', id FROM profession WHERE name = '건설·건축';
INSERT INTO `profession` (name, parent_id) SELECT '기계설계', id FROM profession WHERE name = '건설·건축';
INSERT INTO `profession` (name, parent_id) SELECT '인테리어', id FROM profession WHERE name = '건설·건축';
INSERT INTO `profession` (name, parent_id) SELECT '화물·중장비', id FROM profession WHERE name = '건설·건축';
INSERT INTO `profession` (name, parent_id) SELECT '토목·조경·도시·측량', id FROM profession WHERE name = '건설·건축';
INSERT INTO `profession` (name, parent_id) SELECT '시공·현장', id FROM profession WHERE name = '건설·건축';

INSERT INTO profession (name, parent_id)
VALUES ('미디어·문화', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '공연·전시', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '기자', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '연예·엔터테인먼트', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '무대·스텝', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '문화콘텐츠 제작', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '영상 제작', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '영화·배급', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '레저·스포츠', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '아나운서·리포터·성우', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '음악·음향', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '문화콘텐츠 유통·서비스', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '인쇄·출판·편집', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '작가·시나리오', id FROM profession WHERE name = '미디어·문화';
INSERT INTO `profession` (name, parent_id) SELECT '전시 기획', id FROM profession WHERE name = '미디어·문화';

INSERT INTO profession (name, parent_id)
VALUES ('전문·특수·연구직', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '바이오·제약·식품', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '바리스타', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '도서관 사서', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '법률·특허·상표', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '법무', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '법인영업', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '제과제빵사', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '영양사', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '요리사', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '애견 미용', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '뷰티·미용', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '채권·심사', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '외국어·번역·통역', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '항공', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT 'CPA', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT 'CAD', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT 'CAM', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '화학·에너지·환경', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '이벤트·웨딩·도우미', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '연구소·R&D', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '자동차·조선·기계', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '운전·기사', id FROM profession WHERE name = '전문·특수·연구직';
INSERT INTO `profession` (name, parent_id) SELECT '보안·경호', id FROM profession WHERE name = '전문·특수·연구직';

INSERT INTO profession (name, parent_id)
VALUES ('IT', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '게임 개발', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '데이터베이스', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '빅데이터', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '네트워크·서버·보안', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '웹프로그래머', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT 'PM', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT 'QA', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '응용프로그래머', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '반도체·디스플레이', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT 'AI(인공지능)', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '프론트엔드', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '하드웨어 관리자', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '통신기술·네트워크구축', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT '퍼블리셔', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT 'CS관리·강의', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT 'DBA', id FROM profession WHERE name = 'IT';
INSERT INTO `profession` (name, parent_id) SELECT 'ERP', id FROM profession WHERE name = 'IT';

INSERT INTO profession (name, parent_id)
VALUES ('의료', NULL);

INSERT INTO `profession` (name, parent_id) SELECT '간호 조무사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '간호사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '치과 의사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '의사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '약무보조', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '약사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '의료기사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '의료직기타', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '병원 코디네이터', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '수의간호사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '수의사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '한약사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '한의사', id FROM profession WHERE name = '의료';
INSERT INTO `profession` (name, parent_id) SELECT '원무', id FROM profession WHERE name = '의료';