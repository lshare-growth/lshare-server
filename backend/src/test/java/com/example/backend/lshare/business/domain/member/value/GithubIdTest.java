package com.example.backend.lshare.business.domain.member.value;

import com.example.backend.business.core.member.entity.values.GithubId;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("GithubId 테스트")
class GithubIdTest {

    @Nested
    @DisplayName("GithubId를 등록할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("GithubId가 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_Null_또는_공백일때_GithubId_등록_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> GithubId.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(39)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_GithubId_등록_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> GithubId.from(Strings.repeat(parameter, 40)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"dev", "nightSky"})
        @DisplayName("제목이 한글자 이상, 39글자 이하의 GithubId를 입력했을때 GithubId가 등록된다.")
        void 올바른_규칙으로_입력했을때_GithubId_등록_테스트(String parameter) {
            assertNotNull(GithubId.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"devjun"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(GithubId.from(parameter), GithubId.from(parameter));
    }
}
