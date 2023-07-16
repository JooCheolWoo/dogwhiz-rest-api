package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galaxypoby.dogwhiz.util.IpAnalyzer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원번호")
    @Column(name = "id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @Comment("이메일")
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    @Comment("닉네임")
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String nickname;

    @Comment("비밀번호")
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String password;

    @Comment("회원상태")
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @Comment("이름")
    @Column(nullable = true, columnDefinition = "VARCHAR(20)")
    private String name;

    @Comment("성별")
    @Column(nullable = true, columnDefinition = "TINYINT(1)")
    private boolean gender;

    @Comment("생년월일")
    @Column(nullable = true, columnDefinition = "DATE")
    private LocalDate birth;

    @Comment("전화번호")
    @Column(nullable = true, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Comment("프로필 사진 url")
    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    private String imageUrl;

    @Comment("프로필 사진 경로")
    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    private String imagePath;

    @Comment("로그인 ip")
    @Column(nullable = true, columnDefinition = "VARCHAR(45)")
    private String loginIp;

    @Comment("마지막 로그인 날짜")
    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime lastLoginDate;

    @Comment("마지막 비밀번호 변경 날짜")
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime updatePwdDate;

    @Comment("이메일 인증 여부")
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT=0")
    private boolean emailAuth;

    @Comment("이메일 인증키")
    @Column(nullable = true, columnDefinition = "VARCHAR(100)")
    private String emailKey;

    @Comment("가입 날짜")
    @Column(nullable = true, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정 날짜")
    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Comment("탈퇴 날짜")
    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MemberRole> memberRoles;

    public void setEncodedPwd(String encodedPwd) {
        this.password = encodedPwd;
        this.updatePwdDate = LocalDateTime.now();
    }

    public void updateProfile(Map<String, String> path) {
        this.imageUrl = path.get("url");
        this.imagePath = path.get("path");
    }

    public void updateRole(MemberRole memberRole) {
        if (memberRoles == null) {
            memberRoles = new HashSet<>();
        }
        this.memberRoles.add(memberRole);
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void updateEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    public void updateEmailAuth(boolean emailAuth) {
        this.emailAuth = emailAuth;
    }

    public void login() {
        this.loginIp = IpAnalyzer.getUserIp();
        this.lastLoginDate = LocalDateTime.now();
    }

    public void leave() {
        this.deletedAt = LocalDateTime.now();
    }
}
