package com.galaxypoby.dogwhiz.login.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galaxypoby.dogwhiz.member.entity.MemberImage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseLoginDto {
    private Long id;
    private String email;
    private String nickname;
    private String loginIp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastLoginDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatePwdDate;
    private boolean emailAuth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedAt;

    private MemberImage memberImage;
    private String accessToken;

    public void setAccessToken(String token) {
        this.accessToken = token;
    }
}
