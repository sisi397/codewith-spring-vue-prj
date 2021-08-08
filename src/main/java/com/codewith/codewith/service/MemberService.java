package com.codewith.codewith.service;

import com.codewith.codewith.dto.FileDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.lang.String;

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
            return null;
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
    public boolean login(MemberDto member, HttpServletRequest request) throws UsernameNotFoundException {
        System.out.println("login시작");

        Optional<Member> userEntityWrapper = memberRepository.findByUserId(member.getUserId());
        if (userEntityWrapper.isPresent()) {
            Member userEntity = userEntityWrapper.get();

            if (userEntity != null) {
                BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

                if (pwdEncoder.matches(member.getPassword(), userEntity.getPassword())) {
                    System.out.println("good");
                    HttpSession session = request.getSession();
                    System.out.println(session.isNew());
                    session.setAttribute("userId", userEntity.getUserId());
                    session.setAttribute("name", userEntity.getName());
                    System.out.println(session.getAttribute("userId"));
                    //userInfo.setUserId(userEntity.getUserId());
                    //userInfo.setName(userEntity.getName());
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
        System.out.println("아이디찾기 시작");
         Optional<Member> userEntityWrapper = memberRepository.findByEmail(memberDto.getEmail());
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

                StringBuilder body = new StringBuilder(
                        "<div style='text-align:center'><img src='cid:codewith' style='width:500px'></div>" +
                                "<h2 style='text-align:center; color:blue;'>임시 비밀번호 생성안내</h2>" +
                                "<h3 style='text-align:center;'>임시비밀번호는 <strong>" + newPass + "</strong> 입니다.</h3>" +
                                "<h3 style='text-align:center;'> 로그인 후에는 새로운 비밀번호로 변경하셔야 합니다.</h3>" +
                                "<h3 style='text-align:center;'>감사합니다.</h3>"
                );
                System.out.println(body);
                mimeMessageHelper.setText(body.toString(), true);
                mimeMessageHelper.addInline("codewith", new FileDataSource("C:/springStudy/backend/src/frontend/src/assets/send_email.jpg"));
                javaMailSender.send(mimeMessage);
                System.out.println("메일보내기 성공");
                return true;
            }
        }
        return false;
    }

    //로그아웃
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(!session.isNew()){
            System.out.println("세션삭제");
            session.removeAttribute("userId");
            session.removeAttribute("name");
        }
    }

    //파일 불러오기
    public String fileFind(String userId){
        System.out.println("파일불러오기");
        Optional<Member> userEntityWrapper = memberRepository.findByUserId(userId);
        System.out.println(userId);
        if(userEntityWrapper.isPresent()){
            System.out.println("파일불러오기2");
            Member userEntity = userEntityWrapper.get();
            return userEntity.getFilename();
            //C:/Temp  /home/ubuntu/images
        }else{
            return null;
        }
    }

    //파일 업로드
    public void fileUpload(MultipartFile file, MemberDto memberDto) throws Exception{
        System.out.println("파일업로드 시작");
        FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
        File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());

        Optional<Member> userEntityWrapper = memberRepository.findByUserId(memberDto.getUserId());
        if(userEntityWrapper.isPresent()) {
            Member userEntity = userEntityWrapper.get();
            userEntity.updateFile(dto.getUuid() + "_" + dto.getFileName());
            memberRepository.save(userEntity);
        }

        file.transferTo(newFileName);
    }
}
