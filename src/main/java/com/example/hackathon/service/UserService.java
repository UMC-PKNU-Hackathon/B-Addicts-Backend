package com.example.hackathon.service;

import com.example.hackathon.dto.request.LoginRequestDto;
import com.example.hackathon.dto.request.SignUpRequestDto;
import com.example.hackathon.entity.User;
import com.example.hackathon.jwt.JwtProvider;
import com.example.hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final JwtProvider jwtProvider;

    public void signup(MultipartFile file, SignUpRequestDto signUpRequestDto) throws IOException {

        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        String imageAddress = s3Service.saveSignature(file);

        User user = User.builder()
                .userId(signUpRequestDto.getUserId())
                .password(password)
                .roles(roles)
                .nickname(signUpRequestDto.getNickname())
                .profileImage(imageAddress)
                .build();

        userRepository.save(user);
    }

    public HttpHeaders login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getUserId()).orElseThrow();
        String password = loginRequestDto.getPassword();

        if(!checkPassword(user, password))
            throw new RuntimeException();

        HttpHeaders headers = createHeaders(user.getUserId(), user.getRoles());

        return headers;
    }

    private boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public HttpHeaders createHeaders(String userId, List<String> roles) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + jwtProvider.createAccessToken(userId, roles)); // access token

        return headers;
    }
}
