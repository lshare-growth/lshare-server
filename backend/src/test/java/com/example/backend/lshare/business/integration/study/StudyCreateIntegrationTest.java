package com.example.backend.lshare.business.integration.study;//package com.example.lshare.lshare.business.integration.study;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.business.web.study.facade.StudyCommandFacade;
import com.example.backend.business.web.study.facade.StudyQueryFacade;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("스터디 생성 통합 테스트")
class StudyCreateIntegrationTest extends IntegrationTestContainer {

    @Autowired
    protected StudyCommandFacade studyCommandFacade;

    @Autowired
    protected StudyQueryFacade studyQueryFacade;

    @Nested
    @DisplayName("스터디를 삭제할때")
    class NestedTest {

        @Test
        @DisplayName("내용을 빠트리지 않고 올바르게 작성했다면 스터디가 생성된다.")
        void 스터디개설_성공_통합_테스트() {
            // given
            Member studyLeader = memberCommandService.save(MemberTestData.createMemberTestData());
            MemberId memberId = MemberId.from(studyLeader.getMemberId());
            Study newStudy = StudyTestData.createStudyTestData();
            TagNames tagNames = TagNames.from(List.of("Backend"));

            // when
            Study createdStudy = studyCommandFacade.createStudy(memberId, newStudy, tagNames);

            // then
            assertAll(
                    () -> assertThat(createdStudy.getTitle().equals(newStudy.getTitle())),
                    () -> assertThat(createdStudy.getContent().equals(newStudy.getContent())),
                    () -> assertThat(createdStudy.getMilestoneAsValue().equals(newStudy.getMilestoneAsValue())),
                    () -> assertThat(createdStudy.getMaxStudyMemberCount() == newStudy.getMaxStudyMemberCount()),
                    () -> assertThat(createdStudy.getProgressOfStudy().equals(newStudy.getProgressOfStudy())),
                    () -> assertThat(createdStudy.getDistrict().equals(newStudy.getDistrict()))
            );
        }

        @Test
        @DisplayName("존재하지 않는 회원이 스터디를 개설하려고 시도하면 BusinessException이 발생한다.")
        void 스터디개설_실패_통합_테스트() {
            // given
            MemberId memberId = MemberId.from(99999999L);
            Study newStudy = StudyTestData.createStudyTestData();
            TagNames tagNames = TagNames.from(List.of("Backend Study", "Study"));

            // when, then
            assertThrows(BusinessException.class, () -> studyCommandFacade.createStudy(memberId, newStudy, tagNames));
        }
    }
}
