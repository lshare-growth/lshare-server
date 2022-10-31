package com.example.backend.lshare.business.domain;

import com.example.backend.business.core.comment.infrastructure.CommentJpaRepository;
import com.example.backend.business.core.member.infrastructure.member.MemberJpaRepository;
import com.example.backend.business.core.study.infrastructure.StudyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public abstract class DomainTest {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected StudyJpaRepository studyJpaRepository;

    @Autowired
    protected CommentJpaRepository commentJpaRepository;

    @Autowired
    protected MemberJpaRepository memberJpaRepository;
}
