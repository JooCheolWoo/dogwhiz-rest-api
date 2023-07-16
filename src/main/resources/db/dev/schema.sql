CREATE TABLE IF NOT EXISTS `member` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원번호',
    `email` VARCHAR(100) NOT NULL COMMENT '이메일',
    `nickname` VARCHAR(50) NOT NULL COMMENT '닉네임',
    `password` VARCHAR(100) NOT NULL COMMENT '비밀번호',
    `status` ENUM('APPROVED', 'PENDING', 'SUSPENDED', 'DEACTIVATED') NOT NULL COMMENT '회원상태',
    `name` VARCHAR(20),
    `gender` TINYINT(1),
    `birth` DATE,
    `phone` VARCHAR(20),
    `image_url` VARCHAR(255),
    `image_path` VARCHAR(255),
    `login_ip` VARCHAR(45) COMMENT '로그인 IP',
    `last_login_date` DATETIME COMMENT '마지막 로그인 날짜',
    `update_pwd_date` DATETIME NOT NULL COMMENT '마지막 비밀번호 수정 날짜',
    `email_auth` TINYINT(1) DEFAULT 0 NOT NULL COMMENT '이메일 인증 여부',
    `email_key` VARCHAR(100) COMMENT '이메일 인증키',
    `created_at` DATETIME COMMENT '가입날짜',
    `updated_at` DATETIME COMMENT '수정날짜',
    `deleted_at` DATETIME COMMENT '탈퇴날짜',
    PRIMARY KEY (`id`),
    UNIQUE (`email`),
    UNIQUE (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `member_role` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'role 번호',
    `member_id` BIGINT(20) UNSIGNED NOT NULL,
    `role` ENUM('ADMIN_MASTER', 'ADMIN_MANAGER', 'ADMIN_NORMAL', 'SELLER_CORPORATION', 'SELLER_PERSONAL', 'SELLER_NORMAL',
        'USER_SPECIAL', 'USER_CERTIFIED', 'USER_NORMAL') NOT NULL COMMENT '역할',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
    UNIQUE KEY `uk_member_role` (`member_id`, `role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `member_address` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT(20) UNSIGNED NOT NULL,
    `zipcode` VARCHAR(10) NOT NULL,
    `street` VARCHAR(225) NOT NULL,
    `detail` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS banner (
    id BIGINT(20) UNSIGNED not null auto_increment comment '배너 번호',
    title VARCHAR(50) not null COMMENT '배너 제목',
    url VARCHAR(255) COMMENT '배너 이동 url',
    exposure TINYINT(1) not null DEFAULT 0 COMMENT '노출여부',
    created_at DATETIME not null comment '생성 날짜',
    updated_at DATETIME comment '수정 날짜',
    deleted_at DATETIME comment '삭제 날짜',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS banner_file (
    banner_id BIGINT(20) UNSIGNED comment '배너 번호',
    name VARCHAR(255) comment '파일명',
    url VARCHAR(500) comment '파일 URL',
    path VARCHAR(255) comment '파일 경로',
    size BIGINT comment '파일 크기',
    created_at DATETIME not null comment '생성 날짜',
    updated_at DATETIME comment '수정 날짜',
    deleted_at DATETIME comment '삭제 날짜',
    primary key (banner_id),
    FOREIGN KEY (`banner_id`) REFERENCES `banner` (`id`) ON DELETE CASCADE  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS refresh_token (
    id BIGINT(20) UNSIGNED AUTO_INCREMENT comment '토큰 번호',
    member_id BIGINT(20) UNSIGNED NOT NULL UNIQUE comment '회원 번호',
    refresh_token VARCHAR(255) NOT NULL comment '토큰',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS category (
    id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL COMMENT '카테고리 이름',
    read_auth VARCHAR(255) COMMENT '읽기권한',
    cud_auth VARCHAR(255) COMMENT '작성권한',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sub_category (
    id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    category_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50) NOT NULL COMMENT '하위 카테고리 이름',
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS board (
    id BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '게시글 번호',
    writer VARCHAR(50) NOT NULL COMMENT '작성자 닉네임',
    writer_image_url VARCHAR(255) COMMENT '작성자 프로필 이미지',
    category_id BIGINT UNSIGNED NOT NULL COMMENT '카테고리',
    sub_category_id BIGINT UNSIGNED NOT NULL COMMENT '하위 카테고리',
    pin_to_top TINYINT(1) DEFAULT 0 NOT NULL COMMENT '상단 고정',
    title VARCHAR(100) NOT NULL COMMENT '제목',
    content TEXT NOT NULL COMMENT '내용',
    like_count INT DEFAULT 0 COMMENT '추천수',
    view_count INT DEFAULT 0 COMMENT '조회수',
    created_at DATETIME NOT NULL COMMENT '생성일',
    updated_at DATETIME COMMENT '수정일',
    deleted_at DATETIME COMMENT '삭제일',
    member_id BIGINT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sub_category_id) REFERENCES sub_category(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
