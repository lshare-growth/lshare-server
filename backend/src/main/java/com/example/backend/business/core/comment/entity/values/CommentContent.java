package com.example.backend.business.core.comment.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CommentContent {

    private static final int MAX_COMMENT_CONTENT_LENGTH = 150;

    private String content;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 comment 외부 패키지에서 호출하지 말 것.
     */
    protected CommentContent() {
    }

    public CommentContent(String content) {
        validateCommentContent(content);
        this.content = content;
    }

    public static CommentContent from(String commentContent) {
        return new CommentContent(commentContent);
    }

    private void validateCommentContent(String content) {
        if (Objects.isNull(content) || content.isBlank()) {
            throw new IllegalArgumentException("댓글 내용은 공백일 수 없습니다.");
        }
        if (content.length() > MAX_COMMENT_CONTENT_LENGTH) {
            throw new IllegalArgumentException("최대 입력 가능한 내용을 초과하셨습니다.");
        }
    }

    public static CommentContent getEmptyCommentContentAsValue() {
        return new CommentContent();
    }

    public String getEmptyCommentContent() {
        return "";
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentContent)) return false;
        CommentContent that = (CommentContent) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
