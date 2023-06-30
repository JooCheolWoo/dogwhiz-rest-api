package com.galaxypoby.dogwhiz.member.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "member_detail")
public class MemberDetail {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("이름")
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Comment("성별")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean gender;

    @Comment("생년월일")
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birth;

    @Comment("전화번호")
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String phone;

    public void setMember(Member member) {
        this.member = member;
    }

    @JsonGetter("gender")
    public String getGenderString() {
        return gender ? "여자" : "남자";
    }

    @JsonGetter("phone")
    public String getFormattedPhone() {
        String rawPhone = phone;
        if (rawPhone.length() == 10) {
            return rawPhone.replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
        } else if (rawPhone.length() == 11) {
            return rawPhone.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        }
        return rawPhone;
    }
}