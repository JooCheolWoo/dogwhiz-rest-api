package com.galaxypoby.dogwhiz.banner.dto;

import javax.validation.constraints.NotEmpty;

public class RequestBannerDto {
    public static class ResisterDto {
        @NotEmpty(message = "배너 제목을 입력하세요.")
        private String bannerTitle;
        private String bannerUrl;
        private boolean exposure;
    }
}
