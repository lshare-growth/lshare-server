package com.example.backend.lshare.business.domain.notice.values;

import com.example.backend.business.core.notice.entity.values.NoticeTitle;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("공지사항 제목 테스트")
class NoticeTitleTest {

    @Nested
    @DisplayName("공지사항 제목을 생성할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("제목이 없거나 비어있으면 IllegalArgumentException이 발생한다.")
        void 입력값이_공백일때_공지사항_제목_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> NoticeTitle.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A"})
        @DisplayName("입력가능한 글자수(50)를 초과하면 IllegalArgumentException이 발생한다.")
        void 입력가능한_글자수를_초과했을때_공지사항_제목_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> NoticeTitle.from(Strings.repeat(parameter, 51)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"백엔드 스터디 모집합니다.", "도커 스터디 모집합니다.", "리액트 스터디 모집합니다."})
        @DisplayName("제목이 한글자 이상, 50글자 이하의 스터디 제목을 입력했을때 스터디 제목이 입력된다.")
        void 올바른_규칙으로_입력했을때_공지사항_제목_생성_테스트(String parameter) {
            assertNotNull(NoticeTitle.from(parameter));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"백엔드 스터디 모집"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(NoticeTitle.from(parameter), NoticeTitle.from(parameter));
    }
}
