package com.example.backend.lshare.business.domain.member.value;

import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.GithubLink;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("GithubLink 테스트")
class GithubLinkTest {

    @Nested
    @DisplayName("GithubLink가 생성될때")
    class NestedTest {

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(60)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_GithubLink_입력_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> GithubLink.from(GithubId.from(Strings.repeat(parameter, 61))));
        }

        @Test
        @DisplayName("한글자 이상, 60글자 이하의 값이 입력되면 GithubLink가 생성된다.")
        void 올바른_GithubLink_생성_테스트() {
            assertNotNull(GithubLink.from(GithubId.from("honux")));
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        Assertions.assertEquals(GithubLink.from(GithubId.from("honux")), GithubLink.from(GithubId.from("honux")));
    }
}
