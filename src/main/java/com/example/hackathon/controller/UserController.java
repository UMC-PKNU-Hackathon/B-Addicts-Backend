package com.example.hackathon.controller;

import com.example.hackathon.dto.request.LoginRequestDto;
import com.example.hackathon.dto.request.SignUpRequestDto;
import com.example.hackathon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**회원가입
     * /users/signup*/
    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signup(@RequestPart("image") MultipartFile file,
            @RequestPart("info") SignUpRequestDto signupRequestDto) throws IOException {

        userService.signup(file, signupRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**로그인
     * /users/login*/
    @PostMapping("/login")
    public ResponseEntity<HttpHeaders> login(@RequestBody LoginRequestDto requestDto) {


        HttpHeaders headers = userService.login(requestDto);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
