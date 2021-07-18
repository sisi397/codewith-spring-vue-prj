package com.codewith.codewith.model;

import com.codewith.codewith.dto.JavascriptRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class JavascriptCode extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int stage;

    @Column
    private String imgUrl;

    public JavascriptCode(JavascriptRequestDto requestDto) {
        this.code=requestDto.getCode();
        this.content= requestDto.getContent();
        this.stage= requestDto.getStage();
        this.imgUrl=requestDto.getImgUrl();
    }
}
