package com.example.backend.lshare.business.integration.study;//package com.example.lshare.lshare.business.integration.study;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.common.exception.member.MemberTypeException;
import com.example.backend.common.exception.study.StudyTypeException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

@DisplayName("스터디 수정 통합 테스트")
public class StudyUpdateIntegrationTest extends IntegrationTestContainer {

    @Nested
    @DisplayName("스터디를 수정할때")
    class StudyUpdateTest {

        @Test
        @DisplayName("내용을 빠트리지 않고 올바르게 작성했다면 스터디가 수정된다.")
        void study_update_success_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when
            studyCommandFacade.updateStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyId.from(createdStudy.getStudyId()),
                    StudyTitle.from("CICD스터디 모집합니다."),
                    StudyContent.from("도중에 그만두는 무책임한 스터디원은 받지 않습니다."),
                    TagNames.from(List.of("CICD")),
                    ProgressOfStudy.OFFLINE,
                    MaxStudyMemberCount.from(8),
                    Milestone.from(LocalDate.now(), LocalDate.now().plusDays(3)));
        }

        @Test
        @DisplayName("존재하지 않는 회원이 스터디를 수정하려 시도하면 MemberNotFoundException이 발생한다.")
        void study_failure_nonmember_try_update_case_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when, then
            Assertions.assertThrows(MemberTypeException.class, () ->
                    studyCommandFacade.updateStudy(
                            MemberId.from(9999999999L),
                            StudyId.from(createdStudy.getStudyId()),
                            StudyTitle.from("RealMySQL 스터디 모집합니다."),
                            StudyContent.from("index에 관해 토론하고 실제 프로젝트에 적용할 팀원 모집합니다."),
                            TagNames.from(List.of("MySQL", "RealMySQL", "Backend", "Database", "DB")),
                            ProgressOfStudy.OFFLINE,
                            MaxStudyMemberCount.from(8),
                            Milestone.from(LocalDate.now(), LocalDate.now().plusDays(100))));
        }

        @Test
        @DisplayName("권한이 없는 회원이 스터디를 수정하려 시도하면 UnauthorizedAccessException이 발생한다.")
        void study_failure_unauthorized_case_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Member otherPersion = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when, then
            Assertions.assertThrows(UnauthorizedAccessException.class, () ->
                    studyCommandFacade.updateStudy(
                            MemberId.from(otherPersion.getMemberId()),
                            StudyId.from(createdStudy.getStudyId()),
                            StudyTitle.from("Java 스터디 모집합니다."),
                            StudyContent.from("이펙티브 자바 읽고 자신이 학습한 내용 공유할 스터디 모집합니다."),
                            TagNames.from(List.of("Java", "EffectiveJava", "Java Study", "Backend")),
                            ProgressOfStudy.OFFLINE,
                            MaxStudyMemberCount.from(90),
                            Milestone.from(LocalDate.now(), LocalDate.now().plusDays(3))));
        }

        @Test
        @DisplayName("존재하지 않는 스터디를 수정하려 시도하면 StudyNotFoundException이 발생한다.")
        void study_failure_notfound_study_update_case_test() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when, then
            Assertions.assertThrows(StudyTypeException.class, () ->
                    studyCommandFacade.updateStudy(
                            MemberId.from(studyLeader.getMemberId()),
                            StudyId.from(9999999999L),
                            StudyTitle.from("프론트엔드 스터디 모집합니다."),
                            StudyContent.from("React에 능숙하신 분, 한 번 이상 프로젝트를 경험해보신 분이면 좋겠습니다."),
                            TagNames.from(List.of("React", "TypeScript", "CSS", "Javascript", "JS")),
                            ProgressOfStudy.OFFLINE,
                            MaxStudyMemberCount.from(4),
                            Milestone.from(LocalDate.now(), LocalDate.now().plusDays(60))));
        }
    }
}
