package com.codewith.codewith.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCodeRequestDto {
    private String userId;
    private int course;
    private int stage;
    private String code;

    public UserCodeRequestDto(String userId, int course, int stage, String code) {
        this.userId = userId;
        this.course = course;
        this.stage = stage;
        this.code = code;
    }
}