package com.example.hackathon.dto.response;

import com.example.hackathon.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetBoardDto {

    private String nickname;
    private String title;
    private String content;
    private int view;
    private LocalDateTime createdAt;
}
