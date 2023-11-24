package com.example.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetBoardListDto {

    //필요한 값들만
    private String title;
    private int view;
    private LocalDateTime createdAt;
}
