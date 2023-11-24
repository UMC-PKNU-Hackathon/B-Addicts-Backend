package com.example.hackathon.controller;

import com.example.hackathon.dto.request.BoardRequestDto;
import com.example.hackathon.dto.request.CommentRequestDto;
import com.example.hackathon.dto.response.GetBoardDto;
import com.example.hackathon.dto.response.GetBoardListDto;
import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.User;
import com.example.hackathon.service.BoardService;
import com.example.hackathon.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;


    /**게시글 작성*/
    @PostMapping("/boards")
    public ResponseEntity<HttpStatus> createBoard(@AuthenticationPrincipal User user, @RequestBody BoardRequestDto boardRequestDto) {

        boardService.createBoard(user, boardRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**전체 조회*/
    @GetMapping("/boards")
    public ResponseEntity<List<GetBoardListDto>> getAllBoards() {

        List<GetBoardListDto> getBoardListDtoList = boardService.getAllBoards();

        return new ResponseEntity<>(getBoardListDtoList, HttpStatus.OK);
    }

    /**게시글 조회*/
    @GetMapping("boards/{id}")
    public ResponseEntity<GetBoardDto> getBoard(@PathVariable("id") Long id) {
        GetBoardDto dto = boardService.getBoard(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    /**게시글 수정*/
    @PutMapping("boards/{id}")
    public ResponseEntity<GetBoardDto> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDto boardRequestDto) {
        GetBoardDto dto = boardService.updateBoard(id, boardRequestDto.getTitle(), boardRequestDto.getContent());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**게시글 삭제*/
    @DeleteMapping("boards/{id}")
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}