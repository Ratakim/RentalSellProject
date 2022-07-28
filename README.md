# RentalSpaceProject
The purpose of this project is to create a site that can be used as an Online Marketplace

* ORACLE 사용했습니다. *
----------------------------------------------------------
--회원 테이블
----------------------------------------------------------
create table user_info (
    id          varchar2(20)   CONSTRAINT const_id_pk   primary key
                               CONSTRAINT const_id_nn   NOT NULL,
    pwd         varchar2(40)   CONSTRAINT const_pwd_nn  NOT NULL,
    name        varchar2(80)   CONSTRAINT const_name_nn NOT NULL,
    email       varchar2(100),
    gender      varchar2(10)   NOT NULL,
    CONSTRAINTS gender_check CHECK (gender IN ('남성', '여성')), -- 남성 여성 중의 값만 허용한다.
    join_date    date default sysdate,
    last_join    date default sysdate
);

---------------------------------------------------------고객센터 게시판 START-----------------------------------------------------------

----------------------------------------------------------
--게시판 테이블
----------------------------------------------------------
CREATE table common_board (
    article_NO      number(10)      primary key,
    id              varchar2(10)    NOT NULL,
    board_type      varchar2(30)    NOT NULL,
    title           varchar2(100)   not null,
    content         varchar2(4000),
    write_date      date            default sysdate not null,
    CONSTRAINT FK_ID FOREIGN KEY(id)
    REFERENCES user_info(id)
);

-- 보드타입 조건 걸기 CHECK
alter table common_board add CONSTRAINTS board_type_check 
CHECK (board_type IN ('공지', '1', '자주', '사기'));

-- 게시판 시퀀스 테이블
CREATE SEQUENCE seq_common_board
START WITH      1
INCREMENT BY    1
MAXVALUE        10000
MINVALUE        1
NOCYCLE;

----------------------------------------------------------
--댓글 관련 테이블, 시퀀스
----------------------------------------------------------
CREATE TABLE boardReply (
   rno         number(10)          not null ,
   bno       number(10)        not null,
   id     varchar(30)     not null,
   content    varchar(1000)          not null,
   write_Date    date   default sysdate not null,
   PRIMARY KEY(rno, bno),
   FOREIGN KEY(bno) REFERENCES common_board(article_NO)
);

-- 댓글 게시판 시퀀스 테이블
create sequence Rep_seq
increment by 1
start with 1
maxvalue 100000
minvalue 1
nocycle;

---------------------------------------------------------고객센터 게시판 END-----------------------------------------------------------

---------------------------------------------------------상품 게시판 START-----------------------------------------------------------

----------------------------------------------------------
--카테고리 테이블
----------------------------------------------------------
create table goods_category (
    category_ID                 NUMBER          primary key NOT NULL,    -- NOT NULL 넣어야함
    category_LEV                NUMBER          Not NULL,
    category_NM                 varchar2(100)   NOT NULL,
    category_detail_LEV         NUMBER          NOT NULL,
    category_parent_LEV         NUMBER,
    category_detail_parent_LEV  NUMBER,
    group_ID                    char(2)         NOT NULL
);

-- 카테고리 대분류
INSERT INTO goods_category VALUES (1 , 1, '가전제품',         1, 0, 0, 'A');
INSERT INTO goods_category VALUES (2 , 1, '생활용품',         2, 0, 0, 'A');
INSERT INTO goods_category VALUES (3 , 1, '남성의류',         3, 0, 0, 'A');
INSERT INTO goods_category VALUES (4 , 1, '여성의류',         4, 0, 0, 'A');
INSERT INTO goods_category VALUES (5 , 1, '신발',            5, 0, 0, 'A');
INSERT INTO goods_category VALUES (6 , 1, '악세서리',         6, 0, 0, 'A');
INSERT INTO goods_category VALUES (7 , 1, '스포츠',          7, 0, 0, 'A');
INSERT INTO goods_category VALUES (8 , 1, '차량',           8, 0, 0, 'A');
INSERT INTO goods_category VALUES (9 , 1, '예술',           9, 0, 0, 'A');
INSERT INTO goods_category VALUES (10 , 1, '악기',          10, 0, 0, 'A');
INSERT INTO goods_category VALUES (11 , 1, '도서',          11, 0, 0, 'A');
INSERT INTO goods_category VALUES (12 , 1, '미용',          12, 0, 0, 'A');

