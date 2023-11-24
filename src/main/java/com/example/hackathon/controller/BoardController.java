package com.example.hackathon.controller;

import com.example.hackathon.dto.request.BoardRequestDto;
import com.example.hackathon.dto.request.CommentRequestDto;
import com.example.hackathon.dto.response.GetBoardListDto;
import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.User;
import com.example.hackathon.service.BoardService;
import com.example.hackathon.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService= commentService;
    }

    @PostMapping("/boards") //작성
    public ResponseEntity<HttpStatus> createBoard(
            @AuthenticationPrincipal User user,
            @RequestBody BoardRequestDto boardRequestDto) {
        boardService.createBoard(user, boardRequestDto.getTitle(), boardRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/boards") //전체 조회
    public ResponseEntity<List<GetBoardListDto>> getAllBoards() {
        List<GetBoardListDto> getBoardListDtoList = boardService.getAllBoards();
        return new ResponseEntity<>(getBoardListDtoList, HttpStatus.OK);
    }

    @GetMapping("boards/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> optionalBoard = boardService.getBoardById(id);

        return new ResponseEntity<>(HttpStatus.OK);
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


    @PostMapping("boards/{id}/comments")
    public ResponseEntity<HttpStatus> createComment(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestBody CommentRequestDto commentRequestDto){

        commentService.createComment(user, boardService.getBoardById(id).get(), commentRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }










}