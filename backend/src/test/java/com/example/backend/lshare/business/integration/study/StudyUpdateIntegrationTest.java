package com.example.backend.lshare.business.integration.study;//package com.example.lshare.lshare.business.integration.study;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.example.backend.business.core.common.District.BUSAN;
import static com.example.backend.business.core.study.entity.ProgressOfStudy.OFFLINE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("스터디 수정 통합 테스트")
public class StudyUpdateIntegrationTest extends IntegrationTestContainer {

    @Nested
    @DisplayName("스터디를 수정할때")
    class NestedTest {

        @Test
        @DisplayName("내용을 빠트리지 않고 올바르게 작성했다면 스터디가 수정된다.")
        void 스터디_수정_성공_통합_테스트() {
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
                    OFFLINE,
                    BUSAN,
                    MaxStudyMemberCount.from(8),
                    Milestone.from(LocalDate.now(), LocalDate.now().plusDays(3)));

            assertAll(
                    () -> assertEquals("CICD스터디 모집합니다.", createdStudy.getTitle()),
                    () -> assertEquals("도중에 그만두는 무책임한 스터디원은 받지 않습니다.", createdStudy.getContent()),
                    () -> assertEquals(OFFLINE, createdStudy.getProgressOfStudy()),
                    () -> assertEquals(BUSAN, createdStudy.getDistrict()),
                    () -> assertEquals(8, createdStudy.getMaxStudyMemberCount()),
                    () -> assertEquals(Milestone.from(LocalDate.now(), LocalDate.now().plusDays(3)), createdStudy.getMilestoneAsValue())
            );
        }

        @Test
        @DisplayName("존재하지 않는 회원이 스터디를 수정하려 시도하면 BusinessException이 발생한다.")
        void 스터디_수정_실패_통합_테스트() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when, then
            Assertions.assertThrows(BusinessException.class, () ->
                    studyCommandFacade.updateStudy(
                            MemberId.from(9999999999L),
                            StudyId.from(createdStudy.getStudyId()),
                            StudyTitle.from("RealMySQL 스터디 모집합니다."),
                            StudyContent.from("index에 관해 토론하고 실제 프로젝트에 적용할 팀원 모집합니다."),
                            TagNames.from(List.of("MySQL", "RealMySQL", "Backend", "Database", "DB")),
                            OFFLINE,
                            BUSAN,
                            MaxStudyMemberCount.from(8),
                            Milestone.from(LocalDate.now(), LocalDate.now().plusDays(100))));
        }

        @Test
        @DisplayName("권한이 없는 회원이 스터디를 수정하려 시도하면 BusinessException이 발생한다.")
        void 스터디_수정_실패_통합_테스트2() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Member otherPersion = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when, then
            Assertions.assertThrows(BusinessException.class, () ->
                    studyCommandFacade.updateStudy(
                            MemberId.from(otherPersion.getMemberId()),
                            StudyId.from(createdStudy.getStudyId()),
                            StudyTitle.from("Java 스터디 모집합니다."),
                            StudyContent.from("이펙티브 자바 읽고 자신이 학습한 내용 공유할 스터디 모집합니다."),
                            TagNames.from(List.of("Java", "EffectiveJava", "Java Study", "Backend")),
                            OFFLINE,
                            BUSAN,
                            MaxStudyMemberCount.from(90),
                            Milestone.from(LocalDate.now(), LocalDate.now().plusDays(3))));
        }

        @Test
        @DisplayName("존재하지 않는 스터디를 수정하려 시도하면 BusinessException이 발생한다.")
        void 스터디_수정_실패_통합_테스트3() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            Study createdStudy = studyCommandFacade.createStudy(
                    MemberId.from(studyLeader.getMemberId()),
                    StudyTestData.createStudyTestData(),
                    TagNames.from(List.of("Backend"))
            );

            // when, then
            Assertions.assertThrows(BusinessException.class, () ->
                    studyCommandFacade.updateStudy(
                            MemberId.from(studyLeader.getMemberId()),
                            StudyId.from(9999999999L),
                            StudyTitle.from("프론트엔드 스터디 모집합니다."),
                            StudyContent.from("React에 능숙하신 분, 한 번 이상 프로젝트를 경험해보신 분이면 좋겠습니다."),
                            TagNames.from(List.of("React", "TypeScript", "CSS", "Javascript", "JS")),
                            OFFLINE,
                            BUSAN,
                            MaxStudyMemberCount.from(4),
                            Milestone.from(LocalDate.now(), LocalDate.now().plusDays(60))));
        }
    }
}
