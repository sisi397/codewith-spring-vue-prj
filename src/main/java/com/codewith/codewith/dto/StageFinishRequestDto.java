package com.codewith.codewith.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StageFinishRequestDto {
    private String userId;
    private int course;
    private int stage;

    public StageFinishRequestDto(String userId, int course, int stage) {
        this.userId = userId;
        this.course = course;
        this.stage = stage;
    }
}