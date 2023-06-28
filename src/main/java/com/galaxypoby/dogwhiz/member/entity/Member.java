package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galaxypoby.dogwhiz.code.AuthCode;
import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import com.galaxypoby.dogwhiz.util.IpAnalyzer;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원번호")
    @Column(name = "member_id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
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

    @Comment("권한구분코드")
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String authCd;

    @Comment("상태구분코드")
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String statusCd;

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

    @OneToOne(mappedBy = "member" , cascade = CascadeType.ALL)
    private MemberImage memberImage;

    @OneToOne(mappedBy = "member" , cascade = CascadeType.ALL)
    private MemberDetail memberDetail;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAddress> addresses = new ArrayList<>();

    public void setUpUser() {
        this.authCd = AuthCode.USER_NORMAL.getCode();
        this.statusCd = StatusCode.USER_WAIT.getCode();
    }

    public void setEncodedPwd(String encodedPwd) {
        this.password = encodedPwd;
        this.updatePwdDate = LocalDateTime.now();
    }

    public void login() {
        this.loginIp = IpAnalyzer.getUserIp();
        this.lastLoginDate = LocalDateTime.now();
    }

    public void editInfo(RequestMemberDto.EditMemberDto request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.nickname = request.getNickname();
    }
}
