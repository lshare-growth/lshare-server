package com.example.backend.lshare.business.integration.comment;//package com.example.lshare.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 삭제 통합 테스트")
public class CommentDeleteIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private StudyCommandService studyCommandService;

    @Nested
    class NestedTest {

        @Test
        @DisplayName("글쓴이와 스터디, 댓글이 존재한다면 작성된 댓글이 삭제된다.")
        void 댓글_작성_성공_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment newComemnt = getWriteComment(member, createdStudy);

            // when
            commentCommandFacade.deleteCommentById(
                    MemberId.from(member.getMemberId()),
                    CommentId.from(newComemnt.getCommentId())
            );

            // then
            assertEquals(Deleted.TRUE, newComemnt.getDeleted());

        }

        @Test
        @DisplayName("이미 삭제된 댓글을 삭제하려하면 BusinessException이 발생한다.")
        void 댓글_삭제_실패_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment newComemnt = getWriteComment(member, createdStudy);
            commentCommandFacade.deleteCommentById(
                    MemberId.from(member.getMemberId()),
                    CommentId.from(newComemnt.getCommentId())
            );

            // when, then
            assertThrows(BusinessException.class, () -> commentCommandFacade.deleteCommentById(
                            MemberId.from(member.getMemberId()),
                            CommentId.from(newComemnt.getCommentId())
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
}
