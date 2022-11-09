package com.example.backend.business.core.comment.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CommentParentId {

    private static final Long INIT_COMMNET_PARENT_ID = 0L;

    private Long commentParentId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 comment 외부 패키지에서 호출하지 말 것.
     */
    protected CommentParentId() {
        this.commentParentId = INIT_COMMNET_PARENT_ID;
    }

    private CommentParentId(Long commentParentId) {
        validateCommentParentId(commentParentId);
        this.commentParentId = commentParentId;
    }

    public void validateCommentParentId(Long commentParentId) {
        if (Objects.isNull(commentParentId)) {
            throw new IllegalArgumentException("부모 아이디가 존재하지 않습니다.", ErrorField.of("commentParentId", commentParentId));
        }
        if (commentParentId < 0) {
            throw new IllegalArgumentException("부모 아이디는 음수일 수 없습니다.", ErrorField.of("commentParentId", commentParentId));
        }
    }

    public static CommentParentId initCommentParentId() {
        return new CommentParentId();
    }

    public static CommentParentId from(Long parentId) {
        return new CommentParentId(parentId);
    }

    public Long getCommentParentId() {
        return commentParentId;
    }

    public boolean hasParentId() {
        return this.commentParentId != 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentParentId)) return false;
        CommentParentId commentParentId1 = (CommentParentId) o;
        return commentParentId.equals(commentParentId1.commentParentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentParentId);
    }

    @Override
    public String toString() {
        return commentParentId.toString();
    }
}
