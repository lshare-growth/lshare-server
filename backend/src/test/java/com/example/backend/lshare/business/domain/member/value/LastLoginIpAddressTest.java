package com.example.backend.lshare.business.domain.member.value;

import com.example.backend.business.core.member.entity.values.LastLoginIpAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("최근 접속 아이피 도메인 테스트")
class LastLoginIpAddressTest {

    @Nested
    @DisplayName("최근 접속한 아이피 객체가 생성될때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("값이 NULL 또는 공백이면 UN_KNOWN IP 값이 입력된다..")
        void 입력값이_Null_또는_공백일때_LastLoginIpAddress_객체_생성_테스트(String parameter) {
            assertEquals(LastLoginIpAddress.from("UN_KNOWN"), LastLoginIpAddress.from(parameter));
        }

        @Test
        @DisplayName("정상적인 IP 주소가 들어오면 그 값이 입력된다.")
        void 정상_IP_주소가_들어왔을때_객체_생성_테스트() {
            assertNotNull(LastLoginIpAddress.from("127.0.0.1"));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(LastLoginIpAddress.from(parameter), LastLoginIpAddress.from(parameter));
    }
}
