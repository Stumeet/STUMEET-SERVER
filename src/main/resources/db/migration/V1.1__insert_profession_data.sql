INSERT INTO `profession` (name, parent_id)
VALUES ('경영사무', NULL);

INSERT INTO `profession` (name, parent_id)
VALUES ('기획전략경영', (SELECT id FROM profession WHERE name = '경영사무')),
       ('경영분석·컨설턴트', (SELECT id FROM profession WHERE name = '경영사무')),
       ('고객상담·인바운드', (SELECT id FROM profession WHERE name = '경영사무')),
       ('감리·공무', (SELECT id FROM profession WHERE name = '경영사무')),
       ('결산', (SELECT id FROM profession WHERE name = '경영사무')),
       ('경리', (SELECT id FROM profession WHERE name = '경영사무')),
       ('인사', (SELECT id FROM profession WHERE name = '경영사무')),
       ('영업', (SELECT id FROM profession WHERE name = '경영사무')),
       ('영업관리·지원·영업기획', (SELECT id FROM profession WHERE name = '경영사무')),
       ('재무', (SELECT id FROM profession WHERE name = '경영사무')),
       ('사무', (SELECT id FROM profession WHERE name = '경영사무')),
       ('사무보조', (SELECT id FROM profession WHERE name = '경영사무')),
       ('노무', (SELECT id FROM profession WHERE name = '경영사무')),
       ('해외영업·무역영업', (SELECT id FROM profession WHERE name = '경영사무')),
       ('헤드헌터', (SELECT id FROM profession WHERE name = '경영사무')),
       ('총무', (SELECT id FROM profession WHERE name = '경영사무')),
       ('IR', (SELECT id FROM profession WHERE name = '경영사무'));

