package com.example.backend.lshare.business.domain.member.value;

import com.example.backend.business.core.member.entity.values.BlogUrl;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("블로그 URL 테스트")
class BlogUrlTest {

    @Nested
    @DisplayName("블로그 URL을 입력할때")
    class NestedTest {

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(60)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_블로그_URL_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> BlogUrl.from(Strings.repeat(parameter, 61)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"www.tistory.com", "www.githubio.com"})
        @DisplayName("한글자 이상, 60글자 이하의 블로그 URL을 입력했을때 블로그 URL이 입력된다.")
        void 올바른_규칙으로_입력했을때_블로그_URL_생성_테스트(String parameter) {
            BlogUrl blogUrl = BlogUrl.from(parameter);

            assertNotNull(blogUrl);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"www.tistory.com"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(BlogUrl.from(parameter), BlogUrl.from(parameter));
    }
}
