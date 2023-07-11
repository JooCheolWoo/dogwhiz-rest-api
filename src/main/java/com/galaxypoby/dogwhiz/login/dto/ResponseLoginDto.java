package com.galaxypoby.dogwhiz.login.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galaxypoby.dogwhiz.jwt.dto.ResponseTokenDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseLoginDto {
    private Long id;
    private String email;
    private String nickname;
    private String imageUrl;
    private boolean emailAuth;
    private String loginIp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastLoginDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatePwdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedAt;

    private ResponseTokenDto tokenInfo;

    public void setTokenInfo(ResponseTokenDto token) {
        this.tokenInfo = token;
    }
}
