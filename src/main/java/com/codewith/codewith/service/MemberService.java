package com.codewith.codewith.service;

import com.codewith.codewith.model.Role;
import com.codewith.codewith.model.Member;
import com.codewith.codewith.model.UserInfo;
import com.codewith.codewith.repository.MemberRepository;
import com.codewith.codewith.dto.MemberDto;
import lombok.AllArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080")
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Resource
    private UserInfo userInfo;

    @Transactional
    public Member joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity());
    }

//    public boolean login(String pass){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        pass = passwordEncoder.encode(pass);
//        System.out.println(pass);
//        Optional<Member> userEntityWrapper = memberRepository.findByPassword(pass);
//        System.out.println(userEntityWrapper);
//        if(true){
//            return true;
//        }else{
//            return false;
//        }
//    }
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

    public boolean login(MemberDto member) throws UsernameNotFoundException{
        System.out.println("login시작");

        Optional<Member> userEntityWrapper = memberRepository.findByUserId(member.getUserId());
        if(userEntityWrapper.isPresent()) {
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
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        System.out.println("/login 이 호출 되면 자동 실행되어 username이 DB에 있는지 확인한다.");
//        Optional<Member> userEntityWrapper = memberRepository.findByUserId(userId);
//
//			if(userEntityWrapper == null) {
//				return null;
//			}else {
//				return new Member(userEntityWrapper.);
//			}
//    }
}
