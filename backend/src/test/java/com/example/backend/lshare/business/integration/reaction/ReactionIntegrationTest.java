package com.example.backend.lshare.business.integration.reaction;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.ReactionClicked;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.web.reaction.application.ReactionCommandService;
import com.example.backend.business.web.reaction.application.ReactionQueryService;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("이모티콘 통합 테스트")
class ReactionIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private ReactionCommandService reactionCommandService;

    @Autowired
    private ReactionQueryService reactionQueryService;

    @Nested
    @DisplayName("이모티콘을 추가/삭제할때")
    class NestedTest {
        @Test
        @Order(1)
        @DisplayName("이모티콘을 추가한 적이 없다면 이모티콘이 추가된다.")
        void 이모티콘_추가_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study study = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment comment = writeComment(member, study);

            // when
            reactionCommandService.updateReaction(member, study, comment, ReactionClicked.FALSE, CommentReactionEmotion.from("❤️"));

            // then
            Assertions.assertNotNull(reactionQueryService.exist(
                    MemberId.from(member.getMemberId()),
                    CommentId.from(comment.getCommentId()),
                    CommentReactionEmotion.from("❤️")
            ));
        }

        @Test
        @Order(2)
        @DisplayName("이모티콘을 추가한 적이 았다면 이모티콘이 제거된다.")
        void 이모티콘_제거_통합_테스트() {
            // given
            Member member = memberCommandService.save(MemberTestData.createMemberTestData());
            Study study = studyCommandService.save(StudyTestData.createStudyTestData());
            Comment comment = writeComment(member, study);
            reactionCommandService.updateReaction(member, study, comment, ReactionClicked.TRUE, CommentReactionEmotion.from("❤️"));

            // when
            reactionCommandService.updateReaction(member, study, comment, ReactionClicked.TRUE, CommentReactionEmotion.from("❤️"));

            // then
            Assertions.assertEquals(ReactionClicked.FALSE,
                    reactionQueryService.exist(
                            MemberId.from(member.getMemberId()),
                            CommentId.from(comment.getCommentId()),
                            CommentReactionEmotion.from("❤️")
                    ));
        }
    }

    private Comment writeComment(Member member, Study study) {
        return commentCommandService.writeComment(
                member,
                study,
                CommentContent.from("잘부탁드립니다.")
        );
    }
}
