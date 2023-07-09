package com.galaxypoby.dogwhiz.jwt.dto;

import lombok.Getter;

@Getter
public class RequestTokenDto {
    Long memberId;
    String token;
}
