package com.galaxypoby.dogwhiz.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galaxypoby.dogwhiz.code.BoardCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

public class ResponseBoardDto {

    @Getter
    public static class BoardDto {
        private Long id;
        private Long memberId;
        private String nickname;
        private String imageUrl;
        private String categoryDesc;
        private String subCategoryDesc;
        private boolean pinToTop;
        private String title;
        private String content;
        private Long likeCount;
        private Long viewCount;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime updatedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime deletedAt;

        public void setupWriter(String nickname, String imageUrl) {
            this.nickname = nickname;
            this.imageUrl = imageUrl;
        }

        public void setCategory(String category) {
            this.categoryDesc = BoardCode.Category.codeToValue(category);
        }

        public void setSubCategory(String subCategory) {
            this.subCategoryDesc =  BoardCode.SubCategory.codeToValue(subCategory);
        }
    }
}
