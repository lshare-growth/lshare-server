package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.study.entity.values.LikeCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("좋아요 테스트")
class LikeCountTest {

    @Nested
    @DisplayName("좋아요를 누를때")
    class NestedTest {

        @Test
        @DisplayName("좋아요는 초기화시 0이 된다.")
        void 좋아요_초기화_테스트() {
            LikeCount likeCount = LikeCount.initLikeCount();

            assertEquals(0, likeCount.getLikeCount());
        }

        @Test
        @DisplayName("좋아요를 누르면 좋아요수가 증가한다.")
        void 좋아요_증가_테스트() {
            LikeCount likeCount = LikeCount.initLikeCount();
            LikeCount increasedLikeCount = likeCount.increaseAndGet();

            assertEquals(1, increasedLikeCount.getLikeCount());
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        LikeCount likeCountA = LikeCount.initLikeCount();
        LikeCount likeCountB = LikeCount.initLikeCount();

        assertEquals(likeCountA, likeCountB);
    }
}
