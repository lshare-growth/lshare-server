package com.example.backend.business.core.comment.entity;

import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.comment.entity.values.ReCommentCount;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.common.values.DateTime;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.common.exception.BusinessException;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.backend.business.core.common.Deleted.TRUE;
import static com.example.backend.common.exception.member.MemberTypeException.UNAUTHORIZED_EXCEPTION;

@Entity
@DynamicUpdate
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Embedded
    private CommentContent content;

    @Embedded
    private CommentParentId commentParentId;

    @Embedded
    private ReCommentCount reCommentCount;

    @Embedded
    private DateTime dateTime;

    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 comment 외부 패키지에서 호출하지 말 것.
     */
    protected Comment() {
    }

    public Comment(Long commentId) {
        this.commentId = commentId;
    }

    private Comment(Member writer,
                    Study study,
                    CommentContent content) {

        this.writer = writer;
        this.study = study;
        this.content = content;
        this.commentParentId = CommentParentId.initCommentParentId();
        this.reCommentCount = ReCommentCount.initReCommentCount();
        this.deleted = Deleted.initDeletion();
        this.dateTime = DateTime.initDateTime();
    }

    private Comment(Member writer,
                    Study study,
                    CommentContent content,
                    CommentParentId commentParentId) {

        this.writer = writer;
        this.study = study;
        this.content = content;
        this.deleted = Deleted.initDeletion();
        this.commentParentId = commentParentId;
        this.reCommentCount = ReCommentCount.initReCommentCount();
        this.dateTime = DateTime.initDateTime();
    }

    public static Comment writeComment(Member writer,
                                       Study study,
                                       CommentContent content) {

        return new Comment(writer, study, content);
    }

    public static Comment writeReComment(Member writer,
                                         Study study,
                                         CommentContent content,
                                         CommentParentId commentParentId) {

        return new Comment(writer, study, content, commentParentId);
    }

    public Long getCommentId() {
        return commentId;
    }

    public Study getStudy() {
        return isDeleted() ? Study.getDeletedStudy() : study;
    }

    public Long getWriterId() {
        return writer.getMemberId();
    }

    public String getNickName() {
        return isDeleted() ? "" : writer.getNickName();
    }

    public String getWriter() {
        return isDeleted() ? "" : writer.getNickName();
    }

    public String getContent() {
        return isDeleted() ? content.getEmptyCommentContent() : content.getContent();
    }

    public CommentParentId getCommentParentIdAsValue() {
        return commentParentId;
    }

    public Long getCommentParentId() {
        return isDeleted() ? 0L : commentParentId.getCommentParentId();
    }

    public String getWriterProfileImage() {
        return isDeleted() ? "" : writer.getProfileImageUrl();
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public int getReCommentCount() {
        return reCommentCount.getReCommentCount();
    }

    public LocalDateTime getCreatedAt() {
        return dateTime.getCreatedAt();
    }

    public LocalDateTime getLastModifiedAt() {
        return dateTime.getLastModifiedAt();
    }

    public void updateContent(MemberId memberId, CommentContent content) {
        validateMemberId(memberId);
        this.content = content;
        update();
    }

    private void validateMemberId(MemberId memberId) {
        MemberId writerId = this.writer.getMemberIdAsValue();
        if (!writerId.equals(memberId)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    public void delete(MemberId memberId) {
        validateAuthorization(memberId);
        this.deleted = TRUE;
        update();
    }

    private void validateAuthorization(MemberId memberId) {
        if (!writer.getMemberIdAsValue().equals(memberId)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    private void update() {
        this.dateTime.update();
    }

    public boolean hasParentId() {
        return commentParentId.hasParentId();
    }

    public boolean isDeleted() {
        return deleted.equals(TRUE);
    }

    public boolean isNotDeleted() {
        return deleted.equals(Deleted.FALSE);
    }

    public boolean hasSameCommentId(CommentId commentId) {
        return this.commentId.equals(commentId.getCommentId());
    }

    public void increaseReCommentCount() {
        this.reCommentCount = reCommentCount.increaseAndGetReCommentCount();
    }

    public void decreaseReCommentCount() {
        this.reCommentCount = reCommentCount.decreaseAndGetReCommentCount();
    }

    public void delete() {
        this.deleted = TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getCommentId().equals(comment.getCommentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId());
    }

    @Override
    public String toString() {
        return commentId.toString();
    }
}
