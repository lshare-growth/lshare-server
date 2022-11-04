package com.example.backend.business.web.reaction.application;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.CommentReaction;
import com.example.backend.business.core.reaction.entity.ReactionClicked;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.example.backend.business.core.reaction.infrastructure.command.ReactionQueryDslCommandRepository;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReactionCommandService {

    private final ReactionQueryDslCommandRepository reactionQueryDslCommandRepository;

    @Transactional
    public void updateReaction(Member member,
                               Study study,
                               Comment comment,
                               ReactionClicked reactionClicked,
                               CommentReactionEmotion commentReactionEmotion) {

        if (reactionClicked.isTrue()) {
            deleteReaction(member, comment, commentReactionEmotion);
            return;
        }
        addReaction(member, study, comment, commentReactionEmotion);
    }

    private void deleteReaction(Member member, Comment comment, CommentReactionEmotion commentReactionEmotion) {
        reactionQueryDslCommandRepository.delete(
                MemberId.from(member.getMemberId()),
                CommentId.from(comment.getCommentId()),
                commentReactionEmotion
        );
    }

    private void addReaction(Member member, Study study, Comment comment, CommentReactionEmotion commentReactionEmotion) {
        reactionQueryDslCommandRepository.save(
                CommentReaction.from(comment,
                        StudyId.from(study.getStudyId()),
                        MemberId.from(member.getMemberId()),
                        commentReactionEmotion)
        );
    }
}
