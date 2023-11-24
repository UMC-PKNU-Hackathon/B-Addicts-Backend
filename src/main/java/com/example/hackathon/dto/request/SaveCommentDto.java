package com.example.hackathon.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SaveCommentDto {
    private Long boardId;
    private String content;
}
