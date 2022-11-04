package com.example.backend.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("대댓글 작성 통합 테스트")
class ReCommentWriteIntegrationTest extends IntegrationTestContainer {

    @Test
    @DisplayName("글쓴이와 스터디, 댓글이 존재하고 올바른 내용이 입력됐다면 대댓글이 작성된다.")
    void 대댓글_작성_통합_테스트() {
        Member member = memberCommandService.save(MemberTestData.createMemberTestData());
        Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
        CommentContent content = CommentContent.from("좋은 내용 공유해주셔서 감사합니다.");
        Comment newComemnt = commentCommandFacade.writeComment(MemberId.from(member.getMemberId()), StudyId.from(createdStudy.getStudyId()), content);

        Comment reComment = commentCommandFacade.writeReComment(
                MemberId.from(member.getMemberId()),
                CommentParentId.from(newComemnt.getCommentId()),
                CommentContent.from("감사합니다.")
        );

        assertEquals("감사합니다.", reComment.getContent());
    }
}
