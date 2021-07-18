package com.codewith.codewith.repository;

import com.codewith.codewith.model.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCodeRepository extends JpaRepository<UserCode, Long> {
}
