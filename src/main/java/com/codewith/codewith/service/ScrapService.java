package com.codewith.codewith.service;

import com.codewith.codewith.model.Scrap;
import com.codewith.codewith.repository.ScrapRepository;
import com.codewith.codewith.dto.ScrapRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;

    @Transactional
    public Long update(Long index, ScrapRequestDto requestDto) {
        Scrap scrap1 = scrapRepository.findById(index).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        scrap1.update(requestDto);
        return scrap1.getIndex();
    }
}