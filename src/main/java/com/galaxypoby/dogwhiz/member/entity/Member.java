package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.code.TypeCode;
import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import com.galaxypoby.dogwhiz.util.IpAnalyzer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "members_roles",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @JsonIgnore
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemberDetail memberDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberAddress> memberAddresses;

    @JsonIgnore
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemberImage memberImage;


    public void updateRole(Role role) {
        if (this.roles == null) {
            roles = new HashSet<>();
        } else if (role.getTypeCode().getCode().startsWith("DAC00")) {
            roles.removeIf(rm -> rm.getTypeCode().getCode().startsWith("DAC00"));
        } else if (role.getTypeCode().getCode().startsWith("DAC01")) {
            roles.removeIf(rm -> rm.getTypeCode().getCode().startsWith("DAC01"));
        } else if (role.getTypeCode().getCode().startsWith("DAC10")) {
            roles.removeIf(rm -> rm.getTypeCode().getCode().startsWith("DAC10"));
        }
        roles.add(role);
    }

    public void updateMemberImage(MemberImage memberImage) {
        this.memberImage = memberImage;
    }

    public void setEncodedPwd(String encodedPwd) {
        this.password = encodedPwd;
        this.updatePwdDate = LocalDateTime.now();
    }

    public void login() {
        this.loginIp = IpAnalyzer.getUserIp();
        this.lastLoginDate = LocalDateTime.now();
    }

    public void editInfo(RequestMemberDto.EditDto request) {

        this.updatedAt = LocalDateTime.now();
    }

    public void leave() {
        for (Role role : this.roles) {

        }
        this.deletedAt = LocalDateTime.now();
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        this.roles.stream()
                .map(role -> role.getStatusCode().equals(StatusCode.APPROVED)
                ? roles.add("ROLE_" + role.getTypeCode().getCode()) : null);

        return roles;
    }

    public List<String> getPermissions() {
        List<String> permissions = new ArrayList<>();
        this.roles.stream()
                .map(role -> role.getPermissions().stream()
                        .map(permission -> permissions.add(permission.getName())));

        return permissions;
    }
}
