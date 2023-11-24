package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;
    private String content;
    private int view;
    private LocalDateTime createdAt;

//    @ManyToOne
//    @JoinColumn(name = "keyword")
//    private Toxic toxic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(User user, String title, String content, int view, LocalDateTime createdAt){
        this.user = user;
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
    }

}
