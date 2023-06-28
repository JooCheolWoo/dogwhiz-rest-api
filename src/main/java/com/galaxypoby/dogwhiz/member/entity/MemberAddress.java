package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "member_address")
public class MemberAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원주소 번호")
    @Column(name = "address_id", updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Comment("우편번호")
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String zipcode;

    @Comment("도로명주소")
    @Column(nullable = false, columnDefinition = "VARCHAR(225)")
    private String street;

    @Comment("상세주소")
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String addressDetail;
}
