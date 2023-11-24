package com.example.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetReviewBoardList {

    //유저이름
    //제목
    //키워드
    //작성일자

    private String nickname;
    private String title;
    private String keyword;
    private LocalDateTime createdAt;
}
