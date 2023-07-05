package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galaxypoby.dogwhiz.banner.entity.BannerFile;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "member_image")
public class MemberImage {

    @Id
    @Column(name = "member_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
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

    public MemberImage(Member member, MultipartFile file, Map<String, String> path) {
        this.member = member;
        this.name = file.getOriginalFilename();
        this.size = file.getSize();
        this.url = path.get("url");
        this.path = path.get("path");
    }
}
