package com.codewith.codewith.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ScrapRequestDto {
    private Long user_id;
    private int course;
    private int stage;

    public ScrapRequestDto(Long user_id, int course, int stage) {
        this.user_id = user_id;
        this.course = course;
        this.stage = stage;
    }
}