package com.codewith.codewith.repository;

import com.codewith.codewith.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPassword(String password);
    Optional<Member> findByUserId(String userId);
}
