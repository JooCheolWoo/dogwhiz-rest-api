package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AuthCode {

    ADMIN_MASTER("DAS001", "마스터"),
    ADMIN_MANAGER("DAS002", "매니저"),
    ADMIN_NORMAL("DAS003", "관리자"),

    USER_SPECIAL("DAS101", "특별회원"),
    USER_CERTIFIED("DAS102", "인증회원"),
    USER_NORMAL("DAS103", "일반회원"),

    SELLER_CORPORATION("DAS201", "법인사업자"),
    SELLER_PERSONAL("DAS202", "개인사업자"),
    SELLER_NORMAL("DAS203", "일반판매자");


    @Getter
    private String code;
    @Getter
    private String value;
}
