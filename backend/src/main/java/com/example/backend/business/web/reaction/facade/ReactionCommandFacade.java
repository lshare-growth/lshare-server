package com.example.backend.business.web.reaction.facade;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.ReactionClicked;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.application.CommentQueryServices;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.reaction.application.ReactionCommandService;
import com.example.backend.business.web.reaction.application.ReactionQueryService;
import com.example.backend.business.web.study.facade.StudyQueryFacade;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.comment.CommentNotFoundException.COMMENT_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.study.StudyTypeException.STUDY_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class ReactionCommandFacade {

    private final MemberQueryService memberQueryService;
    private final CommentQueryServices commentQueryServices;
    private final ReactionCommandService reactionCommandService;
    private final ReactionQueryService reactionQueryService;
    private final StudyQueryFacade studyQueryFacade;

    @Transactional
    public void updateReaction(MemberId memberId,
                               StudyId studyId,
                               CommentId commentId,
                               CommentReactionEmotion commentReactionEmotion) {

        Member findMember = memberQueryService.findMemberById(memberId).orElseThrow(()->new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Comment findComment = commentQueryServices.findCommentById(commentId).orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));
        Study findStudy = studyQueryFacade.findStudyById(studyId).orElseThrow(() -> new BusinessException(STUDY_NOT_FOUND_EXCEPTION));
        ReactionClicked reactionClicked = reactionQueryService.exist(memberId, commentId, commentReactionEmotion);

        reactionCommandService.updateReaction(findMember, findStudy, findComment, reactionClicked, commentReactionEmotion);
    }
}
