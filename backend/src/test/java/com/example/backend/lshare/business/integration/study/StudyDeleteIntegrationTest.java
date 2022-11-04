package com.example.backend.lshare.business.integration.study;//package com.example.lshare.lshare.business.integration.study;

import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@DisplayName("스터디 삭제 통합 테스트")
class StudyDeleteIntegrationTest extends IntegrationTestContainer {

    @Nested
    @DisplayName("스터디를 삭제할때")
    class NestedTest {

        @Test
        @DisplayName("스터디 개설자가 자신이 개설한 스터디를 삭제하면 스터디가 삭제된다.")
        void 스터디삭제_성공_통합_테스트() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study newStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("React")));

            // when
            newStudy.delete(studyLeader);

            // then
            assertEquals(newStudy.getDeleted(), Deleted.TRUE);
        }

        @Test
        @DisplayName("권한이 없는 회원이 스터디를 삭제하려 시도하면 BusinessException이 발생한다.")
        void 스터디삭제_실패_통합_테스트() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Member otherMember = memberCommandService.save(MemberTestData.createMemberTestData());
            Study newStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("React")));

            // when, then
            assertThrows(BusinessException.class, () -> studyCommandFacade.deleteStudy(
                    MemberId.from(otherMember.getMemberId()),
                    StudyId.from(newStudy.getStudyId()))
            );
        }

        @Test
        @DisplayName("존재하지 않는 회원이 스터디를 삭제하려 시도하면 BusinessException이 발생한다.")
        void 스터디삭제_실패_통합_테스트2() {
            // given
            MemberId memberId = MemberId.from(99999999L);
            StudyId studyId = StudyId.from(99999999L);

            // when, then
            assertThrows(BusinessException.class, () -> studyCommandFacade.deleteStudy(memberId, studyId));
        }

        @Test
        @DisplayName("존재하지 않는 스터디를 삭제하려 시도하면 BusinessException이 발생한다.")
        void 스터디삭제_실패_통합_테스트3() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            StudyId studyId = StudyId.from(99999999L);

            // when, then
            assertThrows(BusinessException.class, () -> studyCommandFacade.deleteStudy(
                    MemberId.from(studyLeader.getMemberId()), studyId)
            );
        }
    }
}
