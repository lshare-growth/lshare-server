package com.example.backend.business.core.reaction.entity;

import com.example.backend.common.mapper.api.ReactionMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DailyPlanReactionType implements ReactionMapper {

    SMILE(1, "Smile", "\uD83D\uDE03"),
    CRY(2, "Cry", "\uD83D\uDE2D"),
    GROOMY(3, "Groomy", "\uD83E\uDD7A");

    private final int dailyPlanReactionTypeId;
    private final String type;
    private final String emotion;

    DailyPlanReactionType(int dailyPlanReactionTypeId,
                          String type,
                          String emotion) {

        this.dailyPlanReactionTypeId = dailyPlanReactionTypeId;
        this.type = type;
        this.emotion = emotion;
    }

    public static List<DailyPlanReactionType> getDailyPlanReaction() {
        return Arrays.stream(values())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public int getId() {
        return dailyPlanReactionTypeId;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return emotion;
    }

    @Override
    public List<? extends ReactionMapper> getReactions() {
        return Arrays.stream(values())
                .collect(Collectors.toUnmodifiableList());
    }
}
