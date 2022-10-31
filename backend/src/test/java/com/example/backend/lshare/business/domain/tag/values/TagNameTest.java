package com.example.backend.lshare.business.domain.tag.values;

import com.example.backend.business.core.tag.entity.values.TagName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("태그명 테스트")
class TagNameTest {

    @Nested
    @DisplayName("태그를 생성할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("태그명이 Null, 공백이면 IllegalArgumentException이 발생한다.")
        void 태그명이_Null_또는_공백일경우_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> TagName.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"태", "그", "명", "열다섯 글자를 초과했을 경우 테스트한다. 15자 이상"})
        @DisplayName("태그명이 한 글자이하 또는 최대 입력글자수를 초과할 경우 IllegalArgumentException이 발생한다.")
        void 태그명_한_글자_또는_최대_입력_가능한_글자수를_초과한_경우_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> TagName.from(parameter));
        }

        @Test
        @DisplayName("한글자 이상, 15글자 이하의 태그명을 입력했을때 태그명이 만들어진다.")
        void 올바른_규칙으로_입력했을때_태그명_생성() {
            String tagName = "스터디";
            TagName newTagName = TagName.from(tagName);
            assertNotNull(newTagName);
        }
    }

    @Test
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트() {
        assertEquals(TagName.from("태그명"), TagName.from("태그명"));
    }
}
