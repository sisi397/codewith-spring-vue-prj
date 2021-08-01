package com.codewith.codewith;

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

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class CodewithApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodewithApplication.class, args);
    }

    // Week02Application.java 의 main 함수 아래에 붙여주세요.
    // 실행을 위해 임시로 작성한 코드
//    @Bean
//    public CommandLineRunner demo(CourseRepository repository) {
//        return (args) -> {
            // 데이터 저장 및 불러오기
//            Course course1 = new Course("웹개발의 봄 Spring", "남병관");
//            //CourseRepository.java 에 extends JpaRepository를 했기 때문에 메서드를 선언하지 않아도 쓸수 있음.
//            repository.save(course1);
//
//            List<Course> courseList = repository.findAll();
//            for(int i = 0; i < courseList.size(); i++){
//                Course c = courseList.get(i);
//                System.out.println(c.getTitle());
//                System.out.println(c.getTutor());
//            }

            // CREATE & READ 해보기
            // 데이터 저장하기
//            repository.save(new Course("프론트엔드의 꽃, 리액트", "임민영"));
//
//            // 데이터 전부 조회하기
//            List<Course> courseList = repository.findAll();
//            for (int i=0; i<courseList.size(); i++) {
//                Course course = courseList.get(i);
//                System.out.println(course.getId());
//                System.out.println(course.getTitle());
//                System.out.println(course.getTutor());
//            }
//
//            // 데이터 하나 조회하기
//            Course course = repository.findById(1L).orElseThrow(
//                    () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//            );
//        };
//    }
//            @Bean
//            public CommandLineRunner demo(CourseRepository courseRepository, CourseService courseService) {
//                return (args) -> {
//                    courseRepository.save(new Course("프론트엔드의 꽃, 리액트", "임민영"));
//
//                    System.out.println("데이터 인쇄");
//                    List<Course> courseList = courseRepository.findAll();
//                    for (int i=0; i<courseList.size(); i++) {
//                        Course course = courseList.get(i);
//                        System.out.println(course.getId());
//                        System.out.println(course.getTitle());
//                        System.out.println(course.getTutor());
//                    }
//
//                    CourseRequestDto requestDto = new CourseRequestDto("웹개발의 봄, Spring", "임민영");
//                    courseService.update(1L, requestDto);
//                    courseList = courseRepository.findAll();
//                    for (int i=0; i<courseList.size(); i++) {
//                        Course course = courseList.get(i);
//                        System.out.println(course.getId());
//                        System.out.println(course.getTitle());
//                        System.out.println(course.getTutor());
//                    }
//
//                    courseRepository.deleteAll();
//                };
//            }
    //Get
//            @Bean
//            public CommandLineRunner demo(CourseRepository courseRepository, CourseService courseService) {
//                return (args) -> {
//                    courseRepository.save(new Course("프론트엔드의 꽃, 리액트", "임민영"));
//
//                    System.out.println("데이터 인쇄");
//                    List<Course> courseList = courseRepository.findAll();
//                    for (int i=0; i<courseList.size(); i++) {
//                        Course course = courseList.get(i);
//                        System.out.println(course.getId());
//                        System.out.println(course.getTitle());
//                        System.out.println(course.getTutor());
//                    }
//
//                    CourseRequestDto requestDto = new CourseRequestDto("웹개발의 봄, Spring", "임민영");
//                    courseService.update(1L, requestDto);
//                    courseList = courseRepository.findAll();
//                    for (int i=0; i<courseList.size(); i++) {
//                        Course course = courseList.get(i);
//                        System.out.println(course.getId());
//                        System.out.println(course.getTitle());
//                        System.out.println(course.getTutor());
//                    }
//                };
//            }
    //Stage_Ing Test
