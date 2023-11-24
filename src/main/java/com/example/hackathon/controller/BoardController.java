package com.example.hackathon.controller;

import com.example.hackathon.dto.request.BoardRequestDto;
import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.User;
import com.example.hackathon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }



    @PostMapping("/boards")
    public ResponseEntity<HttpStatus> createBoard(
            @AuthenticationPrincipal User user,
            @RequestBody BoardRequestDto boardRequestDto) {
        boardService.createBoard(user, boardRequestDto.getTitle(), boardRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getAllBoards(
            @RequestBody BoardRequestDto boardRequestDto) {
        List<Board> boards = boardService.getAllBoards();

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("boards/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> optionalBoard = boardService.getBoardById(id);

        return optionalBoard.map(board -> new ResponseEntity<>(board, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("boards/{id}")
    public ResponseEntity<Board> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDto boardRequestDto) {
        boardService.updateBoard(id, boardRequestDto.getTitle(), boardRequestDto.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("boards/{id}")
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}