package com.example.hackathon.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SignUpRequestDto {

    private String userId;
    private String password;
    private String nickname;
    private String introduction;
    private List<ToxicDto> toxics = new ArrayList<>();

}
