package com.kh.boardrestjpa.board.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.boardrestjpa.board.model.vo.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
