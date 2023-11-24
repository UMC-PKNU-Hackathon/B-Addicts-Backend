package com.example.hackathon.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Mission {

    @Id
    @GeneratedValue
    @Column(name = "mission_id")
    private Long id;

    private String content;

    private String value;

    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
}
