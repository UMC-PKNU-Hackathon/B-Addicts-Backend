package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="board_id")
    private Board board;

    @Builder
    public Comment(User user, Board board, String content, LocalDateTime createdAt){
        this.user= user;
        this.board = board;
        this.content = content;
        this.createAt = createdAt;
    }
}
