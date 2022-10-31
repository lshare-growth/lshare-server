package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.study.entity.values.StudyId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertNotNull;

@DisplayName("스터디 아이디 테스트")
class StudyIdTest {

    @Nested
    @DisplayName("댓글 부모아이디 객체를 생성할때")
    class NestedTest {

        @ParameterizedTest
        @NullSource
        @DisplayName("값이 존재하지 않으면 IllegalArgumentException이 발생한다.")
        void 스터디_아이디가_Null일때_테스트(Long parameter) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> StudyId.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(longs = {0L, -1L})
        @DisplayName("값이 0 또는 음수라면 IllegalArgumentException이 발생한다.")
        void 스터디_아이디가_음수일때_테스트(Long parameter) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> StudyId.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(longs = {1L, 2L})
        @DisplayName("올바른 값이 입력되면 스터디 아이디 객체가 생성된다.")
        void 스터디_아이디_값이_올바를때_테스트(Long parameter) {
            assertNotNull(StudyId.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(Long parameter) {
        Assertions.assertEquals(StudyId.from(parameter), StudyId.from(parameter));
    }
}
