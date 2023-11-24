package com.example.hackathon.dto.request;

import lombok.Getter;

@Getter
public class CreateReviewBoardDto {

    private String title;
    private String startedAt;
    private String endedAt;
    private String before;
    private String after;
    private String keyword;
}
