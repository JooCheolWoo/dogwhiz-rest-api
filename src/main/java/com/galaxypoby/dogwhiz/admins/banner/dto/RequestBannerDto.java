package com.galaxypoby.dogwhiz.admins.banner.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class RequestBannerDto {

    @Getter
    public static class ResisterDto {
        @NotEmpty(message = "배너 제목을 입력하세요.")
        private String title;
        private String url;
        private boolean exposure;
    }
}
