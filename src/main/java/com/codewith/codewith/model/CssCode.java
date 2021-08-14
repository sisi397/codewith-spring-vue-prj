package com.codewith.codewith.model;


import com.codewith.codewith.dto.CssRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity
public class CssCode{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column
    private String content1;
    @Column
    private String content2;
    @Column
    private String content3;

    @Column(nullable = false)
    private int stage;

    @Column
    private String imgUrl;

    public CssCode(CssRequestDto requestDto) {
        this.code=requestDto.getCode();
        this.content1= requestDto.getContent1();
        this.content2= requestDto.getContent2();
        this.content3= requestDto.getContent3();
        this.stage= requestDto.getStage();
        this.imgUrl=requestDto.getImgUrl();
    }
}

