package com.codewith.codewith.controller;

import com.codewith.codewith.model.*;
import com.codewith.codewith.dto.CssRequestDto;
import com.codewith.codewith.repository.CssCodeRepository;
import com.codewith.codewith.service.CssCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CssController {
    private final CssCodeRepository csscodeRepository;
    private final CssCodeService cssCodeService;

    @GetMapping("/api/css/{id}")
    public CssCode getCssCode(@PathVariable Long id) {
        return csscodeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("페이지가 존재하지 않습니다.")
        );
    }

    //POST (INSERT)
    @PostMapping("/api/css")
    public CssCode createCssCode(@RequestBody CssRequestDto requestDto) {

       CssCode cssCode = new CssCode(requestDto);
        return csscodeRepository.save(cssCode);
    }
}
