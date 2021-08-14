package com.codewith.codewith.controller;

import com.codewith.codewith.model.*;
import com.codewith.codewith.dto.JavascriptRequestDto;
import com.codewith.codewith.repository.JavascriptCodeReopository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class JavascriptController {
    private final JavascriptCodeReopository javascriptCodeReopository;

    @GetMapping("/api/3/{stage}")
    public JavascriptCode getJavaScriptCode(@PathVariable int stage){
        return javascriptCodeReopository.findByStage(stage).orElseThrow(
                () -> new IllegalArgumentException("페이지가 존재하지 않습니다.")
        );
    }
    //POST (INSERT)
    @PostMapping("/api/javascript")
    public JavascriptCode createJavascriptCode(@RequestBody JavascriptRequestDto requestDto) {

        JavascriptCode javascriptCode = new JavascriptCode(requestDto);
        return javascriptCodeReopository.save(javascriptCode);
    }
}
