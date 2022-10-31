package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.study.entity.values.CommentCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("댓글 개수 테스트")
class CommentCountTest {

    @Nested
    @DisplayName("댓글을 작성할때")
    class NestedTest {

        @Test
        @DisplayName("초기 댓글수는 0으로 초기화 된다.")
        void 댓글수_초기화_테스트() {
            CommentCount commentCount = CommentCount.initCommentCount();

            assertEquals(0, commentCount.getCommentCount());
        }

        @Test
        @DisplayName("댓글이 달리면 댓글수가 증가한다.")
        void 댓글수_증가_테스트() {
            CommentCount commentCount = CommentCount.initCommentCount();
            CommentCount increasedCommentCount = commentCount.increaseAndGet();

            assertEquals(1, increasedCommentCount.getCommentCount());
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        CommentCount commentCountA = CommentCount.initCommentCount();
        CommentCount commentCountB = CommentCount.initCommentCount();

        assertEquals(commentCountA, commentCountB);
    }
}
