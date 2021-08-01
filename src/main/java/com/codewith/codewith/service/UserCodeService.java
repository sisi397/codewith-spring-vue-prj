package com.codewith.codewith.service;

import com.codewith.codewith.model.UserCode;
import com.codewith.codewith.repository.UserCodeRepository;
import com.codewith.codewith.dto.UserCodeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserCodeService {
    private final UserCodeRepository userCodeRepository;

    @Transactional
    public Long update(Long index, UserCodeRequestDto requestDto) {
        UserCode userCode1 = userCodeRepository.findById(index).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        userCode1.update(requestDto);
        return userCode1.getId();
    }
}