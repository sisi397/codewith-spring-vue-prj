package com.codewith.codewith.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JavascriptRequestDto {
    private String code;
    private String content1;
    private String content2;
    private String content3;
    private int stage;
    private String imgUrl;

    public JavascriptRequestDto(String code, String content1,String content2,String content3, int stage, String imgUrl) {
        this.code = code;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.stage = stage;
        this.imgUrl= imgUrl;
    }
}
