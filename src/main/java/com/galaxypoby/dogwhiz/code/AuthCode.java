package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AuthCode {

    /**
     * 관리자
     */
    ADMIN_MASTER("DAC001", "슈퍼관리자"),
    ADMIN_MANAGER("DAC002", "관리자"),
    ADMIN_NORMAL("DAC003", "일반관리자"),

    /**
     * 판매자
     */
    SELLER_CORPORATE("DAC011", "법인판매자"),
    SELLER_INDIVIDUAL("DAC012", "개인판매자"),

    /**
     * 회원
     */
    USER_SPECIAL("DAC101", "특별회원"),
    USER_CERTIFIED("DAC102", "인증회원"),
    USER_NORMAL("DAC103", "일반회원");


    @Getter
    private String code;
    @Getter
    private String value;

    public static AuthCode codeToEnum(String code) {
        for (AuthCode value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
