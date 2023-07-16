package com.galaxypoby.dogwhiz.admins.banner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "banner_file")
public class BannerFile {

    @Id
    @Column(name = "banner_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    private String url;

    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    private String path;

    @Column(nullable = true, columnDefinition = "BIGINT")
    private Long size;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = true, columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BannerFile(Banner banner, MultipartFile file, Map<String, String> path) {
        this.banner = banner;
        this.name = file.getOriginalFilename();
        this.size = file.getSize();
        this.url = path.get("url");
        this.path = path.get("path");
    }
}
