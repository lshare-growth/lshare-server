package com.example.backend.business.core.study.infrastructure;

import com.example.backend.business.core.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyJpaRepository extends JpaRepository<Study, Long> {
}
