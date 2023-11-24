package com.example.hackathon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Table(name = "TOXIC")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Toxic {

    @EmbeddedId
    private ToxicId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String degree;

    @Builder
    public Toxic(String userId, String keyword, User user, String degree) {
        ToxicId id = ToxicId.builder()
                .userId(userId)
                .keyword(keyword)
                .build();
        this.id = id;
        this.user = user;
        this.degree = degree;
    }

}
