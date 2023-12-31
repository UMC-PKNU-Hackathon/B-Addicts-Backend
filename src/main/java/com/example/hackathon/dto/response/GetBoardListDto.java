package com.example.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetBoardListDto {

    //필요한 값들만
    private Long boardId;
    private String title;
    private String nickname;
    private int view;
    private int commentCount;
    private String keyword;
    private LocalDateTime createdAt;
}
