package com.codewith.codewith.service;

import com.codewith.codewith.model.StageIng;
import com.codewith.codewith.repository.StageIngRepository;
import com.codewith.codewith.dto.StageIngRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StageIngService {
    private final StageIngRepository stageIngRepository;

    @Transactional
    public Long update(Long index, StageIngRequestDto requestDto) {
        StageIng stageIng1 = stageIngRepository.findById(index).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        stageIng1.update(requestDto);
        return stageIng1.getId();
    }
}