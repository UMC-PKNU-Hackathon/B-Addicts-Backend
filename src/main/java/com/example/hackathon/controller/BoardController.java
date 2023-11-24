package com.example.hackathon.controller;

import com.example.hackathon.dto.request.BoardRequestDto;
import com.example.hackathon.dto.request.CommentRequestDto;
import com.example.hackathon.dto.response.GetBoardDto;
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
    public ResponseEntity<GetBoardDto> getBoard(@PathVariable Long id) {
        GetBoardDto dto = boardService.getBoard(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PutMapping("boards/{id}")
    public ResponseEntity<GetBoardDto> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDto boardRequestDto) {
        GetBoardDto dto = boardService.updateBoard(id, boardRequestDto.getTitle(), boardRequestDto.getContent());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("boards/{id}")
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }












}