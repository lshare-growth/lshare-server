package com.example.backend.lshare.business.domain.comment.values;

import com.example.backend.business.core.comment.entity.values.CommentContent;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글내용 값객체 테스트")
class CommentContentTest {

    @Nested
    @DisplayName("댓글 내용객체를 생성할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("댓글내용이 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_빈문자열일때_댓글내용_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> CommentContent.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(150)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_댓글내용_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> CommentContent.from(Strings.repeat(parameter, 151)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"스터디", "Frontend 스터디 모집합니다.", "Backend 스터디 모집합니다."})
        @DisplayName("한글자 이상, 150글자 이하의 댓글내용을 입력했을때 댓글내용 객체가 생성된다.")
        void 올바른_규칙으로_입력했을때_댓글내용_생성_테스트(String parameter) {
            CommentContent newCommentContent = CommentContent.from(parameter);

            assertNotNull(newCommentContent);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"프론트엔드 스터디"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(CommentContent.from(parameter), CommentContent.from(parameter));
    }
}
