package com.galaxypoby.dogwhiz.banner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Table(name = "banner_file")
public class BannerFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
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
}
