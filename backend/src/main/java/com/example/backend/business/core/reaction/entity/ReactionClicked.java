package com.example.backend.business.core.reaction.entity;

import com.example.backend.business.core.member.entity.values.MemberId;

import java.util.Objects;

public enum ReactionClicked {

    TRUE,
    FALSE;

    public static ReactionClicked findReactionClickedByMemberId(MemberId memberId) {
        if (Objects.isNull(memberId)) {
            return ReactionClicked.FALSE;
        }
        return ReactionClicked.TRUE;
    }

    public boolean isTrue() {
        return this.equals(TRUE);
    }
}
