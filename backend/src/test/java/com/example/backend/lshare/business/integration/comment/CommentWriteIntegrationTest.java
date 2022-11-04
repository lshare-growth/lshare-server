package com.example.backend.lshare.business.integration.comment;//package com.example.lshare.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.application.CommentCommandService;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 작성 통합 테스트")
public class CommentWriteIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private StudyCommandService studyCommandService;

    @Autowired
    private CommentCommandService commentCommandService;

    @Nested
    @DisplayName("댓글을 작성할때")
    class NestedTest {

        @Test
        @DisplayName("글쓴이와 스터디가 존재하고 올바른 내용이 입력됐다면 댓글이 작성된다.")
        void 댓글작성_성공_통합_테스트() {
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
            CommentContent content = CommentContent.from("좋은 내용 공유해주셔서 감사합니다.");

            Comment newComemnt = commentCommandFacade.writeComment(MemberId.from(member.getMemberId()), StudyId.from(createdStudy.getStudyId()), content);

            assertAll(
                    () -> assertNotNull(newComemnt.getCommentId()),
                    () -> assertEquals(content.getContent(), newComemnt.getContent())
            );
        }

        @Test
        @DisplayName("스터디가 존재하지 않는다면 BusinessException이 발생한다.")
        void 댓글작성_실패_통합_테스트() {
            MemberId memberId = MemberId.from(99999999L);
            StudyId studyId = StudyId.from(99999999L);
            CommentContent content = CommentContent.from("좋은 내용 공유해주셔서 감사합니다.");

            assertThrows(BusinessException.class, () -> commentCommandFacade.writeComment(memberId, studyId, content));
        }
    }
}
