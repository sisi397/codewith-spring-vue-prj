package com.codewith.codewith.repository;

import com.codewith.codewith.model.HtmlCode;
import com.codewith.codewith.model.JavascriptCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JavascriptCodeReopository extends JpaRepository<JavascriptCode, Long> {
    Optional<JavascriptCode> findByStage(int stage);
}
