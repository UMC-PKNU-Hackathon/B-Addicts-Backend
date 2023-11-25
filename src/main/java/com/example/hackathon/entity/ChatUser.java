package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "chat_user")
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatUserId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private String value;

    private String time;




}
