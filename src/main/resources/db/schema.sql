CREATE TABLE IF NOT EXISTS dogwhiz.pet_breed
(
    `breed_id`    BIGINT         NOT NULL    AUTO_INCREMENT COMMENT '품종번호',
    `breed_name`  VARCHAR(45)    NOT NULL    COMMENT '품종명',
    `fic`         INT            NOT NULL    COMMENT 'FCI그룹',
    PRIMARY KEY (breed_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.member
(
    `member_id`        BIGINT          NOT NULL    AUTO_INCREMENT COMMENT '회원번호',
    `email`            VARCHAR(100)    NOT NULL    COMMENT '이메일',
    `password`         VARCHAR(255)     NOT NULL    COMMENT '비밀번호',
    `nickname`         VARCHAR(50)     NOT NULL    COMMENT '닉네임',
    `auth_cd`          VARCHAR(10)     NOT NULL    COMMENT '권한구분코드',
    `status_cd`        VARCHAR(10)     NOT NULL    COMMENT '상태구분코드',
    `login_ip`         VARCHAR(45)     NULL        COMMENT '로그인IP',
    `last_login_date`  DATETIME        NULL        COMMENT '마지막 로그인 날짜',
    `update_pwd_date`  DATETIME        NULL        COMMENT '비밀번호 변경 날짜',
    `email_auth`       TINYINT         NOT NULL    DEFAULT 0 COMMENT '이메일인증',
    `email_key`        VARCHAR(100)    NULL        COMMENT '이메일 인증키',
    `created_at`       DATETIME        NOT NULL    COMMENT '가입날짜',
    `updated_at`       DATETIME        NULL        COMMENT '수정날짜',
    `deleted_at`       DATETIME        NULL        COMMENT '탈퇴날짜',
    PRIMARY KEY (member_id)
);

CREATE UNIQUE INDEX UQ_member
    ON dogwhiz.member(email, nickname);


CREATE TABLE IF NOT EXISTS dogwhiz.board
(
    `board_id`    BIGINT         NOT NULL    AUTO_INCREMENT COMMENT '게시판 번호',
    `board_name`  VARCHAR(45)    NOT NULL    COMMENT '게시판 종류',
    PRIMARY KEY (board_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.pet
(
    `pet_id`     BIGINT         NOT NULL    AUTO_INCREMENT COMMENT '반려동물번호',
    `member_id`  BIGINT         NULL        COMMENT '회원번호',
    `name`       VARCHAR(45)    NULL        COMMENT '이름',
    `breed_id`   BIGINT         NULL        COMMENT '품종번호',
    `birth`      DATE           NULL        COMMENT '생년월일',
    `gender`     TINYINT        NULL        COMMENT '성별',
    `surgery`    TINYINT        NULL        COMMENT '중성화',
    `weight`     INT            NULL        COMMENT '몸무게',
    PRIMARY KEY (pet_id)
);



CREATE TABLE IF NOT EXISTS dogwhiz.post
(
    `post_id`     BIGINT          NOT NULL    AUTO_INCREMENT COMMENT '게시글 번호',
    `board_id`    BIGINT          NOT NULL    COMMENT '게시판 번호',
    `title`       VARCHAR(100)    NOT NULL    COMMENT '제목',
    `content`     TEXT            NOT NULL    COMMENT '내용',
    `created_at`  DATETIME        NOT NULL    COMMENT '작성일',
    `updated_at`  DATETIME        NULL        COMMENT '수정일',
    `deleted_at`  DATETIME        NULL        COMMENT '삭제일',
    `like_count`  INT             NOT NULL    DEFAULT 0 COMMENT '추천수',
    `view_count`  INT             NOT NULL    DEFAULT 0 COMMENT '조회수',
    `member_id`   BIGINT          NOT NULL    COMMENT '작성자',
    PRIMARY KEY (post_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.member_image
(
    `member_id`   BIGINT          NOT NULL    COMMENT '회원번호',
    `file_name`   VARCHAR(255)    NOT NULL    COMMENT '파일이름',
    `file_url`    VARCHAR(255)    NOT NULL    COMMENT '파일URL',
    `file_path`   VARCHAR(255)    NOT NULL    COMMENT '파일경로',
    `file_size`   INT             NOT NULL    COMMENT '파일크기',
    `created_at`  DATETIME        NOT NULL    COMMENT '생성날짜',
    `updated_at`  DATETIME        NULL        COMMENT '수정날짜',
    `deleted_at`  DATETIME        NULL        COMMENT '삭제날짜',
    PRIMARY KEY (member_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.member_detail
(
    `member_id`  BIGINT         NOT NULL    COMMENT '회원번호',
    `name`       TINYINT        NOT NULL    COMMENT '성별. 0: 남성 / 1: 여성',
    `birth`      DATE           NOT NULL    COMMENT '생년월일',
    `phone`      VARCHAR(45)    NOT NULL    COMMENT '전화번호',
    PRIMARY KEY (member_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.member_address
(
    `address_id`      BIGINT          NOT NULL    AUTO_INCREMENT COMMENT '회원주소번호',
    `member_id`       BIGINT          NOT NULL    COMMENT '회원번호',
    `zipcode`         VARCHAR(255)    NOT NULL    COMMENT '우편번호',
    `street`          VARCHAR(255)    NOT NULL    COMMENT '도로명주소',
    `address_detail`  VARCHAR(255)    NOT NULL    COMMENT '상세주소',
    PRIMARY KEY (address_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.pet_image
(
    `pet_id`      BIGINT          NOT NULL    COMMENT '반려동물번호',
    `file_name`   VARCHAR(255)    NOT NULL    COMMENT '파일이름',
    `file_url`    VARCHAR(255)    NOT NULL    COMMENT '파일URL',
    `file_path`   VARCHAR(255)    NOT NULL    COMMENT '파일경로',
    `file_size`   INT             NOT NULL    COMMENT '파일크기',
    `created_at`  DATETIME        NOT NULL    COMMENT '생성날짜',
    `updated_at`  DATETIME        NULL        COMMENT '수정날짜',
    `deleted_at`  DATETIME        NULL        COMMENT '삭제날짜',
    PRIMARY KEY (pet_id)
);

CREATE TABLE IF NOT EXISTS dogwhiz.favorite_board
(
    `member_id`  BIGINT    NOT NULL    COMMENT '회원번호',
    `board_id`   BIGINT    NOT NULL    COMMENT '게시판 번호',
    `post_id`    BIGINT    NULL        COMMENT '게시글 번호',
    PRIMARY KEY (member_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.like_post
(
    `member_id`  BIGINT    NOT NULL    COMMENT '회원번호',
    `post_id`    BIGINT    NOT NULL    COMMENT '게시글 번호',
    PRIMARY KEY (member_id)
);


CREATE TABLE IF NOT EXISTS dogwhiz.reply
(
    `reply_id`    INT         NOT NULL    AUTO_INCREMENT COMMENT '댓글 번호',
    `post_id`     BIGINT      NOT NULL    COMMENT '게시글 번호',
    `member_id`   BIGINT      NOT NULL    COMMENT '회원번호',
    `content`     TEXT        NOT NULL    COMMENT '내용',
    `like_count`  INT         NOT NULL    DEFAULT 0 COMMENT '추천수',
    `created_at`  DATETIME    NOT NULL    COMMENT '작성일',
    `updated_at`  DATETIME    NULL        COMMENT '수정일',
    `deleted_at`  DATETIME    NULL        COMMENT '삭제일',
    `parent_id`   BIGINT      NULL        COMMENT '부모 댓글. null 이 아니면 대댓글',
    PRIMARY KEY (reply_id)
);



