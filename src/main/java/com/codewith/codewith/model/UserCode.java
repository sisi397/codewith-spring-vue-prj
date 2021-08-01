package com.codewith.codewith.model;

import com.codewith.codewith.dto.UserCodeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserCode extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private int course;

    @Column(nullable = false)
    private int stage;

    @Column(nullable = false)
    private String code;

    public UserCode(String userId, int course, int stage, String code) {
        this.userId = userId;
        this.course = course;
        this.stage = stage;
        this.code = code;
    }

    public UserCode(UserCodeRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
        this.code = requestDto.getCode();
    }

    public void update(UserCodeRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.course = requestDto.getCourse();
        this.stage = requestDto.getStage();
        this.code = requestDto.getCode();
    }
}
