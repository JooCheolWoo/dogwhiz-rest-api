package com.galaxypoby.dogwhiz.admins.category.repository;

import com.galaxypoby.dogwhiz.admins.category.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findByCategory_NameAndName(String category, String subCategory);
}
