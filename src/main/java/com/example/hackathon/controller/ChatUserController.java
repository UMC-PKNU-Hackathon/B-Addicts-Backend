package com.example.hackathon.controller;

import com.example.hackathon.entity.User;
import com.example.hackathon.service.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-user")
public class ChatUserRepository {

    private final ChatUserService chatUserService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> joinChat(@AuthenticationPrincipal User user) {

        //chatUserService.joinChat(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
