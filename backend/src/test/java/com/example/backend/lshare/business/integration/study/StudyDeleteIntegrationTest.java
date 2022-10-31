package com.example.backend.lshare.business.integration.study;//package com.example.lshare.lshare.business.integration.study;

import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.common.exception.member.MemberTypeException;
import com.example.backend.common.exception.study.StudyTypeException;
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
    class StudyDeleteTest {

        @Test
        @DisplayName("스터디 개설자가 자신이 개설한 스터디를 삭제하면 스터디가 삭제된다.")
        void study_delete_success_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study newStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("React")));

            // when
            newStudy.delete(studyLeader);

            // then
            assertEquals(newStudy.getDeleted(), Deleted.TRUE);
        }

        @Test
        @DisplayName("권한이 없는 회원이 스터디를 삭제하려 시도하면 UnauthorizedAccessException이 발생한다.")
        void study_failure_delete_unauthorized_case_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Member otherMember = memberCommandService.save(MemberTestData.createMemberTestData());
            Study newStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("React")));

            assertThrows(UnauthorizedAccessException.class, () -> studyCommandFacade.deleteStudy(MemberId.from(otherMember.getMemberId()), StudyId.from(newStudy.getStudyId())));
        }

        @Test
        @DisplayName("존재하지 않는 회원이 스터디를 삭제하려 시도하면 MemberNotFoundException이 발생한다.")
        void study_failure_delete_member_not_found_case_test() {
            // given
            MemberId memberId = MemberId.from(99999999L);
            StudyId studyId = StudyId.from(99999999L);

            assertThrows(MemberTypeException.class, () -> studyCommandFacade.deleteStudy(memberId, studyId));
        }

        @Test
        @DisplayName("존재하지 않는 스터디를 삭제하려 시도하면 StudyNotFoundException이 발생한다.")
        void study_failure_delete_study_not_found_case_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            StudyId studyId = StudyId.from(99999999L);

            assertThrows(StudyTypeException.class, () -> studyCommandFacade.deleteStudy(MemberId.from(studyLeader.getMemberId()), studyId));
        }
    }
}
