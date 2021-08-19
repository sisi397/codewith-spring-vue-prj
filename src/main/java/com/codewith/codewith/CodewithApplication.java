package com.codewith.codewith;

import com.amazonaws.services.s3.AmazonS3Client;
import com.codewith.codewith.config.S3config;
import com.codewith.codewith.dto.MemberDto;
import com.codewith.codewith.dto.ScrapRequestDto;
import com.codewith.codewith.model.*;
import com.codewith.codewith.repository.*;
import com.codewith.codewith.service.MemberService;
import com.codewith.codewith.service.ScrapService;
import com.codewith.codewith.service.StageIngService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class CodewithApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodewithApplication.class, args);
    }

    //비밀번호 찾기 Test
    @Bean
    public CommandLineRunner demo(MemberRepository memberRepository, MemberService memberService, StageIngRepository stageIngRepository, ScrapRepository scrapRepository, StageFinishRepository stageFinishRepository) {
        return (args) -> {
        };
    }
}