INSERT INTO profession (name, parent_id)
VALUES ('마케팅·광고·홍보', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('광고제작·카피·CF', (SELECT id FROM profession WHERE name = '마케팅·광고·홍보')),
       ('리서치·통계·설문', (SELECT id FROM profession WHERE name = '마케팅·광고·홍보')),
       ('마케팅', (SELECT id FROM profession WHERE name = '마케팅·광고·홍보')),
       ('매장관리', (SELECT id FROM profession WHERE name = '마케팅·광고·홍보')),
       ('상품기획·MD', (SELECT id FROM profession WHERE name = '마케팅·광고·홍보'));

INSERT INTO profession (name, parent_id)
VALUES ('디자인', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('광고디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('그래픽디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('의류·패션·잡화디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('UI/UX 디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('시각디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('실내디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('전시·공간디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('조경디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('제품·산업디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('캐릭터·애니매이션디자인', (SELECT id FROM profession WHERE name = '디자인')),
       ('CG디자인', (SELECT id FROM profession WHERE name = '디자인'));

INSERT INTO profession (name, parent_id)
VALUES ('무역·유통', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('배송·택배·운송', (SELECT id FROM profession WHERE name = '무역·유통')),
       ('유통·물류·재고', (SELECT id FROM profession WHERE name = '무역·유통')),
       ('수출입·무역 사무', (SELECT id FROM profession WHERE name = '무역·유통'));

INSERT INTO profession (name, parent_id)
VALUES ('영업·고객상담', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('직업상담', (SELECT id FROM profession WHERE name = '영업·고객상담')),
       ('보험·보상', (SELECT id FROM profession WHERE name = '영업·고객상담')),
       ('아웃바운드·TM', (SELECT id FROM profession WHERE name = '영업·고객상담')),
       ('부동산·중개·분양·경매', (SELECT id FROM profession WHERE name = '영업·고객상담'));

INSERT INTO profession (name, parent_id)
VALUES ('서비스', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('가사·육아', (SELECT id FROM profession WHERE name = '서비스')),
       ('요양보호', (SELECT id FROM profession WHERE name = '서비스')),
       ('숙박', (SELECT id FROM profession WHERE name = '서비스')),
       ('사회복지', (SELECT id FROM profession WHERE name = '서비스')),
       ('설치·장비·A/S', (SELECT id FROM profession WHERE name = '서비스'));

INSERT INTO profession (name, parent_id)
VALUES ('연구개발·설계', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('시스템설계', (SELECT id FROM profession WHERE name = '연구개발·설계')),
       ('시스템분석', (SELECT id FROM profession WHERE name = '연구개발·설계')),
       ('전기·소방·통신·안전', (SELECT id FROM profession WHERE name = '연구개발·설계')),
       ('전기·전자·제어', (SELECT id FROM profession WHERE name = '연구개발·설계'));

INSERT INTO profession (name, parent_id)
VALUES ('교육', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('강사', (SELECT id FROM profession WHERE name = '교육')),
       ('교사', (SELECT id FROM profession WHERE name = '교육'));

INSERT INTO profession (name, parent_id)
VALUES ('건설·건축', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('구매·자재', (SELECT id FROM profession WHERE name = '건설·건축')),
       ('기계설계', (SELECT id FROM profession WHERE name = '건설·건축')),
       ('인테리어', (SELECT id FROM profession WHERE name = '건설·건축')),
       ('화물·중장비', (SELECT id FROM profession WHERE name = '건설·건축')),
       ('토목·조경·도시·측량', (SELECT id FROM profession WHERE name = '건설·건축')),
       ('시공·현장', (SELECT id FROM profession WHERE name = '건설·건축'));

INSERT INTO profession (name, parent_id)
VALUES ('미디어·문화', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('공연·전시', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('기자', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('연예·엔터테인먼트', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('무대·스텝', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('문화콘텐츠 제작', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('영상 제작', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('영화·배급', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('레저·스포츠', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('아나운서·리포터·성우', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('음악·음향', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('문화콘텐츠 유통·서비스', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('인쇄·출판·편집', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('작가·시나리오', (SELECT id FROM profession WHERE name = '미디어·문화')),
       ('전시 기획', (SELECT id FROM profession WHERE name = '미디어·문화'));

INSERT INTO profession (name, parent_id)
VALUES ('전문·특수·연구직', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('바이오·제약·식품', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('바리스타', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('도서관 사서', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('법률·특허·상표', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('법무', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('법인영업', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('제과제빵사', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('영양사', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('요리사', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('애견 미용', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('뷰티·미용', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('채권·심사', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('외국어·번역·통역', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('항공', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('CPA', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('CAD', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('CAM', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('화학·에너지·환경', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('이벤트·웨딩·도우미', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('연구소·R&D', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('자동차·조선·기계', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('운전·기사', (SELECT id FROM profession WHERE name = '전문·특수·연구직')),
       ('보안·경호', (SELECT id FROM profession WHERE name = '전문·특수·연구직'));

INSERT INTO profession (name, parent_id)
VALUES ('IT', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('게임 개발', (SELECT id FROM profession WHERE name = 'IT')),
       ('데이터베이스', (SELECT id FROM profession WHERE name = 'IT')),
       ('빅데이터', (SELECT id FROM profession WHERE name = 'IT')),
       ('네트워크·서버·보안', (SELECT id FROM profession WHERE name = 'IT')),
       ('웹프로그래머', (SELECT id FROM profession WHERE name = 'IT')),
       ('PM', (SELECT id FROM profession WHERE name = 'IT')),
       ('QA', (SELECT id FROM profession WHERE name = 'IT')),
       ('응용프로그래머', (SELECT id FROM profession WHERE name = 'IT')),
       ('반도체·디스플레이', (SELECT id FROM profession WHERE name = 'IT')),
       ('AI(인공지능)', (SELECT id FROM profession WHERE name = 'IT')),
       ('프론트엔드', (SELECT id FROM profession WHERE name = 'IT')),
       ('하드웨어 관리자', (SELECT id FROM profession WHERE name = 'IT')),
       ('통신기술·네트워크구축', (SELECT id FROM profession WHERE name = 'IT')),
       ('퍼블리셔', (SELECT id FROM profession WHERE name = 'IT')),
       ('CS관리·강의', (SELECT id FROM profession WHERE name = 'IT')),
       ('DBA', (SELECT id FROM profession WHERE name = 'IT')),
       ('ERP', (SELECT id FROM profession WHERE name = 'IT'));

INSERT INTO profession (name, parent_id)
VALUES ('의료', NULL);

INSERT INTO profession (name, parent_id)
VALUES ('간호 조무사', (SELECT id FROM profession WHERE name = '의료')),
       ('간호사', (SELECT id FROM profession WHERE name = '의료')),
       ('치과 의사', (SELECT id FROM profession WHERE name = '의료')),
       ('의사', (SELECT id FROM profession WHERE name = '의료')),
       ('약무보조', (SELECT id FROM profession WHERE name = '의료')),
       ('약사', (SELECT id FROM profession WHERE name = '의료')),
       ('의료기사', (SELECT id FROM profession WHERE name = '의료')),
       ('의료직기타', (SELECT id FROM profession WHERE name = '의료')),
       ('병원 코디네이터', (SELECT id FROM profession WHERE name = '의료')),
       ('수의간호사', (SELECT id FROM profession WHERE name = '의료')),
       ('수의사', (SELECT id FROM profession WHERE name = '의료')),
       ('한약사', (SELECT id FROM profession WHERE name = '의료')),
       ('한의사', (SELECT id FROM profession WHERE name = '의료')),
       ('원무', (SELECT id FROM profession WHERE name = '의료'));