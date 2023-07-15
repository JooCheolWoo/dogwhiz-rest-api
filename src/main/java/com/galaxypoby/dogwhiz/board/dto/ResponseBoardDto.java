package com.galaxypoby.dogwhiz.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galaxypoby.dogwhiz.code.BoardCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ResponseBoardDto {

    @Getter
    public static class BoardDto {
        private Long id;
        private Long memberId;
        private String writer;
        private String writerImageUrl;
        private String category;
        private String subCategory;
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

        public void setCategory(String category) {
            this.category = BoardCode.Category.codeToValue(category);
        }

        public void setSubCategory(String subCategory) {
            this.subCategory =  BoardCode.SubCategory.codeToValue(subCategory);
        }
    }

    @Getter
    public static class BoardListDto {
        private Long id;
        private Long memberId;
        private String writer;
        private String writerImageUrl;
        private String category;
        private String subCategory;
        private boolean pinToTop;
        private String title;
        private String content;
        private Long likeCount;
        private Long viewCount;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;

        public void setCategory(String category) {
            this.category = BoardCode.Category.codeToValue(category);
        }

        public void setSubCategory(String subCategory) {
            this.subCategory =  BoardCode.SubCategory.codeToValue(subCategory);
        }
    }

    @Builder
    @Getter
    public static class BoardListResultDto {
        private Page<BoardListDto> list;
        private List<BoardListDto> fixedList;
    }
}