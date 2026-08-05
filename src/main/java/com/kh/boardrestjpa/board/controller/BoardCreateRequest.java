package com.kh.boardrestjpa.board.controller;

public class BoardCreateRequest {
    private String boardTitle;
    private String boardContent;

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }
}
