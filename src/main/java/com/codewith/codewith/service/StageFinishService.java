package com.codewith.codewith.service;

import com.codewith.codewith.model.StageFinish;
import com.codewith.codewith.repository.StageFinishRepository;
import com.codewith.codewith.dto.StageFinishRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StageFinishService {

    private final StageFinishRepository stageFinishRepository;

    @Transactional
    public Long update(Long index, StageFinishRequestDto requestDto) {
        StageFinish stageFinish1 = stageFinishRepository.findById(index).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        stageFinish1.update(requestDto);
        return stageFinish1.getId();
    }
}