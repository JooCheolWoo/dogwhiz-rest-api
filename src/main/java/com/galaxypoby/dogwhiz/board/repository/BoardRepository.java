package com.galaxypoby.dogwhiz.board.repository;

import com.galaxypoby.dogwhiz.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategoryAndSubCategoryAndPinToTopAndDeletedAtIsNullOrderByCreatedAtDesc(String category, String subCategory, boolean pinToTop);
}
