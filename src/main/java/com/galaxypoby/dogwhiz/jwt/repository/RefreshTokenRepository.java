package com.galaxypoby.dogwhiz.jwt.repository;

import com.galaxypoby.dogwhiz.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByMemberId(Long memberId);
}
