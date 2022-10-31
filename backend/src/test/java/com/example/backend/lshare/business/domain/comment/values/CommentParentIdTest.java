package com.example.backend.lshare.business.domain.comment.values;

import com.example.backend.business.core.comment.entity.values.CommentParentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertNotNull;

@DisplayName("댓글 부모아이디 테스트")
class CommentParentIdTest {

    @Nested
    @DisplayName("댓글 부모아이디 객체를 생성할때")
    class NestedTest {

        @Test
        @DisplayName("값이 존재하지 않으면 IllegalArgumentException이 발생한다.")
        void 댓글_부모아이디가_Null일때_테스트() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> CommentParentId.from(null));
        }

        @ParameterizedTest
        @ValueSource(longs = {-1L, -2L})
        @DisplayName("값이 음수라면 IllegalArgumentException이 발생한다.")
        void 댓글_부모아이디가_음수일때_테스트(Long parameter) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> CommentParentId.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(longs = {1L, 2L})
        @DisplayName("올바른 값이 입력되면 부모아이디 객체가 생성된다.")
        void 댓글_부모아이디가_올바를때_테스트(Long parameter) {
            assertNotNull(CommentParentId.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(Long parameter) {
        Assertions.assertEquals(CommentParentId.from(parameter), CommentParentId.from(parameter));
    }
}
