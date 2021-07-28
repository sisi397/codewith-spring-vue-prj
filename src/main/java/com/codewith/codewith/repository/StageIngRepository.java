package com.codewith.codewith.repository;

import com.codewith.codewith.model.Scrap;
import com.codewith.codewith.model.StageFinish;
import com.codewith.codewith.model.StageIng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageIngRepository extends JpaRepository<StageIng, Long> {
    Optional<List<StageIng>> findAllByUserId(String userId);
    Optional<StageIng> findByUserIdAndCourseAndStage(String userId, int course, int stage);
}


