package com.example.backend.lshare.business.domain.tag.values;

import com.example.backend.business.core.tag.entity.values.TagCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("태그수 테스트")
class TagCountTest {

    @Test
    @DisplayName("태그된 횟수는 초기화시 1로 된다.")
    void 태그수_초기화() {
        TagCount tagCount = TagCount.initTagCount();

        assertEquals(tagCount.getTagCount(), 1);
    }

    @Test
    @DisplayName("태그하면 태그된 횟수가 증가한다.")
    void 태그수_증가() {
        TagCount tagCount = TagCount.initTagCount();
        tagCount.increaseTagCount();

        assertEquals(tagCount.getTagCount(), 2);
    }
}
