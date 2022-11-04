package com.example.backend.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("대댓글 삭제 통합 테스트")
class ReCommentDeleteIntegrationTest extends IntegrationTestContainer {

    @Nested
    @DisplayName("댓글을 작성할때")
    class NestedTest {

        @Test
        @DisplayName("스터디와 댓글, 대댓글이 존재할때 대댓글을 삭제하면 대댓글이 정상적으로 삭제된다.")
        void 대댓글_삭제_성공_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment newComemnt = getWriteComment(member, createdStudy);
            Comment reComment = writeRecomment(member, newComemnt);

            // when
            commentCommandFacade.deleteReComment(
                    MemberId.from(member.getMemberId()),
                    StudyId.from(createdStudy.getStudyId()),
                    CommentParentId.from(newComemnt.getCommentId()),
                    CommentId.from(reComment.getCommentId())
            );

            // then
            assertEquals(Deleted.TRUE, reComment.getDeleted());
        }

        @Test
        @DisplayName("대댓글 작성자가 아닐때 대댓글 삭제를 시도하면 BusinessException이 발생한다.")
        void 대댓글_삭제_실패_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Member other = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment newComemnt = getWriteComment(member, createdStudy);
            Comment reComment = writeRecomment(member, newComemnt);

            // when, then
            assertThrows(BusinessException.class, () -> commentCommandFacade.deleteReComment(
                            MemberId.from(other.getMemberId()),
                            StudyId.from(createdStudy.getStudyId()),
                            CommentParentId.from(newComemnt.getCommentId()),
                            CommentId.from(reComment.getCommentId())
                    )
            );
        }

        @Test
        @DisplayName("이미 삭제된 대댓글을 다시 삭제하려하면 BusinessException이 발생한다.")
        void 대댓글_삭제_실패_통합_테스트2() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment newComemnt = getWriteComment(member, createdStudy);
            Comment reComment = writeRecomment(member, newComemnt);
            commentCommandFacade.deleteReComment(
                    MemberId.from(member.getMemberId()),
                    StudyId.from(createdStudy.getStudyId()),
                    CommentParentId.from(newComemnt.getCommentId()),
                    CommentId.from(reComment.getCommentId())
            );

            entityManager.flush();

            // when, then
            assertThrows(BusinessException.class, () -> commentCommandFacade.deleteReComment(
                            MemberId.from(member.getMemberId()),
                            StudyId.from(createdStudy.getStudyId()),
                            CommentParentId.from(newComemnt.getCommentId()),
                            CommentId.from(reComment.getCommentId())
                    )
            );
        }
    }

    private Comment getWriteComment(Member member, Study createdStudy) {
        return commentCommandFacade.writeComment(
                MemberId.from(member.getMemberId()),
                StudyId.from(createdStudy.getStudyId()),
                CommentContent.from("좋은 내용 공유해주셔서 감사합니다.")
        );
    }

    private Comment writeRecomment(Member member, Comment newComemnt) {
        return commentCommandFacade.writeReComment(
                MemberId.from(member.getMemberId()),
                CommentParentId.from(newComemnt.getCommentId()),
                CommentContent.from("감사합니다.")
        );
    }
}
