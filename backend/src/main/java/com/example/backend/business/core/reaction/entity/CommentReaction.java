package com.example.backend.business.core.reaction.entity;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.UUID;

@Entity
public class CommentReaction {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID commentReactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Embedded
    private CommentParentId commentParentId;

    @Embedded
    private MemberId memberId;

    @Embedded
    private StudyId studyId;

    @Embedded
    private CommentReactionEmotion emotion;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 reaction 외부 패키지에서 호출하지 말 것.
     */
    protected CommentReaction() {
    }

    private CommentReaction(MemberId memberId,
                            StudyId studyId,
                            Comment comment,
                            CommentReactionEmotion emotion) {

        this.comment = comment;
        this.commentParentId = CommentParentId.from(comment.getCommentParentId());
        this.commentReactionId = createUUID();
        this.studyId = studyId;
        this.memberId = memberId;
        this.emotion = emotion;
    }

    public static CommentReaction from(MemberId memberId,
                                       StudyId studyId,
                                       Comment comment,
                                       CommentReactionEmotion emotion) {

        return new CommentReaction(memberId, studyId, comment, emotion);
    }

    private CommentReaction(Comment comment,
                            StudyId studyId,
                            MemberId memberId,
                            CommentReactionEmotion emotion) {

        this.comment = comment;
        this.commentParentId = CommentParentId.from(comment.getCommentParentId());
        this.commentReactionId = createUUID();
        this.studyId = studyId;
        this.memberId = memberId;
        this.emotion = emotion;
    }

    public static CommentReaction from(Comment comment,
                                       StudyId studyId,
                                       MemberId memberId,
                                       CommentReactionEmotion emotion) {

        return new CommentReaction(comment, studyId, memberId, emotion);
    }

    private CommentReaction(Long commentId,
                            String emotion,
                            Long memberId) {

        this.comment = new Comment(commentId);
        this.emotion = CommentReactionEmotion.from(emotion);
        this.memberId = MemberId.from(memberId);
    }

    public static CommentReaction from(Long commentId,
                                       String emotion,
                                       Long memberId) {

        return new CommentReaction(commentId, emotion, memberId);
    }

    private UUID createUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    public boolean isEqualTo(CommentReaction commentReaction) {
        return this.emotion.equals(commentReaction.emotion) && this.memberId.equals(commentReaction.getMemberIdAsValue());
    }

    public String getCommentReactionId() {
        return commentReactionId.toString();
    }

    public Long getCommentId() {
        return comment.getCommentId();
    }

    public String getEmotion() {
        return emotion.getEmotion();
    }

    public MemberId getMemberIdAsValue() {
        return memberId;
    }

    public Long getMemberId() {
        return memberId.getMemberId();
    }

    public boolean hasSameCommentId(Long commentId) {
        return this.comment.getCommentId().equals(commentId);
    }

    public boolean hasSameMemberId(Long memberId) {
        return this.memberId.getMemberId().equals(memberId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentReaction)) return false;
        CommentReaction that = (CommentReaction) o;
        return comment.equals(that.comment) && getMemberId().equals(that.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, getMemberId());
    }

    @Override
    public String toString() {
        return commentReactionId.toString();
    }
}
