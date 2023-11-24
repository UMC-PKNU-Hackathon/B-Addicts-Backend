package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOARD")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private int view;

    @Column(name = "comment_count")
    private int commentCount;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String keyword;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Board(User user, String title, String content, LocalDateTime createdAt, String keyword){
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.keyword = keyword;
        this.view= 0;
        this.commentCount = 0;
    }

    public void addCommentCount() {
        this.commentCount++;
    }

}
