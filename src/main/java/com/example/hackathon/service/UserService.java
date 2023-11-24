package com.example.hackathon.service;

import com.example.hackathon.dto.request.SignUpRequestDto;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
}
