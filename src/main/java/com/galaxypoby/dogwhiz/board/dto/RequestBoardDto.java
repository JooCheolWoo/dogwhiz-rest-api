package com.galaxypoby.dogwhiz.board.dto;

import lombok.Getter;

public class RequestBoardDto {

    @Getter
    public static class BoardDto {
        private String category;
        private String subCategory;
        private boolean pinToTop;
        private String title;
        private String content;
    }
}
