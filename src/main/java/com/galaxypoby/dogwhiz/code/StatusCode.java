package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusCode {

    APPROVED("DSC001", "승인"),
    PENDING("DSC002", "승인대기"),
    SUSPENDED("DSC003", "정지"),
    DEACTIVATED("DSC004", "탈퇴");

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
