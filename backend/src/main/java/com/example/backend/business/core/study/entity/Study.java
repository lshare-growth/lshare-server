package com.example.backend.business.core.study.entity;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.Comments;
import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.common.District;
import com.example.backend.business.core.common.values.DateTime;
import com.example.backend.business.core.common.values.ViewCount;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.StudyMembers;
import com.example.backend.business.core.study.entity.values.CommentCount;
import com.example.backend.business.core.study.entity.values.CurrentStudyMemberCount;
import com.example.backend.business.core.study.entity.values.LikeCount;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.StudyHashTags;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicUpdate
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @Embedded
    private StudyTitle title;

    @Embedded
    private StudyContent content;

    @Embedded
    private Comments comments;

    @Column(columnDefinition = "ENUM('RECRUITING', 'RECRUITMENT_COMPLETE', 'FINISHED')")
    @Enumerated(EnumType.STRING)
    private StudyStatus studyStatus;

    @Embedded
    private StudyHashTags hashTags;

    @Embedded
    private Milestone milestone;

    @Embedded
    private StudyMembers studyMembers;

    @Embedded
    private CurrentStudyMemberCount currentStudyMemberCount;

    @Embedded
    private MaxStudyMemberCount maxStudyMemberCount;

    @Column(columnDefinition = "ENUM('ONLINE', 'OFFLINE', 'BOTH')")
    @Enumerated(EnumType.STRING)
    private ProgressOfStudy progressOfStudy;

    @Column(columnDefinition = "ENUM('SEOUL', 'BUSAN', 'DAEGU', 'INCHEON', 'DAEJEON', 'ULSAN')")
    @Enumerated(EnumType.STRING)
    private District district;

    @Embedded
    private CommentCount commentCount;

    @Embedded
    private ViewCount viewCount;

    @Embedded
    private LikeCount likeCount;

    @Embedded
    private DateTime dateTime;

    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected Study() {
    }

    private Study(StudyTitle title,
                  StudyContent content,
                  Milestone milestone,
                  MaxStudyMemberCount maxStudyMemberCount,
                  ProgressOfStudy progressOfStudy,
                  District district,
                  StudyHashTags hashTags) {

        this.title = title;
        this.content = content;
        this.milestone = milestone;
        this.comments = Comments.initComments();
        this.currentStudyMemberCount = CurrentStudyMemberCount.from(1);
        this.maxStudyMemberCount = maxStudyMemberCount;
        this.progressOfStudy = progressOfStudy;
        this.district = district;
        this.hashTags = hashTags;
        this.dateTime = DateTime.initDateTime();
        this.studyStatus = StudyStatus.RECRUITING;
        this.hashTags = StudyHashTags.initHashTags();
        this.viewCount = ViewCount.initViewCount();
        this.commentCount = CommentCount.initCommentCount();
        this.deleted = Deleted.initDeletion();
        this.likeCount = LikeCount.initLikeCount();
        this.studyMembers = StudyMembers.initStudyMembers();
    }

    public static Study createStudy(StudyTitle title,
                                    StudyContent content,
                                    Milestone milestone,
                                    MaxStudyMemberCount maxStudyMemberCount,
                                    ProgressOfStudy progressOfStudy,
                                    District district,
                                    StudyHashTags hashTags) {

        return new Study(title, content, milestone, maxStudyMemberCount, progressOfStudy, district, hashTags);
    }

    public Long getStudyId() {
        return studyId;
    }

    public String getStudyLeaderNickName() {
        return studyMembers.getStudyLeaderNickName();
    }

    public String getTitle() {
        return title.getTitle();
    }

    public StudyContent getStudyContentAsValue() {
        return content;
    }

    public String getContent() {
        return content.getContent();
    }

    public StudyHashTags getHashTags() {
        return hashTags;
    }

    public int getStudyMembersSize() {
        return studyMembers.getStudyMembersSize();
    }

    public CurrentStudyMemberCount getStudyMemberCountAsValue() {
        return currentStudyMemberCount;
    }

    public int getCurrentStudyMemberCount() {
        return currentStudyMemberCount.getCurrentStudyMemberCount();
    }

    public int getMaxStudyMemberCount() {
        return maxStudyMemberCount.getMaxStudyMemberCount();
    }

    public ProgressOfStudy getProgressOfStudy() {
        return progressOfStudy;
    }

    public Milestone getMilestoneAsValue() {
        return milestone;
    }

    public LocalDate getStartDate() {
        return milestone.getStartDate();
    }

    public LocalDate getEndDate() {
        return milestone.getEndDate();
    }

    public LocalDateTime getCreatedAt() {
        return dateTime.getCreatedAt();
    }

    public LocalDateTime getLastModifiedAt() {
        return dateTime.getLastModifiedAt();
    }

    public int getCommentCount() {
        return commentCount.getCommentCount();
    }

    public int getViewCount() {
        return viewCount.getViewCount();
    }

    public int getLikeCount() {
        return likeCount.getLikeCount();
    }

    public List<Comment> getComments() {
        return comments.getComments();
    }

    public int getCommentSize() {
        return comments.getCommentSize();
    }

    public District getDistrict() {
        return district;
    }

    public String getStudyStatus() {
        return studyStatus.name();
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public static Study getDeletedStudy() {
        return new Study();
    }

    public void registerHashTags(StudyHashTags hashTags) {
        hashTags.validate();
        this.hashTags = hashTags;
    }

    public void delete(Member studyLeader) {
        validateStudyLeader(studyLeader);
        this.comments.deleteComments();
        this.deleted = Deleted.TRUE;
        updateDate();
    }

    public void updateStudy(Member studyLeader,
                            StudyTitle title,
                            StudyContent content,
                            StudyHashTags hashTags,
                            ProgressOfStudy progressOfStudy,
                            District district,
                            MaxStudyMemberCount maxStudyMemberCount,
                            Milestone milestone) {

        validateStudyLeader(studyLeader);

        this.title = title;
        this.content = content;
        this.hashTags.updateHashTags(hashTags);
        this.progressOfStudy = progressOfStudy.updateProgressOfStudy(progressOfStudy);
        this.district = updateDistrict(district);
        this.milestone = milestone;
        this.maxStudyMemberCount = maxStudyMemberCount;
        updateDate();
    }

    public void updateStudyStatus(Member member, StudyStatus studyStatus) {
        validateStudyLeader(member);
        this.studyStatus = studyStatus;
    }

    public void validateStudyLeader(Member studyLeader) {
        studyMembers.validateStudyLeader(studyLeader);
    }

    private void updateDate() {
        this.dateTime.update();
    }

    private District updateDistrict(District district) {
        if (Objects.isNull(district)) {
            return this.district;
        }
        return district;
    }

    public void registerMember(Member studyLeader) {
        this.studyMembers.addStudyMember(studyLeader, this, StudyMemberRole.LEADER);
        this.currentStudyMemberCount = currentStudyMemberCount.increaseAndGetMemberCount();
    }

    public void addComment(Comment comment) {
        this.comments.addComment(comment);
        this.commentCount = this.commentCount.increaseAndGet();
    }

    public void increaseViewCount() {
        this.viewCount = viewCount.increaseAndGet();
    }

    public void increaseCommentCount() {
        this.commentCount = commentCount.increaseAndGet();
    }

    public void increaseLikeCount() {
        this.likeCount = likeCount.increaseAndGet();
    }

    public void decreaseLikeCount() {
        this.likeCount = likeCount.decreaseAndGet();
    }

    public void decreaseCommentCount() {
        this.commentCount = commentCount.decreaseAndGetCommentCount();
    }

    public Member findStudyLeader() {
        return studyMembers.findStudyLeader();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Study)) return false;
        Study study = (Study) o;
        return getStudyId().equals(study.getStudyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudyId());
    }

    @Override
    public String toString() {
        return studyId.toString();
    }
}

