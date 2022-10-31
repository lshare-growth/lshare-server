package com.example.backend.lshare.business.integration.comment;//package com.example.lshare.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.application.CommentCommandService;
import com.example.backend.business.web.comment.application.CommentQueryServices;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.business.web.study.application.StudyQueryServices;
import com.example.backend.common.exception.study.StudyTypeException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 작성 통합 테스트")
public class CommentWriteIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private CommentQueryServices commentQueryServices;

    @Autowired
    private StudyQueryServices studyQueryServices;

    @Autowired
    private StudyCommandService studyCommandService;

    @Autowired
    private CommentCommandService commentCommandService;

    @Autowired
    private MemberQueryService memberQueryService;

    @Autowired
    private RedissonClient redissonClient;

    @Nested
    @DisplayName("댓글을 작성할때")
    class CommentWriteTest {

        @Test
        @DisplayName("글쓴이와 스터디가 존재하고 올바른 내용이 입력됐다면 댓글이 작성된다.")
        void comment_write_success_test() throws Exception {
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
        @DisplayName("스터디가 존재하지 않는다면 StudyNotFoundException이 발생한다.")
        void write_comment_study_not_found_case_test() throws Exception {
            MemberId memberId = MemberId.from(99999999L);
            StudyId studyId = StudyId.from(99999999L);
            CommentContent content = CommentContent.from("좋은 내용 공유해주셔서 감사합니다.");

            assertThrows(StudyTypeException.class, () -> commentCommandFacade.writeComment(memberId, studyId, content));
        }
    }
}
