package com.example.backend.lshare.business.integration.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
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

@DisplayName("대댓글 수정 통합 테스트")
class ReCommentUpdateIntegrationTest extends IntegrationTestContainer {

    @Test
    @DisplayName("글쓴이와 스터디, 댓글이 존재하고 올바른 내용을 입력했다면 대댓글이 수정된다.")
    void 대댓글_수정_통합_테스트() {
        // given
        Member member = memberCommandService.save(MemberTestData.createMemberTestData());
        Study createdStudy = studyCommandService.save(StudyTestData.createStudyTestData());
        CommentContent content = CommentContent.from("좋은 내용 공유해주셔서 감사합니다.");
        Comment newComemnt = commentCommandFacade.writeComment(MemberId.from(member.getMemberId()), StudyId.from(createdStudy.getStudyId()), content);
        Comment reComment = writeRecomment(member, newComemnt);

        // when
        commentCommandFacade.updateReComment(
                MemberId.from(member.getMemberId()),
                StudyId.from(createdStudy.getStudyId()),
                CommentParentId.from(newComemnt.getCommentId()),
                CommentId.from(reComment.getCommentId()),
                CommentContent.from("고마워요.")
        );

        // then
        assertEquals("고마워요.", reComment.getContent());
    }

    private Comment writeRecomment(Member member, Comment newComemnt) {
        return commentCommandFacade.writeReComment(
                MemberId.from(member.getMemberId()),
                CommentParentId.from(newComemnt.getCommentId()),
                CommentContent.from("감사합니다.")
        );
    }
}
