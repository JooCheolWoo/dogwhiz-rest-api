package com.galaxypoby.dogwhiz.login.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class RequestLoginDto {
        @Email(message = "이메일 형식에 맞지 않습니다.")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
}
