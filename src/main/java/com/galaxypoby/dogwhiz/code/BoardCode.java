package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BoardCode {

    @AllArgsConstructor
    public enum Category {
        NOTICE("DWB010", "공지사항"),
        FAQ("DWB020", "FAQ"),
        COMMUNITY("DWB030", "커뮤니티"),
        WALK("DWB040", "산책친구"),
        DICTIONARY("DWB050", "개과사전"),
        TRAVEL("DWB060", "동반여행"),
        HOSPITAL("DWB070", "병원찾기");

        @Getter
        private String code;
        @Getter
        private String value;
    }

    @AllArgsConstructor
    public enum SubCategory {
        /**
         * 커뮤니티
         */
        DAILY("DWB031", "일상"),
        FUNNY("DWB032", "재미"),
        QUESTION("DWB033", "질문"),
        INFO("DWB034", "정보공유"),

        /**
         * 산책친구
         */
        RECODE("DWB041", "산책기록"),
        SHARE("DWB042", "경로공유"),
        FRIEND("DWB043", "친구찾기"),

        /**
         * 개과사전
         */
        KNOWLEDGE("DWB051", "상식"),
        HEALTH("DWB052", "건강"),
        BREED("DWB053", "견종백과"),

        /**
         * 동반여행
         */
        CAFE("DWB061", "카페"),
        RESTAURANT("DWB062", "음식점"),
        PLAYGROUND("DWB063", "운동장"),
        TRAVEL_COURSE("DWB064", "여행코스");

        @Getter
        private String code;
        @Getter
        private String value;
    }
}
