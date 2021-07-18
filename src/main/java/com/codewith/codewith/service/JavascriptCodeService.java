package com.codewith.codewith.service;

import com.codewith.codewith.repository.JavascriptCodeReopository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JavascriptCodeService {
    private final JavascriptCodeReopository javascriptCodeReopository;
}
