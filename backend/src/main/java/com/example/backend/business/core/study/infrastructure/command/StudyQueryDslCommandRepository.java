package com.example.backend.business.core.study.infrastructure.command;

import com.example.backend.business.core.study.entity.Study;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class StudyQueryDslCommandRepository {

    private final EntityManager entityManager;

    public StudyQueryDslCommandRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Study save(Study study) {
        entityManager.persist(study);
        return study;
    }
}
