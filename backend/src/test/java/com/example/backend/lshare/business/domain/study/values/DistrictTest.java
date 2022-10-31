package com.example.backend.lshare.business.domain.study.values;

import com.example.backend.business.core.common.District;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("도시명 조회 테스트")
class DistrictTest {

    @Nested
    @DisplayName("도시명을 조회할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"캐나다", "몬트리올", "America"})
        @DisplayName("올바르지 않은 도시명(영문)으로 조회하면 DistrictNotFoundException이 발생한다.")
        void 도시명_조회실패_테스트(String parameter) {
            assertEquals(District.findDistrictByName(parameter), District.NONE);
        }

        @ParameterizedTest
        @ValueSource(strings = {"SEOUL", "BUSAN", "DAEGU", "INCHEON", "DAEJEON", "ULSAN"})
        @DisplayName("올바른 도시명(영문)으로 조회하면 해당 도시가 반환된다.")
        void 도시명_조회성공_테스트(String parameter) {
            assertEquals(District.findDistrictByName(parameter), District.valueOf(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"SEOUL", "BUSAN", "DAEGU", "INCHEON", "DAEJEON", "ULSAN"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(District.findDistrictByName(parameter), District.findDistrictByName(parameter));
    }
}
