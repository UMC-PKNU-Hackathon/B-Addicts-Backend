package com.example.hackathon.dto.response;

import com.example.hackathon.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class GetBoardDto {

    private String nickname;
    private String title;
    private String content;
    private int view;
    private LocalDateTime createdAt;

    private List<GetCommentDto> commentList = new ArrayList<>();


}
