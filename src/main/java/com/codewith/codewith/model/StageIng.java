package com.codewith.codewith.model;

import com.codewith.codewith.dto.StageIngRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StageIng extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private int course;

    @Column(nullable = false)
    private int stage;

    public StageIng(String userId, int course, int stage) {
        this.userId = userId;
        this.course = course;
        this.stage = stage;
    }

    public StageIng(StageIngRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }

    public void update(StageIngRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }
}
