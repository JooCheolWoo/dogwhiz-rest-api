package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BoardCode {

    @AllArgsConstructor
    public enum Category {
        NOTICE("공지사항"),
        FAQ("FAQ"),
        COMMUNITY("커뮤니티"),
        WALK("산책친구"),
        DICTIONARY("개과사전"),
        TRAVEL("동반여행"),
        HOSPITAL("병원찾기");

        @Getter
        private String value;
    }

    @AllArgsConstructor
    public enum SubCategory {
        /**
         * 커뮤니티
         */
        DAILY("일상"),
        FUNNY("재미"),
        QUESTION("질문"),
        INFO("정보"),

        /**
         * 산책친구
         */
        RECODE("산책기록"),
        SHARE("경로공유"),
        FRIEND("친구찾기"),

        /**
         * 개과사전
         */
        KNOWLEDGE("상식"),
        HEALTH("건강"),
        BREED("견종백과"),

        /**
         * 동반여행
         */
        CAFE("카페"),
        RESTAURANT("음식점"),
        PLAYGROUND("운동장"),
        TRAVEL_COURSE("여행코스");

        @Getter
        private String value;
    }
}
