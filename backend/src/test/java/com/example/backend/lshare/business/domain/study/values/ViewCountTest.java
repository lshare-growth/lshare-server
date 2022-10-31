package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.common.values.ViewCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("조회수 테스트")
class ViewCountTest {

    @Test
    @DisplayName("조회수는 초기화시 0로 된다.")
    void 조회수_초기화_테스트() {
        ViewCount viewCount = ViewCount.initViewCount();

        assertEquals(viewCount.getViewCount(), 0);
    }

    @Test
    @DisplayName("죄회를 하면 조회수가 증가한다.")
    void 조회수_증가_테스트() {
        ViewCount viewCount = ViewCount.initViewCount();
        viewCount.increaseAndGet();
        viewCount = viewCount.increaseAndGet();

        assertEquals(viewCount.getViewCount(), 1);
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        Assertions.assertEquals(ViewCount.initViewCount(), ViewCount.initViewCount());
    }
}
