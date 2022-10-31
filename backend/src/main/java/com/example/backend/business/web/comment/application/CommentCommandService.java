package com.example.backend.business.web.comment.application;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.comment.infrastructure.command.CommentQueryDslCommandRepository;
import com.example.backend.business.core.comment.infrastructure.query.CommentQueryDslQueryRepository;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.comment.CommentNotFoundException.COMMENT_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.comment.CommentNotFoundException.COMMENT_PARENT_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class CommentCommandService {

    private final CommentQueryDslQueryRepository commentCommandRepository;
    private final CommentQueryDslCommandRepository commentQueryDslCommandRepository;

    @Transactional
    public Comment writeComment(Member member,
                                Study study,
                                CommentContent content) {

        Comment newComment = Comment.writeComment(member, study, content);
        commentQueryDslCommandRepository.save(newComment);
        study.increaseCommentCount();

        return newComment;
    }

    @Transactional
    public Comment writeReComment(Member writer,
                                  CommentParentId commentParentId,
                                  CommentContent content) {

        Comment findCommentParent = commentCommandRepository.findCommentByParentId(commentParentId).orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));
        Study findStudy = findCommentParent.getStudy();

        Comment newComment = Comment.writeReComment(writer, findStudy, content, commentParentId);
        findCommentParent.increaseReCommentCount();
        findStudy.addComment(newComment);

        return newComment;
    }

    @Transactional
    public void updateComment(MemberId memberId,
                              CommentId commentId,
                              CommentContent content) {

        Comment findComment = commentCommandRepository.findCommentById(commentId).orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));

        if (findComment.hasParentId()) {
            findComment.updateContent(memberId, content);
            commentCommandRepository.exist(CommentParentId.from(findComment.getCommentParentId()));
            return;
        }

        findComment.updateContent(memberId, content);
    }

    @Transactional
    public void updateReComment(MemberId memberId,
                                CommentId commentId,
                                CommentContent content) {

        Comment findReComment = commentCommandRepository.findCommentById(commentId).orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));

        findReComment.updateContent(memberId, content);
    }

    @Transactional
    public void deleteComment(MemberId memberId, CommentId commentId) {
        Comment findComment = commentCommandRepository.findCommentById(commentId).orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));
        Study findStudy = findComment.getStudy();

        findComment.delete(memberId);
        findStudy.decreaseCommentCount();
    }

    @Transactional
    public void deleteReComment(MemberId memberId,
                                CommentParentId commentParentId,
                                CommentId commentId) {

        Comment findComment = commentCommandRepository.findCommentByParentId(commentParentId).orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));
        Comment findRecomment = commentCommandRepository.findCommentById(commentId).orElseThrow(()->new BusinessException(COMMENT_PARENT_NOT_FOUND_EXCEPTION));
        Study findStudy = findRecomment.getStudy();

        findRecomment.delete(memberId);
        findComment.decreaseReCommentCount();
        findStudy.decreaseCommentCount();
    }
}
