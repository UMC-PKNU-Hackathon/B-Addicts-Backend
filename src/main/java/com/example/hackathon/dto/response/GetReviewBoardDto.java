package com.example.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetReviewBoardDto {
    private String nickname;
    private String title;
    private String startedAt;
    private String endedAt;
    private String before;
    private String after;
    private String keyword;
    private LocalDateTime createdAt;
    private int good;
}
