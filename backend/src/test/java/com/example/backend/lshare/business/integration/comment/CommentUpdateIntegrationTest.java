package com.example.backend.lshare.business.integration.comment;//package com.example.lshare.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
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

@DisplayName("댓글 수정 통합 테스트")
public class CommentUpdateIntegrationTest extends IntegrationTestContainer {

    @Nested
    @DisplayName("댓글을 수정할때")
    class NestedTest {

        @Test
        @DisplayName("글쓴이와 스터디가 존재하고 올바른 내용이 입력됐다면 댓글이 수정된다.")
        void 댓글수정_성공_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment comment = commentCommandFacade.writeComment(MemberId.from(member.getMemberId()), StudyId.from(createdStudy.getStudyId()), CommentContent.from("좋은 내용이네요!"));
            CommentContent newContent = CommentContent.from("좋은 내용 공유해주셔서 감사합니다.");

            // when
            commentCommandFacade.updateComment(
                    MemberId.from(member.getMemberId()),
                    CommentId.from(comment.getCommentId()),
                    newContent
            );

            // then
            assertEquals(newContent.getContent(), comment.getContent());
        }

        @Test
        @DisplayName("존재하지 않는 회원이 댓글을 수정하는 경우 BusinessException이 발생한다.")
        void 댓글수정_실패_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment comment = commentCommandFacade.writeComment(MemberId.from(member.getMemberId()), StudyId.from(createdStudy.getStudyId()), CommentContent.from("좋은 내용이네요!"));
            CommentContent newContent = CommentContent.from("Java의 불변객체를 어떻게 잘 사용할 수 있을까요?");

            // when, then
            assertThrows(BusinessException.class, () -> commentCommandFacade.updateComment(
                    MemberId.from(999999999L),
                    CommentId.from(comment.getCommentId()),
                    newContent)
            );
        }

        @Test
        @DisplayName("존재하지 않는 댓글을 수정하려 시도하는 경우 BusinessException이 발생한다.")
        void 댓글수정_실패_통합_테스트2() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment comment = commentCommandFacade.writeComment(MemberId.from(member.getMemberId()), StudyId.from(createdStudy.getStudyId()), CommentContent.from("좋은 내용이네요!"));
            CommentContent newContent = CommentContent.from("RealMySQL 내용이 쉽지 않네요.");

            // when, then
            assertThrows(BusinessException.class, () -> commentCommandFacade.updateComment(
                    MemberId.from(member.getMemberId()),
                    CommentId.from(999999999L),
                    newContent)
            );
        }
    }
}
