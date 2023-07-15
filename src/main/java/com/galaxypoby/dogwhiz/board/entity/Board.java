package com.galaxypoby.dogwhiz.board.entity;

import com.galaxypoby.dogwhiz.code.BoardCode;
import com.galaxypoby.dogwhiz.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게시글 번호")
    @Column(name = "id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @Comment("작성자 닉네임")
    @Column(name = "writer", nullable = false, columnDefinition = "VARCHAR(50)")
    private String writer;

    @Comment("작성자 프로필 이미지")
    @Column(name = "writer_image_url", columnDefinition = "VARCHAR(255)")
    private String writerImageUrl;

    @Comment("게시판 카테고리")
    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String category;

    @Comment("하위 카테고리")
    @Column(nullable = true, columnDefinition = "VARCHAR(50)")
    private String subCategory;

    @Comment("상단 고정")
    @Column(name = "pin_to_top", nullable = false, columnDefinition = "TINYINT(1) DEFAULT=0")
    @Builder.Default
    private boolean pinToTop = false;

    @Comment("제목")
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;

    @Comment("내용")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Comment("추천수")
    @Column(columnDefinition = "INT DEFAULT=0")
    @Builder.Default
    private Long likeCount = 0L;

    @Comment("조회수")
    @Column(columnDefinition = "INT DEFAULT=0")
    @Builder.Default
    private Long viewCount = 0L;

    @Comment("생성일")
    @Column(nullable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정일")
    @Column(nullable = true, columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Comment("삭제일")
    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
