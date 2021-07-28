package com.codewith.codewith.repository;

import com.codewith.codewith.model.Scrap;
import com.codewith.codewith.model.StageFinish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageFinishRepository  extends JpaRepository<StageFinish, Long> {
    Optional<List<StageFinish>> findAllByUserId(String userId);
    Optional<StageFinish> findByUserIdAndCourseAndStage(String userId, int course, int stage);
}
