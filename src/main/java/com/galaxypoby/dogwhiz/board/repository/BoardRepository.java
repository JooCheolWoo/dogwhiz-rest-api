package com.galaxypoby.dogwhiz.board.repository;

import com.galaxypoby.dogwhiz.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategoryAndDeletedAtIsNull(String category);
}
