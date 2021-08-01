package com.codewith.codewith.repository;

import com.codewith.codewith.model.StageIng;
import com.codewith.codewith.model.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCodeRepository extends JpaRepository<UserCode, Long> {
    Optional<List<UserCode>> findAllByUserId(String userId);
}
