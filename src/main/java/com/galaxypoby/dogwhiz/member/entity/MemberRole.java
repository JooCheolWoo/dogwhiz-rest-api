package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "member_role", uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "role"}))
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("role 번호")
    @Column(name = "id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Comment("역할")
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String role;
}
