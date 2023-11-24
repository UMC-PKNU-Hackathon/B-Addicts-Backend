package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEW_BOARD")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public class ReviewBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_board_id")
    private Long reviewBoardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(name = "started_at")
    private String startedAt;

    @Column(name = "ended_at")
    private String endedAt;

    @CreatedDate //엔티티가 생성되어 저장될 때 시간을 자동으로 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String before;

    private String after;

    private String keyword;

    @Column(columnDefinition = "INT default 0")
    private int good;

    @Builder
    public ReviewBoard(User user, String title, String startedAt, String endedAt, String before, String after, String keyword) {
        this.user = user;
        this.title = title;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.before = before;
        this.after = after;
        this.keyword = keyword;
    }
}
