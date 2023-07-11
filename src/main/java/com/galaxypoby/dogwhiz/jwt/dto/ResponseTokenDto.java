package com.galaxypoby.dogwhiz.jwt.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseTokenDto {
    String accessToken;
    Long expiredDate;
}
