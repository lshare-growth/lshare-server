package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StartDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("마일스톤 테스트")
class MilestoneTest {

    @Nested
    @DisplayName("마일스톤을 생성할때")
    class NestedTest {

        @Test
        @DisplayName("시작일이 마감일보다 늦다면 IllegalArgumentException가 발생한다.")
        void 마일스톤_생성_테스트() {
            LocalDate startDate = LocalDate.of(2022, 1, 2);
            LocalDate endDate = LocalDate.of(2022, 1, 1);

            assertThrows(IllegalArgumentException.class, () -> Milestone.from(startDate, endDate));
        }

        @Test
        @DisplayName("마일스톤에서 값이 하나라도 비어있으면 IllegalArgumentException이 발생한다.")
        void 마일스톤에_값이_NULL인_경우_테스트() {
            LocalDate caseAstartDate = LocalDate.now();
            LocalDate caseAendDate = null;

            LocalDate caseBstartDate = null;
            LocalDate caseBendDate = LocalDate.now();

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> Milestone.from(caseAstartDate, caseAendDate)),
                    () -> assertThrows(IllegalArgumentException.class, () -> Milestone.from(caseBstartDate, caseBendDate))
            );
        }

//        @Test
//        @DisplayName("당일날 마감되는 스터디는 정상적으로 개설된다.")
//        void 시작일이_오늘_이전일_경우_테스트() {
//            LocalDate startDate = LocalDate.now().minusDays(30);
//
//            assertThrows(IllegalArgumentException.class, () -> StartDate.from(startDate));
//        }

        @Test
        @DisplayName("당일날 마감되는 스터디는 정상적으로 개설된다.")
        void 당일날_마감되는_마일스톤_테스트() {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate;

            assertNotNull(Milestone.from(startDate, endDate));
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        LocalDate now = LocalDate.now();
        LocalDate afterTwoDays = LocalDate.now().plusDays(2);

        Milestone milestoneA = Milestone.from(now, afterTwoDays);
        Milestone milestoneB = Milestone.from(now, afterTwoDays);

        assertEquals(milestoneA, milestoneB);
    }
}
