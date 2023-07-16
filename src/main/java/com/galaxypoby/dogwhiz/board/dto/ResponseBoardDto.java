package com.galaxypoby.dogwhiz.board.dto;

import com.galaxypoby.dogwhiz.admins.category.entity.Category;
import com.galaxypoby.dogwhiz.admins.category.entity.SubCategory;
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
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;

        public void setCategory(Category category) {
            this.category = category.getName();
        }

        public void setSubCategory(SubCategory subCategory) {
            this.subCategory = subCategory.getName();
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
        private LocalDateTime createdAt;

        public void setCategory(Category category) {
            this.category = category.getName();
        }

        public void setSubCategory(SubCategory subCategory) {
            this.subCategory = subCategory.getName();
        }
    }

    @Builder
    @Getter
    public static class BoardListResultDto {
        private Page<BoardListDto> list;
        private List<BoardListDto> fixedList;
    }
}
