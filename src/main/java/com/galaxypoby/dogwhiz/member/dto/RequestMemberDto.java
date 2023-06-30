package com.galaxypoby.dogwhiz.member.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RequestMemberDto {

    @Getter
    public static class LoginDto {
        @Email(message = "이메일 형식에 맞지 않습니다.")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Getter
    public static class SingUpDto {
        @Email(message = "이메일 형식에 맞지 않습니다.")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        @NotBlank(message = "비밀번호 확인을 입력해주세요.")
        private String rePassword;

        @NotBlank(message = "닉네임을 입력해주세요.")
        private String nickname;
    }

    @Getter
    public static class EditDto {
        private String email;
        private String password;
        private String rePassword;
        private String nickname;
    }
}
