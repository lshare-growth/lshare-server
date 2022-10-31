package com.example.backend.business.core.comment.infrastructure.command;

import com.example.backend.business.core.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentQueryDslCommandRepository {

    private final EntityManager entityManager;

    public CommentQueryDslCommandRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Comment save(Comment newComment) {
        entityManager.persist(newComment);
        return newComment;
    }
}
