package com.example.backend.lshare.business.domain.reaction;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.ReactionClicked;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("이모티콘 클릭여부 테스트")
class ReactionClickedTest {

    @Test
    @DisplayName("회원 아이디 유무에 따른 이모티콘 클릭여부 테스트")
    void 회원_아이디_유무에_따라_이모티콘_클릭_여부_결정_테스트() {
        assertAll(
                () -> assertEquals(ReactionClicked.FALSE, ReactionClicked.findReactionClickedByMemberId(null)),
                () -> assertEquals(ReactionClicked.TRUE, ReactionClicked.findReactionClickedByMemberId(MemberId.from(1L)))
        );
    }
}
