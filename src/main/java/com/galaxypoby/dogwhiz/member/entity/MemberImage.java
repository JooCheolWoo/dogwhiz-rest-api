package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "member_image")
public class MemberImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("파일이름")
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Comment("파일 URL")
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String url;

    @Comment("파일 경로")
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String path;

    @Comment("파일 크기")
    @Column(nullable = false, columnDefinition = "INT")
    private Long size;

    @Comment("생성일")
    @Column(nullable = true, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정일")
    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Comment("삭제일")
    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;
}
