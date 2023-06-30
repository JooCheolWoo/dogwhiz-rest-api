CREATE TABLE IF NOT EXISTS `member` (
    `member_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원번호',
    `email` VARCHAR(100) NOT NULL COMMENT '이메일',
    `nickname` VARCHAR(50) NOT NULL COMMENT '닉네임',
    `password` VARCHAR(100) NOT NULL COMMENT '비밀번호',
    `auth_cd` VARCHAR(10) NOT NULL COMMENT '권한코드',
    `status_cd` VARCHAR(10) NOT NULL COMMENT '상태코드',
    `login_ip` VARCHAR(45) COMMENT '로그인 IP',
    `last_login_date` DATETIME COMMENT '마지막 로그인 날짜',
    `update_pwd_date` DATETIME NOT NULL COMMENT '마지막 비밀번호 수정 날짜',
    `email_auth` TINYINT(1) DEFAULT 0 NOT NULL COMMENT '이메일 인증 여부',
    `email_key` VARCHAR(100) COMMENT '이메일 인증키',
    `created_at` DATETIME COMMENT '가입날짜',
    `updated_at` DATETIME COMMENT '수정날짜',
    `deleted_at` DATETIME COMMENT '탈퇴날짜',
    PRIMARY KEY (`member_id`),
    UNIQUE (`email`),
    UNIQUE (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `member_address` (
    `address_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT(20) UNSIGNED NOT NULL,
    `zipcode` VARCHAR(10) NOT NULL,
    `street` VARCHAR(225) NOT NULL,
    `address_detail` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`address_id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `member_detail` (
    `member_id` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `gender` TINYINT(1) NOT NULL,
    `birth` DATE NOT NULL,
    `phone` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`member_id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `member_image` (
    `image_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT(20) UNSIGNED NOT NULL,
    `file_name` VARCHAR(255) NOT NULL,
    `file_url` VARCHAR(255) NOT NULL,
    `file_path` VARCHAR(255) NOT NULL,
    `file_size` INT NOT NULL,
    `created_at` DATETIME,
    `updated_at` DATETIME,
    `deleted_at` DATETIME,
    PRIMARY KEY (`image_id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS banner (
    banner_id BIGINT(20) UNSIGNED not null auto_increment comment '배너 번호',
    banner_title VARCHAR(50) not null COMMENT '배너 제목',
    banner_url VARCHAR(255) COMMENT '배너 이동 url',
    exposure TINYINT(1) not null DEFAULT 0 COMMENT '노출여부',
    created_at DATETIME not null comment '생성 날짜',
    updated_at DATETIME comment '수정 날짜',
    deleted_at DATETIME comment '삭제 날짜',
    PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS banner_file (
    banner_file_id BIGINT(20) UNSIGNED not null auto_increment comment '파일 번호',
    banner_id BIGINT(20) UNSIGNED comment '배너 번호',
    file_name VARCHAR(255) comment '파일명',
    file_url VARCHAR(500) comment '파일 URL',
    file_path VARCHAR(255) comment '파일 경로',
    file_size BIGINT comment '파일 크기',
    created_at DATETIME not null comment '생성 날짜',
    updated_at DATETIME comment '수정 날짜',
    deleted_at DATETIME comment '삭제 날짜',
    primary key (banner_file_id),
    FOREIGN KEY (`banner_id`) REFERENCES `banner` (`banner_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;