package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("최대 스터디 멤버 테스트")
class MaxStudyMemberCountTest {

    @Nested
    @DisplayName("최대 스터디 멤버 객체를 생성할때")
    class NestedTest {

        @Test
        @DisplayName("최소 등록 가능한 스터디 멤버수에 미달되면 IllegalArgumentException이 발생한다.")
        void 최대소등록_가능한_스터디_멤버수_초과_테스트() {
            assertThrows(IllegalArgumentException.class, () -> MaxStudyMemberCount.from(0));
        }

        @Test
        @DisplayName("최대 등록 가능한 스터디 멤버수를 초과하면 IllegalArgumentException이 발생한다.")
        void 최대_등록_가능한_스터디_멤버수_초과_테스트() {
            assertThrows(IllegalArgumentException.class, () -> MaxStudyMemberCount.from(1000));
        }

        @Test
        @DisplayName("올바른 최대 스터디 인원 수를 입력하면 객체가 생성된다.")
        void 올바른_값을_입력했을때_최대_스터디_멤버수_생성_테스트() {
            assertNotNull(MaxStudyMemberCount.from(3));
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        assertEquals(MaxStudyMemberCount.from(3), MaxStudyMemberCount.from(3));
    }

}
