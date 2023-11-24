package com.example.hackathon.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SignUpRequestDto {

    private String userId;
    private String password;
    private String nickname;
    private String introduction;

}
