package com.kh.boardrestjpa.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kh.boardrestjpa.board.model.vo.Board;
import com.kh.boardrestjpa.board.service.BoardService;
import com.kh.boardrestjpa.exception.BadRequestException;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public PagedModel<Board> findAll(Integer page, Integer size) {
        if (page < 1) {
            throw new BadRequestException("페이지는 1 이상이어야 합니다.");
        }

        if (size < 1) {
            throw new BadRequestException("페이지 크기는 1 이상이어야 합니다.");
        }

        if (size > 100) {
            throw new BadRequestException("페이지 크기는 100 이하여야 합니다.");
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("boardNo").descending());
        return boardService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Board findById(@PathVariable("id") Long boardNo) {
        return boardService.findById(boardNo);
    }

    @PostMapping
    public Board create(@RequestBody BoardCreateRequest body) {
        String boardTitle = body.getBoardTitle();
        String boardContent = body.getBoardContent();

        if (boardTitle == null || boardTitle.isBlank()) {
            throw new BadRequestException("게시글 제목은 비어있을 수 없습니다.");
        }

        if (boardContent == null || boardContent.isBlank()) {
            throw new BadRequestException("게시글 본문은 비어있을 수 없습니다.");
        }

        if (boardTitle.length() > 255) {
            throw new BadRequestException("게시글 제목의 길이는 255자 이하여야 합니다.");
        }

        return boardService.create(boardTitle, boardContent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long boardNo) {
        boardService.delete(boardNo);
    }
}
