CREATE TABLE IF NOT EXISTS member (
    member_id BIGINT(20) UNSIGNED not null auto_increment comment '회원 번호',
    email VARCHAR(255) not null unique comment '이메일',
    password VARCHAR(80) not null comment '비밀번호',
    nickname VARCHAR(20) not null unique comment '닉네임',
    created_at DATETIME comment '가입 날짜',
    updated_at DATETIME comment '수정 날짜',
    deleted_at DATETIME comment '탈퇴 날짜',
    primary key (member_id)
)
COMMENT='회원 정보'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB;





