package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChatRoom {

    @GeneratedValue
    @Id
    @Column(name = "chat_room_id")
    private Long id;

    private String chatRoomName; // 채팅방 이름 - 필수

    // 방 사진

    private LocalDateTime deadline; // 인원 모집 마감 기간 - 필수

    private LocalDateTime targetPeriod; // 끊기 목표 기간 - 필수

    private String keyword; // 방 소개 키워드

    private String tag; // 방 중독 태그 - 필수 (1개)

    private Long chatMembers; // 제한 인원

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 방장

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatUser> chatUsers = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();


    @Builder
    public ChatRoom(User user, String chatRoomName, LocalDateTime deadline, LocalDateTime targetPeriod, String keyword, String tag, Long chatMembers) {
        this.user = user;
        this.chatRoomName = chatRoomName;
        this.deadline = deadline;
        this.targetPeriod = targetPeriod;
        this.keyword = keyword;
        this.tag = tag;
        this.chatMembers = chatMembers;
    }
}