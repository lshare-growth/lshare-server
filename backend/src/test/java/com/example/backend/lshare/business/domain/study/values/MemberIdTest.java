package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.member.entity.values.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("회원 아이디 테스트")
class MemberIdTest {

    @Nested
    @DisplayName("회원 아이디를 생성할때")
    class NestedTest {

        @ParameterizedTest
        @NullSource
        @DisplayName("아이디가 Null이면 NullPointException이 발생한다.")
        void 회원_PK가_NULL일_경우_테스트(Long parameter) {
            assertThrows(UnauthorizedAccessException.class, () -> MemberId.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(longs = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 88888L, 912348123L, 999999999L})
        @DisplayName("회원 PK가 Null이 아니면 정상적인 회원 PK 값 객체가 생성된다.")
        void 회원_PK가_NULL이_아닐_경우_테스트(Long parameter) {
            assertEquals(MemberId.from(parameter).getMemberId(), parameter);
        }
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(Long parameter) {
        assertEquals(MemberId.from(parameter).getMemberId(), parameter);
    }
}
