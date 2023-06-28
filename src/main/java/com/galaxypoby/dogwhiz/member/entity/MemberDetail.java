package com.galaxypoby.dogwhiz.member.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "member_detail")
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id", updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Comment("성별")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean gender;

    @Comment("생년월일")
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birth;

    @Comment("전화번호")
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String phone;
}
