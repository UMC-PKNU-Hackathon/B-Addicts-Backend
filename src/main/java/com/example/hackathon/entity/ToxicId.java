package com.example.hackathon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ToxicId implements Serializable {

    private String userId;

    @Column(name = "keyword")
    private String keyword;

    @Builder
    public ToxicId(String userId, String keyword) {
        this.userId = userId;
        this.keyword = keyword;
    }

}
