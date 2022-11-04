package com.example.backend.lshare.business.integration.like;//package com.example.lshare.lshare.business.integration.like;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.business.web.like.facade.LikeCommandFacade;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@DisplayName("좋아요 추가/삭제 통합 테스트")
public class LikeIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private LikeCommandFacade likeCommandFacade;

    @Nested
    @DisplayName("좋아요를 추가/삭제 할때")
    class NestedTest {

        @Test
        @DisplayName("좋아요를 한 번도 누른적이 없다면 좋아요가 추가된다.")
        void 좋아요추가_성공_통합_테스트() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("Backend")));

            // when
            likeCommandFacade.updateLike(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyId.from(createdStudy.getStudyId())
            );

            // then
            assertEquals(1, createdStudy.getLikeCount());
        }

        @Test
        @DisplayName("좋아요를 이미 눌렀다면 좋아요가 취소된다.")
        void 좋아요취소_성공_통합_테스트() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("Backend")));
            likeCommandFacade.updateLike(MemberId.from(studyLeader.getMemberId()), StudyId.from(createdStudy.getStudyId()));

            // when
            likeCommandFacade.updateLike(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyId.from(createdStudy.getStudyId())
            );

            // then
            assertEquals(0, createdStudy.getLikeCount());
        }

        @Test
        @DisplayName("존재하지 않는 스터디에 좋아요를 업데이트 하려하면 BusinessException이 발생한다.")
        void 좋아요_추가_실패_통합_테스트() {
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());

            assertThrows(BusinessException.class, () -> likeCommandFacade.updateLike(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyId.from(99999999L))
            );
        }
    }
}
