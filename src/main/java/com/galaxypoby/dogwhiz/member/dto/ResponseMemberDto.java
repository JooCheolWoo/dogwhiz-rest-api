package com.galaxypoby.dogwhiz.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galaxypoby.dogwhiz.code.AuthCode;
import com.galaxypoby.dogwhiz.code.StatusCode;
import lombok.Getter;

import java.time.LocalDateTime;

public class ResponseMemberDto {

    @Getter
    public static class MemberDto {
        private Long id;
        private String email;
        private String nickname;
        private String authCd;
        private String authCdDesc;
        private String statusCd;
        private String statusCdDesc;
        private String loginIp;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime lastLoginDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime updatePwdDate;
        private boolean emailAuth;
        private String emailKey;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime updatedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime deletedAt;

        public void setAuthCd(String code) {
            this.authCd = code;
            this.authCdDesc = AuthCode.codeToEnum(code).getValue();
        }

        public void setStatusCd(String code) {
            this.statusCd = code;
            this.statusCdDesc = StatusCode.codeToEnum(code).getValue();
        }
    }
}
