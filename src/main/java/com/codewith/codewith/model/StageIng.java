package com.codewith.codewith.model;

import com.codewith.codewith.dto.StageIngRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StageIng {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private int course;

    @Column(nullable = false)
    private int stage;

    public StageIng(Long user_id, int course, int stage) {
        this.user_id = user_id;
        this.course = course;
        this.stage = stage;
    }

    public StageIng(StageIngRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }

    public void update(StageIngRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
    }
}
