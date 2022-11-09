package com.example.backend.business.core.reaction.entity.values;

import com.example.backend.business.core.common.ErrorField;
import com.example.backend.business.core.reaction.entity.CommentReactionType;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class CommentReactionEmotion {

    private static final List<String> emotions = CommentReactionType.getCommentReactionTypesAsString();

    private String emotion;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 reaction 외부 패키지에서 호출하지 말 것.
     */
    protected CommentReactionEmotion() {
    }

    private CommentReactionEmotion(String emotion) {
        validateEmotion(emotion);
        this.emotion = emotion;
    }

    private void validateEmotion(String emotion) {
        if (Objects.isNull(emotion) || !emotions.contains(emotion)) {
            throw new IllegalArgumentException("올바른 이모티콘을 입력해주세요.", ErrorField.of("emotion", emotion));
        }
    }

    public static CommentReactionEmotion from(String emotion) {
        return new CommentReactionEmotion(emotion);
    }

    public String getEmotion() {
        return emotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentReactionEmotion)) return false;
        CommentReactionEmotion that = (CommentReactionEmotion) o;
        return getEmotion().equals(that.getEmotion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmotion());
    }

    @Override
    public String toString() {
        return emotion;
    }
}
