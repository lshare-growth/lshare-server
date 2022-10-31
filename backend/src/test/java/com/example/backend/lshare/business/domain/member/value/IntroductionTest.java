package com.example.backend.lshare.business.domain.member.value;

import com.example.backend.business.core.member.entity.values.Introduction;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("자기소개 테스트")
class IntroductionTest {

    @Nested
    @DisplayName("자기소개를 작성할때")
    class NestedTest {

        @Test
        @DisplayName("자기소개는 입력했지만 빈 문자열을 입력했을때 IllegalArgumentException이 발생한다.")
        void 입력값이_공백일때_자기소개_입력_테스트() {
            assertThrows(IllegalArgumentException.class, () -> Introduction.from(""));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(250)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_자기소개_입력_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> Introduction.from(Strings.repeat(parameter, 251)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"반갑습니다. 어제 가입했는데 조금 늦게 인사드립니다. 잘부탁드립니다."})
        @DisplayName("제목이 한글자 이상, 15글자 이하의 자기소개를 입력했을때 자기소개가 작성된다.")
        void 올바른_규칙으로_입력했을때_자기소개_입력_테스트(String parameter) {
            assertNotNull(Introduction.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"반갑습니다."})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(Introduction.from(parameter), Introduction.from(parameter));
    }
}
