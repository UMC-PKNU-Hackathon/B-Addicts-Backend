package com.example.hackathon.controller;

import com.example.hackathon.dto.request.CreateChatRoomDto;
import com.example.hackathon.entity.User;
import com.example.hackathon.service.ChatService;
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
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("create")
    public ResponseEntity<HttpStatus> createChatRoom(@AuthenticationPrincipal User user, @RequestBody CreateChatRoomDto createChatRoomDto) {
        chatService.create(user, createChatRoomDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
