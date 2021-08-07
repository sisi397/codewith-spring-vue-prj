package com.codewith.codewith.controller;

import com.codewith.codewith.dto.FileDto;
import com.codewith.codewith.dto.MemberDto;
import com.codewith.codewith.model.Member;
import com.codewith.codewith.repository.MemberRepository;
import com.codewith.codewith.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private MemberRepository memberRepository;

    @GetMapping("/memberInfo")
    public MemberDto get(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session.isNew());
        System.out.println((String)(session.getAttribute("userId")));

        if(!(session.isNew())){
            String userId = (String)(session.getAttribute("userId"));
            String name = (String)(session.getAttribute("name"));
            //MemberDto memberDto = new MemberDto(userId,name);
            MemberDto memberDto = new MemberDto("id","코드윗");
            return memberDto;
        }else{
            MemberDto memberDto = new MemberDto("id","코드윗");
            return memberDto;
            //return null;
        }
    }

    @GetMapping("/api/member")
    public List<Member> getMember() {
        return memberRepository.findAll();
    }

    // 회원가입 처리
    @PostMapping("/api/signup")
    public Member createSignup(@RequestBody MemberDto memberDto) {
        return memberService.joinUser(memberDto);
    }

    @PostMapping("/api/login")
    public boolean createLogin(@RequestBody MemberDto memberDto, HttpServletRequest request) {
        return memberService.login(memberDto, request);
    }


    //아이디 찾기
    // 성공 시 아이디 리턴, 실패시 null 리턴
    @PostMapping("/api/idFind")
    public String findId(@RequestBody MemberDto memberDto){
        return memberService.idFind(memberDto);
    }

    //비밀번호 찾기
    // 성공시 true 리턴, 실패시 false 리턴
    @PutMapping("/api/passFind")
    public boolean updatePass(@RequestBody MemberDto memberDto) throws MessagingException {
        return memberService.passFind(memberDto);
    }

    //로그아웃
    @DeleteMapping("/api/logout")
    public void deletelogin(HttpServletRequest request){
        memberService.logout(request);
    }

    //파일 업로드
    @PostMapping("/api/fileUpload")
    public void updatefileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
        System.out.println("파일업로드 시작");
        HttpSession session = request.getSession();
        MemberDto memberDto;
        if(!(session.isNew())){
            String userId = (String)(session.getAttribute("userId"));
            String name = (String)(session.getAttribute("name"));
            //MemberDto memberDto = new MemberDto(userId,name);
            memberDto = new MemberDto("id","코드윗");
        }else{
            memberDto = new MemberDto("id","코드윗");
            //return null;
        }
        memberService.fileUpload(file, memberDto);
    }
}