-- 카테고리 중분류
INSERT INTO goods_category VALUES (101 , 2, '모바일',            1, 1, 1, 'A');
INSERT INTO goods_category VALUES (102 , 2, '생활가전',           2, 1, 1, 'A');
INSERT INTO goods_category VALUES (103 , 2, '모니터/빔프로젝트',  3, 1, 1, 'A');
INSERT INTO goods_category VALUES (104 , 2, 'PC/노트북',      4, 1, 1, 'A');
INSERT INTO goods_category VALUES (105 , 2, '게임기',      5, 1, 1, 'A');
INSERT INTO goods_category VALUES (106 , 2, '카메라',      6, 1, 1, 'A');
INSERT INTO goods_category VALUES (107 , 2, '공구',      1, 1, 2, 'A');
INSERT INTO goods_category VALUES (108 , 2, '주방용품',   2, 1, 2, 'A');
INSERT INTO goods_category VALUES (109 , 2, '아우터',     1, 1, 3, 'A');
INSERT INTO goods_category VALUES (110 , 2, '상의',      2, 1, 3, 'A');
INSERT INTO goods_category VALUES (111 , 2, '하의',      3, 1, 3, 'A');
INSERT INTO goods_category VALUES (112 , 2, '트레이닝',   4, 1, 3, 'A');
INSERT INTO goods_category VALUES (113 , 2, '정장',      5, 1, 3, 'A');
INSERT INTO goods_category VALUES (114 , 2, '기타',      6, 1, 3, 'A');
INSERT INTO goods_category VALUES (115 , 2, '아우터',     1, 1, 4, 'A');
INSERT INTO goods_category VALUES (116 , 2, '상의',      2, 1, 4, 'A');
INSERT INTO goods_category VALUES (117 , 2, '하의',      3, 1, 4, 'A');
INSERT INTO goods_category VALUES (118 , 2, '트레이닝',    4, 1, 4, 'A');
INSERT INTO goods_category VALUES (119 , 2, '정장',      5, 1, 4, 'A');
INSERT INTO goods_category VALUES (120 , 2, '기타',      6, 1, 4, 'A');
INSERT INTO goods_category VALUES (121 , 2, '스니커즈',      1, 1, 5, 'A');
INSERT INTO goods_category VALUES (122 , 2, '슬리퍼/샌들',   2 , 1, 5, 'A');
INSERT INTO goods_category VALUES (123 , 2, '구두/로퍼',    3 , 1, 5, 'A');
INSERT INTO goods_category VALUES (124 , 2, '워커/부츠',    4, 1, 5, 'A');
INSERT INTO goods_category VALUES (125 , 2, '단화',      5, 1, 5, 'A');
INSERT INTO goods_category VALUES (126 , 2, '모자',        1 , 1, 6, 'A');
INSERT INTO goods_category VALUES (127 , 2, '벨트',        2 , 1, 6, 'A');
INSERT INTO goods_category VALUES (128 , 2, '목도리/스카프', 3, 1, 6, 'A');
INSERT INTO goods_category VALUES (129 , 2, '안경',        4, 1, 6, 'A');
INSERT INTO goods_category VALUES (130 , 2, '시계',        5, 1, 6, 'A');
INSERT INTO goods_category VALUES (131 , 2, '쥬얼리',      6, 1, 6, 'A');
INSERT INTO goods_category VALUES (132 , 2, '기타',      7, 1, 6, 'A');
INSERT INTO goods_category VALUES (133 , 2, '골프',      1, 1, 7, 'A');
INSERT INTO goods_category VALUES (134 , 2, '낚시',      2, 1, 7, 'A');
INSERT INTO goods_category VALUES (135 , 2, '테니스',      3, 1, 7, 'A');
INSERT INTO goods_category VALUES (136 , 2, '등산/클라이밍',      4, 1, 7, 'A');
INSERT INTO goods_category VALUES (137 , 2, '헬스/요가',      5, 1, 7, 'A');
INSERT INTO goods_category VALUES (138 , 2, '구기스포츠',      6, 1, 7, 'A');
INSERT INTO goods_category VALUES (139 , 2, '자전거',      7, 1, 7, 'A');
INSERT INTO goods_category VALUES (140 , 2, '킥보드/전동킥보드',     8 , 1, 7, 'A');
INSERT INTO goods_category VALUES (141 , 2, '보드',     9 , 1, 7, 'A');
INSERT INTO goods_category VALUES (142 , 2, '기타',     10 , 1, 7, 'A');
INSERT INTO goods_category VALUES (143 , 2, '차량',      1, 1, 8, 'A');
INSERT INTO goods_category VALUES (144 , 2, '조각상',     1 , 1, 9, 'A');
INSERT INTO goods_category VALUES (145 , 2, '그림',      2, 1, 9, 'A');
INSERT INTO goods_category VALUES (146 , 2, '타악기',      1, 1, 10, 'A');
INSERT INTO goods_category VALUES (147 , 2, '관악기',     2 , 1, 10, 'A');
INSERT INTO goods_category VALUES (148 , 2, '현악기',     3 , 1, 10, 'A');
INSERT INTO goods_category VALUES (149 , 2, '건반악기',     4 , 1, 10, 'A');
INSERT INTO goods_category VALUES (150 , 2, '기타',     5 , 1, 10, 'A');
INSERT INTO goods_category VALUES (151 , 2, '시/에세이/소설',     1 , 1, 11, 'A');
INSERT INTO goods_category VALUES (152 , 2, '자기계발',     2 , 1, 11, 'A');
INSERT INTO goods_category VALUES (153 , 2, '인문',     3 , 1, 11, 'A');
INSERT INTO goods_category VALUES (154 , 2, '경제/경영',    4  , 1, 11, 'A');
INSERT INTO goods_category VALUES (155 , 2, '어린이',    5  , 1, 11, 'A');
INSERT INTO goods_category VALUES (156 , 2, '사전/참고서',    6  , 1, 11, 'A');
INSERT INTO goods_category VALUES (157 , 2, '만화',      7, 1, 11, 'A');
INSERT INTO goods_category VALUES (158 , 2, '여행/건강/취미',      8, 1, 11, 'A');
INSERT INTO goods_category VALUES (159 , 2, '사회/정치/법',     9 , 1, 11, 'A');
INSERT INTO goods_category VALUES (160 , 2, '과학/IT',      10, 1, 11, 'A');
INSERT INTO goods_category VALUES (161 , 2, '패션/예술/디자인',     11 , 1, 11, 'A');
INSERT INTO goods_category VALUES (162 , 2, '기타',     12 , 1, 11, 'A');
INSERT INTO goods_category VALUES (163 , 2, '스킨케어',      1, 1, 12, 'A');
INSERT INTO goods_category VALUES (164 , 2, '메이크업',      2, 1, 12, 'A');
INSERT INTO goods_category VALUES (165 , 2, '헤어/바디케어',     3 , 1, 12, 'A');
INSERT INTO goods_category VALUES (166 , 2, '향수',      4, 1, 12, 'A');
INSERT INTO goods_category VALUES (167 , 2, '네일',     5 , 1, 12, 'A');
INSERT INTO goods_category VALUES (168 , 2, '미용기기',      6, 1, 12, 'A');

