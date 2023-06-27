package com.galaxypoby.dogwhiz.member.repository;

import com.galaxypoby.dogwhiz.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Member findByNickname(String nickname);
}
