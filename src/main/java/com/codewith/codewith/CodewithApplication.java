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
//            StageFinish si = new StageFinish("id", 2, 2);
//            stageFinishRepository.save(si);
//            StageFinish si2 = new StageFinish("id", 2, 2);
//            stageFinishRepository.save(si2);
//            Scrap si1 = new Scrap("id", 2, 2);
//            scrapRepository.save(si1);
//            Scrap s4 = new Scrap("id", 2, 2);
//            scrapRepository.save(s4);
            //MemberDto memberDto = new MemberDto(1L,"id","name","tldms9700@gmail.com","pass");
//
            //Member member = memberService.joinUser(memberDto);
//            System.out.println(member);
//            System.out.println(memberService.passRand());
//
//
//            String pass = memberService.passFind(memberDto);
//
//            System.out.println(pass);
//            System.out.println(memberService.login(memberDto));
//
//            memberDto.setPassword(pass);
//            System.out.println(memberService.login(memberDto));
        };
    }
}
