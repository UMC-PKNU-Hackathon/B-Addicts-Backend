package com.example.hackathon.controller;

import com.example.hackathon.dto.request.LoginRequestDto;
import com.example.hackathon.dto.request.SignUpRequestDto;
import com.example.hackathon.dto.response.Address;
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
    public ResponseEntity<HttpStatus> signup(@RequestBody SignUpRequestDto signupRequestDto) throws IOException {

        userService.signup(signupRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**로그인
     * /users/login*/
    @PostMapping("/login")
    public ResponseEntity<Address> login(@RequestBody LoginRequestDto requestDto) {

        Address address = userService.login(requestDto);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<Address> saveProfileImage(@RequestPart("profile") MultipartFile file) throws IOException {

        Address address = Address.builder()
                .address(userService.saveProfileImage(file))
                .build();

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
