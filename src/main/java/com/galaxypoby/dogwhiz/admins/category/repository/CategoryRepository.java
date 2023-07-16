package com.galaxypoby.dogwhiz.admins.category.repository;

import com.galaxypoby.dogwhiz.admins.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
