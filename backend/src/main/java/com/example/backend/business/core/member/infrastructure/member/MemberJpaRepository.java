package com.example.backend.business.core.member.infrastructure.member;

import com.example.backend.business.core.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
