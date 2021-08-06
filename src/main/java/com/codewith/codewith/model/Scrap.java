package com.codewith.codewith.model;

import com.codewith.codewith.dto.ScrapRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Scrap extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private int course;

    @Column(nullable = false)
    private int stage;

    public Scrap(String userId, int course, int stage) {
        this.userId = userId;
        this.course = course;
        this.stage = stage;
    }

    public Scrap(ScrapRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }

    public void update(ScrapRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }
}
