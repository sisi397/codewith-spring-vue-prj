package com.codewith.codewith.model;

import com.codewith.codewith.dto.StageFinishRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StageFinish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long index;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private int course;

    @Column(nullable = false)
    private int stage;

    public StageFinish(Long user_id, int course, int stage) {
        this.user_id = user_id;
        this.course = course;
        this.stage = stage;
    }

    public StageFinish(StageFinishRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }

    public void update(StageFinishRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }
}
