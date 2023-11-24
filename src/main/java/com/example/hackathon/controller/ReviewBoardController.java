package com.example.hackathon.controller;

import com.example.hackathon.dto.request.CreateReviewBoardDto;
import com.example.hackathon.dto.response.GetReviewBoardDto;
import com.example.hackathon.dto.response.GetReviewBoardList;
import com.example.hackathon.entity.User;
import com.example.hackathon.service.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review-boards")
public class ReviewBoardController {

    private final ReviewBoardService reviewBoardService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> createReviewBoard(@AuthenticationPrincipal User user, @RequestBody CreateReviewBoardDto createReviewBoardDto) {

        reviewBoardService.create(user, createReviewBoardDto);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<GetReviewBoardDto> getReviewBoard(@PathVariable("reviewId") Long id) {

        GetReviewBoardDto dto = reviewBoardService.getReviewBoard(id);

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //유저이름
    //제목
    //조회수
    //댓글수
    //키워드
    //작성일자
    @GetMapping("")
    public ResponseEntity<List<GetReviewBoardList>> getReviewBoardList() {

        List<GetReviewBoardList> getReviewBoardLists = reviewBoardService.getReviewBoardList();

        return new ResponseEntity<>(getReviewBoardLists, HttpStatus.OK);
    }
}
