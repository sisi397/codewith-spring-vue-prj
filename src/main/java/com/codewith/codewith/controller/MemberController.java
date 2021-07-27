package com.codewith.codewith.controller;

import com.codewith.codewith.dto.MemberDto;
import com.codewith.codewith.model.Member;
import com.codewith.codewith.model.UserInfo;
import com.codewith.codewith.repository.MemberRepository;
import com.codewith.codewith.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private MemberRepository memberRepository;

    @Resource
    private UserInfo userInfo;
    //로그인한 멤버 정보받기
//    @GetMapping("/memberInfo")
//    public Member getMemberInfo(Principal principal) {
//        System.out.println(principal);
//        Member member =memberRepository.findByUserId(principal.getName()).orElseThrow(
//                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
//        return member;
//    }

    @GetMapping("/memberInfo")
    public String get(){
        return userInfo.toString();
    }

    @GetMapping("/api/member")
    public List<Member> getMember() {
        return memberRepository.findAll();
    }


    // 메인 페이지
//    @GetMapping("/")
//    public String index() {
//        return "/index.html";
//    }
//
//    // 회원가입 페이지
//    @GetMapping("/user/signup")
//    public String dispSignup() {
//        return "redirect:/Signup";
//    }
    // 회원가입 처리
    @PostMapping("/api/signup")
    public Member createSignup(@RequestBody MemberDto memberDto) {
        return memberService.joinUser(memberDto);
    }

    @PostMapping("/api/login")
    public boolean createLogin(@RequestBody MemberDto memberDto) {
        return memberService.login(memberDto);
    }

//    @GetMapping("/loginPage")
//    public String dispLogin(){
//        return "login.html";
//    }
//    // 로그인 페이지
//    @GetMapping("/user/login")
//    public String dispLogin() {
//        return "/";
//    }
//
//    // 로그인 결과 페이지
//    @GetMapping("/user/login/result")
//    public String dispLoginResult() {
//        return "/";
//    }
//
//    // 로그아웃 결과 페이지
//    @GetMapping("/user/logout/result")
//    public String dispLogout() {
//        return "/";
//    }
//
//    // 접근 거부 페이지
//    @GetMapping("/user/denied")
//    public String dispDenied() {
//        return "/";
//    }

//    // 내 정보 페이지
//    @GetMapping("/user/info")
//    public String dispMyInfo() {
//        return "/Mypage";
//    }
}
