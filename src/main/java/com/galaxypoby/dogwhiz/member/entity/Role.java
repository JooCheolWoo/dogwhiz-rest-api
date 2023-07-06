package com.galaxypoby.dogwhiz.member.entity;


import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.code.RoleCode;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleCode roleCode;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;
}
