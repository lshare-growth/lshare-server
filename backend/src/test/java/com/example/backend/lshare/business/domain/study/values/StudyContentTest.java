package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.study.entity.values.StudyContent;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("스터디 내용 테스트")
class StudyContentTest {

    @Nested
    @DisplayName("스터디 내용을 입력할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("스터디 내용이 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_공백일때_스터디_내용_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> StudyContent.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_스터디_내용_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> StudyContent.from(Strings.repeat(parameter, 10001)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"반갑습니다. 백엔드 취업스터디입니다.", "도커 스터디 모집합니다.", "클라우드 스터디 모집합니다."})
        @DisplayName("한글자 이상, 10000글자 이하의 스터디 내용을 입력했을때 스터디 내용이 만들어진다.")
        void 올바른_규칙으로_입력했을때_스터디_내용_생성_테스트(String parameter) {
            assertNotNull(StudyContent.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"과제 열심히 해오시고 공유하실 스터디원 모집합니다."})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(StudyContent.from(parameter), StudyContent.from(parameter));
    }
}
