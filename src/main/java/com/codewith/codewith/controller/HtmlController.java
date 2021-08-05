package com.codewith.codewith.controller;

import com.codewith.codewith.model.*;
import com.codewith.codewith.dto.HtmlRequestDto;
import com.codewith.codewith.repository.HtmlCodeRepository;
import com.codewith.codewith.service.HtmlCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class HtmlController {
    private final HtmlCodeRepository htmlcodeRepository;
    private final HtmlCodeService htmlCodeService;

    @GetMapping("/api/html/{stage}")
    public HtmlCode getHtmlCode(@PathVariable int stage){
        return htmlcodeRepository.findByStage(stage).orElseThrow(
                () -> new IllegalArgumentException("페이지가 존재하지 않습니다.")
        );
    }

    //POST (INSERT)
    @PostMapping("/api/html")
    public HtmlCode createHtmlCode(@RequestBody HtmlRequestDto requestDto) {

        HtmlCode HtmlCode = new HtmlCode(requestDto);
        return htmlcodeRepository.save(HtmlCode);
    }
}
