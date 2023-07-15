package com.galaxypoby.dogwhiz.board.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestBoardDto {

    @Getter
    public static class BoardDto {
        @NotNull
        private String category;
        @NotNull
        private String subCategory;
        @NotNull
        private boolean pinToTop;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Getter
    public static class BoardListRequestDto {
        private String category;
        private String subCategory;
        private String type;
        private String search;
    }
}
