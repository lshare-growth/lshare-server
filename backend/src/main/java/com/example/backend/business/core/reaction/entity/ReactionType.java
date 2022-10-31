package com.example.backend.business.core.reaction.entity;

import com.example.backend.common.mapper.api.EnumMapperType;

import java.util.List;

public enum ReactionType {

    COMMENT_REACTION(1, "CommentReaction", CommentReactionType.getCommentReactionTsypes()),
    DAILY_PLAN_REACTION(2, "DailyPlanReaction", DailyPlanReactionType.getDailyPlanReaction());

    private final int reactionTypeId;
    private final String type;
    private final List<? extends EnumMapperType> reactions;

    ReactionType(int reactionTypeId,
                 String type,
                 List<? extends EnumMapperType> reactions) {

        this.reactionTypeId = reactionTypeId;
        this.type = type;
        this.reactions = reactions;
    }

    public String getType() {
        return type;
    }
}
