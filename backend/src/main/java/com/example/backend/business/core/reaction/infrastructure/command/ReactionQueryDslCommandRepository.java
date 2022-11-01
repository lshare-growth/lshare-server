package com.example.backend.business.core.reaction.infrastructure.command;

import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.CommentReaction;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.example.backend.business.core.reaction.entity.QCommentReaction.commentReaction;

@Repository
public class ReactionQueryDslCommandRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ReactionQueryDslCommandRepository(EntityManager entityManager, JPAQueryFactory queryFactory) {
        this.entityManager = entityManager;
        this.queryFactory = queryFactory;
    }

    public void save(CommentReaction commentReaction) {
        entityManager.persist(commentReaction);
    }

    public void delete(MemberId memberId, CommentId commentId, CommentReactionEmotion commentReactionEmotion) {
        queryFactory.delete(commentReaction)
                .where(
                        commentReaction.memberId.eq(memberId)
                                .and(commentReaction.comment.commentId.eq(commentId.getCommentId()))
                                .and(commentReaction.emotion.eq(commentReactionEmotion))
                )
                .execute();
    }
}
