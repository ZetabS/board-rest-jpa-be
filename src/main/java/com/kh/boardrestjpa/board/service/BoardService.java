package com.kh.boardrestjpa.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.boardrestjpa.board.model.repository.BoardRepository;
import com.kh.boardrestjpa.board.model.vo.Board;
import com.kh.boardrestjpa.exception.NotFoundException;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board findById(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        // 게시글 조회수 증가
        board.increaseCount();

        return board;
    }

    @Transactional
    public Board create(String boardTitle, String boardContent) {
        Board board = new Board(boardTitle, boardContent);
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    @Transactional
    public void delete(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(board);
    }
}
