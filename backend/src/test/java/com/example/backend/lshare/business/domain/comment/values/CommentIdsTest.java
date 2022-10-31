package com.example.backend.lshare.business.domain.comment.values;

import com.example.backend.business.core.comment.entity.values.pojo.CommentIds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 아이디 목록 테스트")
class CommentIdsTest {

    @Nested
    @DisplayName("댓글 아이디 목록 객체를 생성할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("댓글내용이 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_Null_이거나_빈_값일때_댓글_아이디_목록_생성_테스트(Set<Long> parameter) {
            assertThrows(IllegalArgumentException.class, () -> CommentIds.from(parameter));
        }

        @Test
        @DisplayName("올바른 아이디 값이 들어왔을때 댓글 목록 객체가 생성된다.")
        void 올바른_규칙으로_입력했을때_댓글목록_생성_테스트() {
            CommentIds newCommentContent = CommentIds.from(Set.of(1L, 2L));

            assertNotNull(newCommentContent);
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        Assertions.assertEquals(CommentIds.from(Set.of(2L, 1L)), CommentIds.from(Set.of(1L, 2L)));
    }
}
