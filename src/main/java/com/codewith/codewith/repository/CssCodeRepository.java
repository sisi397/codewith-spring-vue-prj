package com.codewith.codewith.repository;

import com.codewith.codewith.model.CssCode;
import com.codewith.codewith.model.HtmlCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CssCodeRepository extends JpaRepository<CssCode, Long> {
    Optional<CssCode> findByStage(int stage);
}