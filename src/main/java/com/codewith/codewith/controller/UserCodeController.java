package com.codewith.codewith.controller;

import com.codewith.codewith.model.UserCode;
import com.codewith.codewith.repository.UserCodeRepository;
import com.codewith.codewith.dto.UserCodeRequestDto;
import com.codewith.codewith.service.UserCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserCodeController {

    private final UserCodeRepository userCodeRepository;
    private final UserCodeService userCodeService;

    @GetMapping("/api/userCode")
    public List<UserCode> getUserCode(Principal principal) {
        String userId = principal.getName();
        return userCodeRepository.findAllByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("userId가 존재하지 않습니다."));
    }

    //POST (INSERT)
    @PostMapping("/api/userCode")
    public UserCode createUserCode(@RequestBody UserCodeRequestDto requestDto) {
        UserCode userCode = new UserCode(requestDto);
        return userCodeRepository.save(userCode);
    }

    //PUT (UPDATE)
    @PutMapping("/api/userCode/{id}")
    public Long updateUserCode(@PathVariable Long id, @RequestBody UserCodeRequestDto requestDto) {
        return userCodeService.update(id, requestDto);
    }

    //DELETE
    @DeleteMapping("/api/userCode/{id}")
    public Long deleteUserCode(@PathVariable Long id){
        userCodeRepository.deleteById(id);
        return id;
    }
}
