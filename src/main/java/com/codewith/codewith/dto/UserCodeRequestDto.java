package com.codewith.codewith.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCodeRequestDto {
    private Long user_id;
    private int course;
    private int stage;
    private String code;

    public UserCodeRequestDto(Long user_id, int course, int stage, String code) {
        this.user_id = user_id;
        this.course = course;
        this.stage = stage;
        this.code = code;
    }
}