package com.codewith.codewith.service;

import com.codewith.codewith.model.Role;
import com.codewith.codewith.model.Member;
import com.codewith.codewith.model.Scrap;
import com.codewith.codewith.model.UserInfo;
import com.codewith.codewith.repository.MemberRepository;
import com.codewith.codewith.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.String;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:8080")
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Resource
    private UserInfo userInfo;

    //회원가입
    @Transactional
    public Member joinUser(MemberDto memberDto) {

        String userId = memberDto.getUserId();

        Optional<Member> found = memberRepository.findByUserId(userId);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(@RequestBody String username) throws UsernameNotFoundException {
        System.out.println(username);
        System.out.println("/login 이 호출 되면 자동 실행되어 username이 DB에 있는지 확인한다.");
        Optional<Member> userEntityWrapper = memberRepository.findByUserId(username);
        System.out.println(userEntityWrapper);
        Member userEntity = userEntityWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        System.out.println(userEntity.getPassword() + " " + userEntity.getUserId() + " " + authorities);
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

        if(pwdEncoder.matches("pass", userEntity.getPassword()))
            System.out.println("good");
        return new User(userEntity.getUserId(), userEntity.getPassword(), authorities);

    }

    //로그인
    public boolean login(MemberDto member) throws UsernameNotFoundException {
        System.out.println("login시작");

        Optional<Member> userEntityWrapper = memberRepository.findByUserId(member.getUserId());
        if (userEntityWrapper.isPresent()) {
            Member userEntity = userEntityWrapper.get();

            if (userEntity != null) {
                BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

                if (pwdEncoder.matches(member.getPassword(), userEntity.getPassword())) {
                    System.out.println("good");
                    userInfo.setUserId(userEntity.getUserId());
                    userInfo.setName(userEntity.getName());
                    return true;
                }
            } else {
                System.out.println("fail");
            }
        }
        return false;
    }

    //비밀번호 랜덤 생성
    public String passRand(){
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int flag;
        String pass = "";
        for(int i = 0; i < 8; i++){
            flag = random.nextInt(2)+1;
            if(flag == 1){
                pass += (char)(random.nextInt(26)+65);
            }else{
                pass += (char)(random.nextInt(26)+97);
            }
        }

        return pass;
    }


    //아이디 찾기. 이메일이랑 이름 입력했을 때.
     public String idFind(MemberDto memberDto){
         Optional<Member> userEntityWrapper = memberRepository.findByUserId(memberDto.getEmail());
         if(userEntityWrapper.isPresent()){
             Member userEntity = userEntityWrapper.get();
             if(userEntity.getName().equals(memberDto.getName())){
                 return userEntity.getUserId();
             }
         }
         return null;
     }

    private final JavaMailSender javaMailSender;

    //오류나서 from안쓰고 직접 이메일 적음.
//    @Value("${spring.mail.username}")
//    private String from;

    //비밀번호 찾기. id랑 이메일을 입력했을 때.
    public boolean passFind(MemberDto memberDto) throws MessagingException {
        Optional<Member> userEntityWrapper = memberRepository.findByUserId(memberDto.getUserId());
        String newPass="";
        if(userEntityWrapper.isPresent()){
            Member userEntity = userEntityWrapper.get();
            if(userEntity.getEmail().equals(memberDto.getEmail())){
                System.out.println("비밀번호 찾기 시작");
                //아이디, 이메일 같으면 비밀번호 UPDATE
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                newPass = passRand();
                userEntity.update(passwordEncoder.encode(newPass));
                memberRepository.save(userEntity);

                // 메일보내기
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setFrom("sieun0907@naver.com");
                mimeMessageHelper.setTo(memberDto.getEmail());
                mimeMessageHelper.setSubject("[Code-with] 임시 비밀번호 안내");

                StringBuilder body = new StringBuilder("임시비밀번호는 " + newPass + " 입니다.");
                System.out.println(body);
                mimeMessageHelper.setText(body.toString(), true);
                javaMailSender.send(mimeMessage);
                System.out.println("메일보내기 성공");
                return true;
            }
        }
        return false;
    }
}