----------------------------------------------------------
-- 상품 게시판 테이블
----------------------------------------------------------
CREATE TABLE goods_board (
    goods_code      NUMBER              NOT NULL,    
    category_ID     NUMBER              NOT NULL,                                 
    user_ID         varchar2(100)       NOT NULL,                          
    goods_title     varchar2(200)       NOT NULL,
    deal_region     varchar2(250)       NOT NULL,
    goods_desc      varchar2(1500),
    goods_price     NUMBER              NOT NULL,
    create_date     DATE                default SYSDATE NOT NULL,
    goods_hit       NUMBER              default 0   NOT NULL,
    CONSTRAINT FK_CATEGORY_ID   FOREIGN KEY(category_ID) REFERENCES goods_category(category_ID),
    CONSTRAINT FK_USER_ID       FOREIGN KEY(user_ID)     REFERENCES user_info(id),
    primary key(goods_code)
);

-- 상품 게시판 SEQ
CREATE SEQUENCE "SEQ_GOODS_ID" MINVALUE 1 MAXVALUE 10000000 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER NOCYCLE;

----------------------------------------------------------
--상품 이미지 파일 테이블
----------------------------------------------------------
CREATE TABLE goods_imagefile (
    file_num            int             NOT NULL,
    goods_code          int             NOT NULL,
    origin_file_name    varchar2(100)   NOT NULL,
    stored_file_name    varchar2(100)   NOT NULL, -- 인덱스_카테고리이름
    stored_thumbNail    int             NOT NULL,
    CONSTRAINTS stored_thumbNail_boolean CHECK (stored_thumbNail IN (0, 1)),
    file_size           INT             NOT NULL,
    create_date         DATE            default SYSDATE NOT NULL,
    delete_check        int default 0 NOT NULL,
    CONSTRAINTS delete_check_boolean CHECK (delete_check IN (0, 1)), -- 삭제 예정인지를 체크
    CONSTRAINT FK_GOODS_CODE      FOREIGN KEY(goods_code)     REFERENCES goods_board(goods_code),
    primary key(file_num)
);

