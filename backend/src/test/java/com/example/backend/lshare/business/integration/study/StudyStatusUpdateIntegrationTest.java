package com.example.backend.lshare.business.integration.study;//package com.example.lshare.lshare.business.integration.study;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyStatus;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("스터디 상태 수정 통합 테스트")
class StudyStatusUpdateIntegrationTest extends IntegrationTestContainer {

    @Nested
    @DisplayName("스터디 상태를 수정할때")
    class StudyStatusUpdateTest {

        @Test
        @DisplayName("올바른 상태를 입력했다면 스터디 상태가 수정된다.")
        void study_status_update_success_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("Backend")));

            // when, then
            assertAll(
                    () -> studyCommandFacade.updateStudyStatus(MemberId.from(studyLeader.getMemberId()), StudyId.from(createdStudy.getStudyId()), StudyStatus.FINISHED),
                    () -> studyCommandFacade.updateStudyStatus(MemberId.from(studyLeader.getMemberId()), StudyId.from(createdStudy.getStudyId()), StudyStatus.RECRUITING),
                    () -> studyCommandFacade.updateStudyStatus(MemberId.from(studyLeader.getMemberId()), StudyId.from(createdStudy.getStudyId()), StudyStatus.RECRUITMENT_COMPLETE)
            );
        }

        @Test
        @DisplayName("권한이 없는 회원이 스터디 상태 변경을 시도했을 경우 UnauthorizedAccessException이 발생한다.")
        void study_status_update_unauthorized_case_failure_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Member otherMember = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("Backend")));

            // when, then
            assertThrows(UnauthorizedAccessException.class, () -> studyCommandFacade.updateStudyStatus(MemberId.from(otherMember.getMemberId()), StudyId.from(createdStudy.getStudyId()), StudyStatus.FINISHED));
        }

        @Test
        @DisplayName("존재하지 않는 회원이 스터디 상태 변경을 시도했을 경우 MemberNotFoundException이 발생한다.")
        void study_status_update_member_not_found_case_failure_test() {
            // given
            MemberId memberId = MemberId.from(999999999L);
            Study createdStudy = studyCommandFacade.createStudy(MemberId.from(studyLeader.getMemberId()), StudyTestData.createStudyTestData(), TagNames.from(List.of("Backend")));

            // when, then
            assertThrows(MemberTypeException.class, () -> studyCommandFacade.updateStudyStatus(memberId, StudyId.from(createdStudy.getStudyId()), StudyStatus.FINISHED));
        }

        @Test
        @DisplayName("존재하지 않는 스터디의 상태 변경을 시도했을 경우 StudyNotFoundException이 발생한다.")
        void study_status_update_study_not_found_case_failure_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            StudyId studyId = StudyId.from(999999999L);

            // when, then
            assertThrows(StudyTypeException.class, () -> studyCommandFacade.updateStudyStatus(MemberId.from(studyLeader.getMemberId()), studyId, StudyStatus.FINISHED));
        }
    }
}
