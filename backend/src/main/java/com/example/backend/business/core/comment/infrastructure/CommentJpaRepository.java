package com.example.backend.business.core.comment.infrastructure;

import com.example.backend.business.core.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
