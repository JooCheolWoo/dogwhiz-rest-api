package com.galaxypoby.dogwhiz.member.entity;

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
@Table(name = "member_auth")
public class MemberAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("권한번호")
    @Column(name = "id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @Comment("회원번호")
    @Column(name = "member_id", nullable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long memberId;

    @Comment("관리자 권한")
    @Column(name = "admin_code")
    private String adminCode;

    @Comment("관리자 권한 상태")
    @Column(name = "admin_status")
    private String adminStatus;

    @Comment("판매자 권한")
    @Column(name = "seller_code")
    private String sellerCode;

    @Comment("판매자 권한 상태")
    @Column(name = "seller_status")
    private String sellerStatus;

    @Comment("회원 권한")
    @Column(name = "user_code")
    private String userCode;

    @Comment("회원 권한 상태")
    @Column(name = "user_status")
    private String userStatus;
}
