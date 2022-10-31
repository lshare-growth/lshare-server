package com.example.backend.lshare.business.domain.reaction.values;

import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("이모티콘 생성 테스트")
class EmotionTest {

    /**
     * 이모티콘 종류
     * 좋아요(Like): "\uD83D\uDC4D",
     * 싫어요(Hate): "\uD83D\uDC4E",
     * 웃음(Smile): "\uD83D\uDE03",
     * 우울(Groomy): "\uD83D\uDE15",
     * 별빛(Shine): "✨",
     * 하트(Heart): "❤️",
     * 로켓(Rocket): "\uD83D\uDE80",
     * 눈(Eyes)   : "\uD83D\uDC40"
     */

    @Nested
    @DisplayName("이모티콘을 조회할때")
    class NestedTest {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"^^", "^_^", "ㅡㅡ", "^^"})
        @DisplayName("이모티콘이 존재하지 않거나 등록되지 않은 이모티콘을 조회할때 IllegalArgumentException이 발생한다.")
        void 등록되지_않은_이모티콘_생성_테스트(String parameter) {
            assertThrows(IllegalArgumentException.class, () -> CommentReactionEmotion.from(parameter));
        }

        @ParameterizedTest
        @ValueSource(strings = {"\uD83D\uDC4D", "\uD83D\uDC4E", "\uD83D\uDE03", "\uD83D\uDE15", "✨", "❤️", "\uD83D\uDE80", "\uD83D\uDC40"})
        @DisplayName("올바른 표정을 입력하면 이모티콘이 생성된다.")
        void 이모티콘_생성_테스트(String parameter) {
            assertEquals(CommentReactionEmotion.from(parameter).getEmotion(), parameter);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"\uD83D\uDC4D", "\uD83D\uDC4E", "\uD83D\uDE03", "\uD83D\uDE15", "✨", "❤️", "\uD83D\uDE80", "\uD83D\uDC40"})
    @DisplayName("Equals와 HashCode를 재정의했다면 값으로 객체를 비교한다.")
    void euqlas_hashcode_재정의_테스트(String parameter) {
        Assertions.assertEquals(CommentReactionEmotion.from(parameter), CommentReactionEmotion.from(parameter));
    }
}
