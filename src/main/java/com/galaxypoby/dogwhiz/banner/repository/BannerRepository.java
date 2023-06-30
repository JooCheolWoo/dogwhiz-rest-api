package com.galaxypoby.dogwhiz.banner.repository;

import com.galaxypoby.dogwhiz.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
