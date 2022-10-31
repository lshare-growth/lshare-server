package com.example.backend.lshare.business.domain.member.value;

import com.example.backend.business.core.member.entity.values.NickName;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("사용자 닉네임 테스트")
class NickNameTest {

    @Nested
    @DisplayName("사용자 닉네임을 입력할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("닉네임이 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_Null_또는_공백일때_닉네임_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> NickName.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(39)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_닉네임_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> NickName.from(Strings.repeat(parameter, 40)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"dev", "nightSky"})
        @DisplayName("제목이 한글자 이상, 15글자 이하의 닉네임을 입력하면 닉네임이 생성된다.")
        void 올바른_규칙으로_입력했을때_닉네임_생성_테스트(String parameter) {
            assertNotNull(NickName.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"devjun"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(NickName.from(parameter), NickName.from(parameter));
    }
}
