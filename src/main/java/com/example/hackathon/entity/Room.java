package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Room {

    @GeneratedValue
    @Id
    @Column(name = "room_id")
    private Long id;

    private String chatRoomName; // 채팅방 이름 - 필수

    private String image;// 방 사진

    private Timestamp deadline; // 인원 모집 마감 기간 - 필수

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 방장

    public Room(User user, String chatRoomName, Timestamp deadline) {
        this.user = user;
        this.chatRoomName = chatRoomName;
        this.deadline = deadline;
    }
}