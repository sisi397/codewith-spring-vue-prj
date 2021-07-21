package com.codewith.codewith.repository;

import com.codewith.codewith.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);
    Optional<Member> findByUserId(String userId);
}
