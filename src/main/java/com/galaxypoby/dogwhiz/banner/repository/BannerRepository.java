package com.galaxypoby.dogwhiz.banner.repository;

import com.galaxypoby.dogwhiz.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findAllByExposureIsTrue();
}
