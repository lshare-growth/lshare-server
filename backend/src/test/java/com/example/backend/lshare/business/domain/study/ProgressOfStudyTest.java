package com.example.backend.lshare.business.domain.study;

import com.example.backend.business.core.study.entity.ProgressOfStudy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("스터디 진행방식 조회 테스트")
class ProgressOfStudyTest {

    @Nested
    @DisplayName("스터디 진행방식을 조회할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Null 또는 Null-String이 들어오면 IllegalArgumentException이 발생한다.")
        void 스터디_진행방식_조회에서_Null_또는_Null_String으로_조회_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> ProgressOfStudy.findProcessByType(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"HELLO", "BUSAN", "SEOUL"})
        @DisplayName("존재하지 않는 진행타입으로 조회하면 IllegalArgumentException이 발생한다.")
        void 존재하지_않는_스터디_진행방식을_조회_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> ProgressOfStudy.findProcessByType(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"ONLINE", "OFFLINE", "BOTH"})
        @DisplayName("진행타입으로 조회하면 해당 Meeting방식이 반환된다.")
        void 올바른_스터디_진행방식_조회_테스트(String parameter) {
            assertEquals(ProgressOfStudy.findProcessByType(parameter), ProgressOfStudy.valueOf(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"ONLINE", "OFFLINE", "BOTH"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(ProgressOfStudy.findProcessByType(parameter), ProgressOfStudy.findProcessByType(parameter));
    }
}
