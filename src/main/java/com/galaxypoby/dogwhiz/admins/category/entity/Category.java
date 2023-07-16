package com.galaxypoby.dogwhiz.admins.category.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Comment("카테고리 이름")
    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Comment("읽기권한")
    @Column(name = "read_auth", nullable = false)
    private String readAuth;

    @Comment("작성,수정,삭제 권한")
    @Column(name = "cud_auth", nullable = false)
    private String cudAuth;
}
