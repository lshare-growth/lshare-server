package com.example.backend.business.core.comment.entity.values;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.common.exception.BusinessException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.backend.common.exception.comment.CommentNotFoundException.COMMENT_NOT_FOUND_EXCEPTION;

@Embeddable
public class Comments {

    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Comment> comments;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 comment 외부 패키지에서 호출하지 말 것.
     */
    protected Comments() {
        this(new LinkedList<>());
    }

    public Comments(List<Comment> comments) {
        this.comments = comments;
    }

    public static Comments initComments() {
        return new Comments();
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public int getCommentSize() {
        return (int) comments.stream()
                .filter(Comment::isNotDeleted)
                .count();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Comment findCommentById(CommentId commentId) {
        return this.comments.stream()
                .filter(comment -> comment.hasSameCommentId(commentId))
                .filter(Comment::isNotDeleted)
                .findAny()
                .orElseThrow(()->new BusinessException(COMMENT_NOT_FOUND_EXCEPTION));
    }

    public void deleteComments() {
        this.comments.forEach(Comment::delete);
    }

    public void delete(MemberId memberId) {
        this.comments.forEach(comment -> comment.delete(memberId));
    }

    @Override
    public String toString() {
        return comments.toString();
    }
}
