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
    `role` VARCHAR(20) NOT NULL COMMENT '역할',
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_member_role_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
    UNIQUE KEY `uk_member_role` (`member_id`, `role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `member_address` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT(20) UNSIGNED NOT NULL,
    `zipcode` VARCHAR(10) NOT NULL,
    `street` VARCHAR(225) NOT NULL,
    `detail` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
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
    FOREIGN KEY (`banner_id`) REFERENCES `banner` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS refresh_token (
    id BIGINT(20) UNSIGNED AUTO_INCREMENT comment '토큰 번호',
    member_id BIGINT(20) UNSIGNED NOT NULL UNIQUE comment '회원 번호',
    refresh_token VARCHAR(255) NOT NULL comment '토큰',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;