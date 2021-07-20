package com.codewith.codewith.model;


import com.codewith.codewith.dto.CssRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity
public class CssCode extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int stage;

    @Column
    private String imgUrl;

    public CssCode(CssRequestDto requestDto) {
        this.code=requestDto.getCode();
        this.content= requestDto.getContent();
        this.stage= requestDto.getStage();
        this.imgUrl=requestDto.getImgUrl();
    }
}

