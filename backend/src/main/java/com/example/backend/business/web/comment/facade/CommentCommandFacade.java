package com.example.backend.business.web.comment.facade;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.application.CommentCommandService;
import com.example.backend.business.web.comment.application.CommentQueryServices;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.study.application.StudyQueryServices;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.exception.study.StudyTypeException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class CommentCommandFacade {

    private final CommentQueryServices commentQueryServices;
    private final CommentCommandService commentCommandService;
    private final StudyQueryServices studyQueryServices;
    private final MemberQueryService memberQueryService;
    private final RedissonClient redissonClient;

    /**
     * 개설된 스터디에 댓글을 추가하고 댓글수를 1증가시킨다.
     * <p>
     * Params
     *
     * @memberId - 회원 아이디
     * @studyId - 스터디 아이디
     * @content - 댓글 내용
     * <p>
     * Returns: 작성된 댓글
     * <p>
     * Throws: StudyNotFoundException  - 스터디를 찾을 수 없는 경우
     * MemberNotFoundException - 회원을 찾을 수 없는 경우
     * <p>
     * Note: 스터디에서 댓글수를 관리하기 때문에 저장된 댓글수와 실제 댓글의 개수가 일치하지 않을 수 있다.
     * 이런 동시성 이슈 제어를 위해 RedissonClient가 제공하는 락을 사용한다.
     * Since: 2022-10-13
     */
    @Transactional
    public Comment writeComment(MemberId memberId,
                                StudyId studyId,
                                CommentContent content) {

        RLock lock = redissonClient.getLock(studyId.toString());
        try {
            boolean available = lock.tryLock(2, 1, TimeUnit.SECONDS);
            if (!available) {
                Thread.sleep(500);
            }
            Study findStudy = studyQueryServices.findStudyById(studyId).orElseThrow(() -> new BusinessException(StudyTypeException.STUDY_NOT_FOUND_EXCEPTION));
            Member writer = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            return commentCommandService.writeComment(writer, findStudy, content);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 작성된 댓글에 대댓글을 추가하고 대댓글수를 1증가시킨다.
     * <p>
     * Params
     *
     * @memberId - 회원 아이디
     * @commentParentId - 댓글 부모 아이디
     * @content - 대댓글 내용
     * <p>
     * Returns: 작성된 대댓글
     * Throws:
     * @MemberNotFoundException - 회원을 찾을 수 없는 경우
     * @CommentParentNotFoundException - 부모 댓글을 찾을 수 없는 경우
     * <p>
     * Note: 댓글에서 대댓글수를 관리하기 때문에 저장된 대댓글수와 실제 대댓글의 개수가 일치하지 않을 수 있다.
     * 이런 동시성 이슈 제어를 위해 RedissonClient가 제공하는 락을 사용한다.
     * Since: 2022-10-13
     */
    @Transactional
    public Comment writeReComment(MemberId memberId,
                                  CommentParentId commentParentId,
                                  CommentContent content) {

        RLock lock = redissonClient.getLock(commentParentId.toString());
        try {
            boolean available = lock.tryLock(2, 1, TimeUnit.SECONDS);
            if (!available) {
                Thread.sleep(500);
            }
            Member writer = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            Comment newReComment = commentCommandService.writeReComment(writer, commentParentId, content);
            commentQueryServices.existCommentParent(commentParentId);
            return newReComment;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void updateComment(MemberId memberId,
                              CommentId commentId,
                              CommentContent content) {

        Member writer = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        commentCommandService.updateComment(writer.getMemberIdAsValue(), commentId, content);
    }

    @Transactional
    public void updateReComment(MemberId memberId,
                                StudyId studyId,
                                CommentParentId commentParentId,
                                CommentId commentId,
                                CommentContent content) {

        Member writer = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));

        commentCommandService.updateReComment(writer.getMemberIdAsValue(), commentId, content);
        existValidation(studyId, commentParentId);
    }

    @Transactional
    public void existValidation(StudyId studyId, CommentParentId commentParentId) {
        studyQueryServices.existStudy(studyId);
        commentQueryServices.existCommentParent(commentParentId);
    }

    /**
     * 개설된 스터디에서 댓글을 제거하고 댓글수를 1감소시킨다.
     * Params: memberId   - 회원 아이디
     * commentId  - 댓글 아이디
     * Throws: MemberNotFoundException  - 회원을 찾을 수 없는 경우
     * CommentNotFoundException - 댓글을 찾을 수 없는 경우
     * Note: 스터디에서 댓글수를 관리하기 때문에 저장된 댓글수와 실제 댓글의 개수가 일치하지 않을 수 있다.
     * 이런 동시성 이슈 제어를 위해 RedissonClient가 제공하는 락을 사용한다.
     * Since: 2022-10-13
     */
    @Transactional
    public void deleteCommentById(MemberId memberId, CommentId commentId) {
        RLock lock = redissonClient.getLock(commentId.toString());
        try {
            boolean available = lock.tryLock(2, 1, TimeUnit.SECONDS);

            if (!available) {
                Thread.sleep(500);
            }

            Member writer = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            commentCommandService.deleteComment(writer.getMemberIdAsValue(), commentId);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 작성된 댓글에 대댓글을 삭제하고 대댓글수를 1감소시킨다.
     * Params: memberId         - 회원 아이디
     * studyId          - 스터디 아이디
     * commentParentId  - 댓글 부모 아이디
     * commentId        - 댓글 아이디
     * Throws: MemberNotFoundException        - 회원을 찾을 수 없는 경우
     * CommentParentNotFoundException - 부모 댓글을 찾을 수 없는 경우
     * Note: 댓글에서 대댓글수를 관리하기 때문에 저장된 대댓글수와 실제 대댓글의 개수가 일치하지 않을 수 있다.
     * 이런 동시성 이슈 제어를 위해 RedissonClient가 제공하는 락을 사용한다.
     * Since: 2022-10-13
     */
    @Transactional
    public void deleteReComment(MemberId memberId,
                                StudyId studyId,
                                CommentParentId commentParentId,
                                CommentId commentId) {

        RLock lock = redissonClient.getLock(commentId.toString());
        try {
            boolean available = lock.tryLock(2, 1, TimeUnit.SECONDS);

            if (!available) {
                Thread.sleep(500);
            }

            Member writer = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            commentCommandService.deleteReComment(writer.getMemberIdAsValue(), commentParentId, commentId);
            existValidation(studyId, commentParentId);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}
