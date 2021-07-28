package com.codewith.codewith.repository;

import com.codewith.codewith.model.Member;
import com.codewith.codewith.model.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<List<Scrap>> findAllByUserId(String userId);
    Optional<Scrap> findByUserIdAndCourseAndStage(String userId, int course, int stage);
}
