package com.example.backend.lshare.business.domain.comment.values;

import com.example.backend.business.core.comment.entity.values.ReCommentCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("대댓글 개수 테스트")
class ReCommentCountTest {

    @Nested
    @DisplayName("대댓글을 작성할때")
    class NestedTest {

        @Test
        @DisplayName("초기 대댓글수는 0으로 초기화 된다.")
        void 대댓글수_초기화_테스트() {
            ReCommentCount commentCount = ReCommentCount.initReCommentCount();

            assertEquals(0, commentCount.getReCommentCount());
        }

        @Test
        @DisplayName("댓글이 0 미만이 되면 IllegalStateException이 발생한다.")
        void 대댓글수_감소_테스트() {
            ReCommentCount reCommentCount = ReCommentCount.initReCommentCount();
            assertThrows(IllegalStateException.class, reCommentCount::decreaseAndGetReCommentCount);
        }

        @Test
        @DisplayName("대댓글이 달리면 댓글수가 증가한다.")
        void 대댓글수_증가_테스트() {
            ReCommentCount reCommentCount = ReCommentCount.initReCommentCount();
            ReCommentCount increasedReCommentCount = reCommentCount.increaseAndGetReCommentCount();

            assertEquals(1, increasedReCommentCount.getReCommentCount());
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        assertEquals(ReCommentCount.initReCommentCount(), ReCommentCount.initReCommentCount());
    }
}
