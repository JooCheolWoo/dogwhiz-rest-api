package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    OK(0, "성공"),

    /**
     * 회원가입 (100)
     */
    MEMBER_NOT_EXIST(100, "존재하지 않는 회원입니다."),
    MEMBER_EMAIL_DUPLICATION(101, "중복된 이메일이 존재합니다."),
    MEMBER_NICKNAME_DUPLICATION(102, "중복된 닉네임이 존재합니다."),
    MEMBER_PASSWORD_NOT_MATCH(103, "비밀번호가 일치하지 않습니다."),

    /**
     * 권한설정 (200)
     */
    MEMBER_FAIL_AUTHENTICATION(200, "권한이 없습니다."),

    /**
     * 공통(500)
     */
    FILE_NOT_REGISTERED(500, "파일이 등록되지 않았습니다."),
    FILE_NOT_IMAGE(501, "이미지 파일만 등록이 가능합니다.");

    @Getter
    private int status;
    @Getter
    private String message;
}
