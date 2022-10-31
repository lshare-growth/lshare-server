package com.example.backend.lshare.business.domain.comment.values;

import com.example.backend.business.core.comment.entity.values.CommentWriter;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 작성자 테스트")
class CommentWriterTest {

    @Nested
    @DisplayName("댓글 작성자 객체가 생성될때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("값이 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_Null_또는_공백일때_댓글_작성자_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> CommentWriter.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(39)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_댓글_작성자_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> CommentWriter.from(Strings.repeat(parameter, 40)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"dev", "nightSky"})
        @DisplayName("한글자 이상, 39글자 이하의 값을 입력하면 댓글 작성자 객첵가 생성된다.")
        void 올바른_규칙으로_입력했을때_댓글_작성자_테스트(String parameter) {
            assertNotNull(CommentWriter.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"devjun"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(CommentWriter.from(parameter), CommentWriter.from(parameter));
    }
}
