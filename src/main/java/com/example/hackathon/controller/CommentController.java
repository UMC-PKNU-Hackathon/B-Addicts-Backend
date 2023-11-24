package com.example.hackathon.controller;

import com.example.hackathon.dto.request.SaveCommentDto;
import com.example.hackathon.entity.User;
import com.example.hackathon.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("")
    public ResponseEntity<HttpStatus> saveComment(@AuthenticationPrincipal User user, @RequestBody SaveCommentDto saveCommentDto) {

        commentService.saveComment(user, saveCommentDto);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
