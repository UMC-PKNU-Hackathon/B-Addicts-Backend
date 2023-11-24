package com.example.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetCommentDto {
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
}
