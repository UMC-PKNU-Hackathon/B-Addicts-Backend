package com.example.hackathon.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateChatRoomDto {
    private String chatRoomName;
    private LocalDateTime deadline;
    private LocalDateTime targetPeriod;
    private String keyword;
    private String tag;
    private Long chatMembers;
}