//            @Bean
//            public CommandLineRunner demo(StageIngRepository stage_IngRepository, StageIngService stage_IngService) {
//                return (args) -> {
//                    stage_IngRepository.save(new StageIng(1L, 2, 3));
//
//                    System.out.println("데이터 인쇄");
//                    List<StageIng> stage_IngList = stage_IngRepository.findAll();
//                    for (int i=0; i< stage_IngList.size(); i++) {
//                        StageIng stage_Ing = stage_IngList.get(i);
//                        System.out.println(stage_Ing.getUser_id());
//                        System.out.println(stage_Ing.getCourse());
//                        System.out.println(stage_Ing.getStage());
//                    }
//
//                };
//            }
//
//    @Bean
//    public CommandLineRunner demo(MemberRepository memberRepository, MemberService memberService, ScrapRepository scrapRepository, StageFinishRepository userCodeRepository) {
//        return (args) -> {
//            MemberDto memberDto = new MemberDto(1L,"id","nick","name","e","pass");
//            MemberDto memberDto2 = new MemberDto(2L,"id2","nick2","name2","e2","pass2");
//            Scrap scrap1 = new Scrap("id",1,1);
//            Scrap scrap2 = new Scrap("id2",2,2);
//            Scrap scrap3 = new Scrap("id2",3,1);
//            scrapRepository.save(scrap1);
//            scrapRepository.save(scrap2);
//            scrapRepository.save(scrap3);
//
//            StageFinish userCode1 = new StageFinish("id",1,1);
//            StageFinish userCode2 = new StageFinish("id2",2,1);
//            StageFinish userCode3 = new StageFinish("id2",3,1);
//
//            userCodeRepository.save(userCode1);
//            userCodeRepository.save(userCode2);
//            userCodeRepository.save(userCode3);
//
//            memberService.joinUser(memberDto);
//            memberService.joinUser(memberDto2);
//
//            List<Member> stage_IngList = memberRepository.findAll();
//            for (int i=0; i< stage_IngList.size(); i++) {
//                Member stage_Ing = stage_IngList.get(i);
//                System.out.println(stage_Ing.getUserId());
//                System.out.println(stage_Ing.getEmail());
//                System.out.println(stage_Ing.getName());
//            }
//
//        };
//    }

//    @Bean
//    public CommandLineRunner demo(MemberRepository memberRepository, MemberService memberService, ScrapRepository scrapRepository, StageFinishRepository userCodeRepository) {
//        return (args) -> {
//            MemberDto memberDto = new MemberDto(1L,"id","name","e","pass");
//            MemberDto memberDto2 = new MemberDto(2L,"id2","name2","e2","pass2");
//            Scrap scrap1 = new Scrap("id",1,1);
//            Scrap scrap2 = new Scrap("id2",2,2);
//            Scrap scrap3 = new Scrap("id2",3,1);
//            scrapRepository.save(scrap1);
//            scrapRepository.save(scrap2);
//            scrapRepository.save(scrap3);
//
//            StageFinish userCode1 = new StageFinish("id",1,1);
//            StageFinish userCode2 = new StageFinish("id2",2,1);
//            StageFinish userCode3 = new StageFinish("id2",3,1);
//
//            userCodeRepository.save(userCode1);
//            userCodeRepository.save(userCode2);
//            userCodeRepository.save(userCode3);
//
//            memberService.joinUser(memberDto);
//            memberService.joinUser(memberDto2);
//
//            List<Member> stage_IngList = memberRepository.findAll();
//            for (int i=0; i< stage_IngList.size(); i++) {
//                Member stage_Ing = stage_IngList.get(i);
//                System.out.println(stage_Ing.getUserId());
//                System.out.println(stage_Ing.getEmail());
//                System.out.println(stage_Ing.getName());
//            }
//
//        };
//    }

    //비밀번호 찾기 Test
    @Bean
    public CommandLineRunner demo(MemberRepository memberRepository, MemberService memberService, ScrapRepository scrapRepository, StageFinishRepository userCodeRepository) {
        return (args) -> {
            MemberDto memberDto = new MemberDto(1L,"id","name","tldms9700@gmail.com","pass");

            Member member = memberService.joinUser(memberDto);
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