-- 상품 이미지 SEQ
CREATE SEQUENCE "SEQ_GOODS_IMG" MINVALUE 1 MAXVALUE 10000000 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER NOCYCLE;
select sequence_name  from user_sequences;

----------------------------------------------------------
--제품 예약 테이블
----------------------------------------------------------
CREATE TABLE transaction (
    reserve_NUM             INT             NOT NULL,    
    user_ID                 varchar2(100)    NOT NULL,
    goods_code              INT             NOT NULL,
    total_price             INT             NOT NULL,
    borrow_period_start     DATE            NOT NULL,
    borrow_period_end       DATE            NOT NULL,
    transaction_status      varchar2(100)  default '예약승인중' NOT NULL,
    create_date             DATE  default SYSDATE NOT NULL,
    CONSTRAINTS STATUS_check CHECK (transaction_status IN ('예약승인중', '구매자입금중', '결제완료', '제품인수완료','반납완료')),
    CONSTRAINT FK_GOODS_CODE_RESERVE    FOREIGN KEY(goods_code) REFERENCES goods_board(goods_code),
    CONSTRAINT FK_USER_ID_RESERVE       FOREIGN KEY(user_ID)     REFERENCES user_info(id),
    primary key(reserve_NUM)
);

-- 상품 예약 SEQ
CREATE SEQUENCE "SEQ_RESERVE_NUM" MINVALUE 1 MAXVALUE 10000000 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER NOCYCLE;

---------------------------------------------------------상품 게시판 END-----------------------------------------------------------

---------------------------------------------------------기타 테이블 START-----------------------------------------------------------

----------------------------------------------------------
--은행 계좌
----------------------------------------------------------
CREATE table Account(
    no           number(10)      ,
    id           varchar2(10)    NOT NULL,
    bank         varchar2(20) not null,
    account      varchar2(30)    NOT NULL,
    money        number(30)      ,
    PRIMARY KEY(no  , bank),
    FOREIGN KEY(id) REFERENCES user_info(id)
);

--은행 계좌 SEQ
create sequence account_seq
increment by 1
start with 1
maxvalue 100000
minvalue 1
nocycle;

----------------------------------------------------------
-- 카드등록
----------------------------------------------------------
CREATE table card(
    no           number(10)      ,
    id           varchar2(10)    NOT NULL,
    bank         varchar2(20) not null,
    card_NO      number(10)      ,
    cvc      varchar2(30)    NOT NULL,
    money        number(30)      ,
    PRIMARY KEY(no  , card_no),
    FOREIGN KEY(id) REFERENCES user_info(id)
);

--카드 등록 SEQ
create sequence card_seq
increment by 1
start with 1
maxvalue 100000
minvalue 1
nocycle;

---------------------------------------------------------기타 테이블 END-----------------------------------------------------------
