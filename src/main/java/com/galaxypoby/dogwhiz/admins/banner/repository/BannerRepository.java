package com.galaxypoby.dogwhiz.admins.banner.repository;

import com.galaxypoby.dogwhiz.admins.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findAllByExposureIsTrue();
}
