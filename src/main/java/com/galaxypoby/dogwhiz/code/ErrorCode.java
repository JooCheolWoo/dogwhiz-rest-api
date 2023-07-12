package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    OK(0, "성공"),

    /**
     * 회원 (1000)
     */
    MEMBER_NOT_EXIST(1000, "존재하지 않는 회원입니다."),
    MEMBER_EMAIL_DUPLICATION(1001, "중복된 이메일이 존재합니다."),
    MEMBER_NICKNAME_DUPLICATION(1002, "중복된 닉네임이 존재합니다."),
    MEMBER_PASSWORD_NOT_MATCH(1003, "비밀번호가 일치하지 않습니다."),
    MEMBER_ABNORMAL_REFRESH(1004, "잘못된 재인증 요청입니다."),
    MEMBER_STATUS_PENDING(1005,"승인 대기중인 회원입니다."),
    MEMBER_STATUS_SUSPENDED(1006, "이용이 정지된 회원입니다."),
    MEMBER_STATUS_DEACTIVATED(1007, "탈퇴한 회원입니다."),

    /**
     * 존재하는 상태코드 (400)
     */
    TOKEN_NOT_EXIST(400, "JWT 토큰이 존재하지 않습니다."),
    MEMBER_FAIL_AUTHENTICATION(401, "권한 인증에 실패하였습니다."),
    MEMBER_DENY_ACCESS(403, "접근 권한이 없습니다."),
    API_NOT_FOUND(404, "해당 API를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "허용되지 않는 API 호출입니다."),

    /**
     * 파일(2000)
     */
    FILE_NOT_REGISTERED(2000, "파일이 등록되지 않았습니다."),
    FILE_NOT_IMAGE(2001, "이미지 파일만 등록이 가능합니다."),
    FILE_OVER_SIZE(2002, "업로드 허용 용량을 초과합니다."),

    /**
     * 공통(3000)
     */
    FAIL_SEND_EMAIL(3000, "메일 전송에 실패하였습니다."),
    FAIL_VERIFY_EMAIL(3001, "잘못된 인증키 또는 이메일입니다.");

    @Getter
    private int status;
    @Getter
    private String message;
}
