package com.example.backend.business.core.comment.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CommentWriter {

    private String writer;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 comment 외부 패키지에서 호출하지 말 것.
     */
    protected CommentWriter() {
    }

    public CommentWriter(String writer) {
        validateCommentWriter(writer);
        this.writer = writer;
    }

    private void validateCommentWriter(String writer) {
        if (Objects.isNull(writer) || writer.isBlank()) {
            throw new IllegalArgumentException("작성자가 존재하지 않습니다.", ErrorField.of("writer", writer));
        }
        if (writer.length() > 39) {
            throw new IllegalArgumentException("입력 가능한 이름의 최대길이를 초과했습니다.", ErrorField.of("writer", writer));
        }
    }

    public static CommentWriter from(String writer) {
        return new CommentWriter(writer);
    }

    public String getWriter() {
        return writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentWriter)) return false;
        CommentWriter that = (CommentWriter) o;
        return getWriter().equals(that.getWriter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWriter());
    }

    @Override
    public String toString() {
        return writer;
    }
}
