package com.codewith.codewith.repository;

import com.codewith.codewith.model.HtmlCode;
import com.codewith.codewith.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HtmlCodeRepository extends JpaRepository<HtmlCode, Long> {
    Optional<HtmlCode> findByStage(int stage);
}

