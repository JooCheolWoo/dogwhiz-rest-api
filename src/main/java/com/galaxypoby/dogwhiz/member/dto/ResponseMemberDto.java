package com.galaxypoby.dogwhiz.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ResponseMemberDto {

    @Getter
    @Setter
    public static class MemberDto {
        private Long id;
        private String email;
        private String nickname;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
