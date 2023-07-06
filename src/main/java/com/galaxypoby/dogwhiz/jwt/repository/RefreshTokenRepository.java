package com.galaxypoby.dogwhiz.jwt.repository;

import com.galaxypoby.dogwhiz.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
