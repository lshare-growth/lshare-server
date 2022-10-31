package com.example.backend.business.core.reaction.entity;

import com.example.backend.common.mapper.api.ReactionMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CommentReactionType implements ReactionMapper {

    LIKE(1, "Like", "\uD83D\uDC4D"),
    HATE(2, "Hate", "\uD83D\uDC4E"),
    SMILE(3, "Smile", "\uD83D\uDE03"),
    GROOMY(4, "Groomy", "\uD83D\uDE15"),
    SHINE(5, "Shine", "✨"),
    HEART(6, "Heart", "❤️"),
    ROCKET(7, "Rocket", "\uD83D\uDE80"),
    EYES(8, "Eyes", "\uD83D\uDC40");

    private final int commentReactionTypeId;
    private final String type;
    private final String emotion;

    CommentReactionType(int commentReactionTypeId,
                        String type,
                        String emotion) {

        this.commentReactionTypeId = commentReactionTypeId;
        this.type = type;
        this.emotion = emotion;
    }

    public static List<String> getCommentReactionTypesAsString() {
        return Arrays.stream(values())
                .map(CommentReactionType::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<CommentReactionType> getCommentReactionTsypes() {
        return Arrays.stream(values())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public int getId() {
        return commentReactionTypeId;
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
