package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusCode {

    /**
     * 관리자
     */
    ADMIN_APPROVAL("DSC001", "관리자승인"),
    ADMIN_WAIT("DSC002", "관리자승인대기"),
    ADMIN_STOP("DSC003", "관리자권한정지"),
    ADMIN_LEAVE("DSC004", "관리자탈퇴"),


    /**
     * 판매자
     */
    SELLER_APPROVAL("DSC011", "판매자승인"),
    SELLER_WAIT("DSC012", "판매자승인대기"),
    SELLER_STOP("DSC013", "판매자권한정지"),
    SELLER_LEAVE("DSC014", "판매자탈퇴"),

    /**
     * 회원
     */
    USER_APPROVAL("DSC101", "회원승인"),
    USER_WAIT("DSC102", "회원승인대기"),
    USER_STOP("DSC103", "회원권한정지"),
    USER_LEAVE("DSC104", "회원탈퇴");


    @Getter
    private String code;
    @Getter
    private String value;

    public static StatusCode codeToEnum(String code) {
        for (StatusCode value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
