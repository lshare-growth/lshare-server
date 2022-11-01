package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyHashTag;
import com.example.backend.business.core.study.entity.StudyMember;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class StudyLandingResponse {

    private final Long studyId;
    private final String title;
    private final String content;
    private final String studyOrganizer;
    private final String studyStatus;
    private final LocalDateTime createdAt;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int currentStudyMemberCount;
    private final int maxStudyMemberCount;
    private final ProgressOfStudy progressOfStudy;
    private final District district;
    private final int commentCount;
    private final int viewCount;
    private final int likeCount;

    public StudyLandingResponse(Study study) {
        this.studyId = study.getStudyId();
        this.title = study.getTitle();
        this.content = study.getContent();
        this.studyOrganizer = study.getStudyLeaderNickName();
        this.studyStatus = study.getStudyStatus();
        this.createdAt = study.getCreatedAt();
        this.startDate = study.getStartDate();
        this.endDate = study.getEndDate();
        this.currentStudyMemberCount = study.getCurrentStudyMemberCount();
        this.maxStudyMemberCount = study.getMaxStudyMemberCount();
        this.progressOfStudy = study.getProgressOfStudy();
        this.district = study.getDistrict();
        this.commentCount = study.getCommentCount();
        this.viewCount = study.getViewCount();
        this.likeCount = study.getLikeCount();
    }

    public StudyLandingResponse(StudyMember studyMember) {
        Study study = studyMember.getStudy();
        Member studyLeader = studyMember.getMember();

        this.studyId = study.getStudyId();
        this.title = study.getTitle();
        this.content = study.getContent();
        this.studyOrganizer = studyLeader.getNickName();
        this.studyStatus = study.getStudyStatus();
        this.createdAt = study.getCreatedAt();
        this.startDate = study.getStartDate();
        this.endDate = study.getEndDate();
        this.currentStudyMemberCount = study.getCurrentStudyMemberCount();
        this.maxStudyMemberCount = study.getMaxStudyMemberCount();
        this.progressOfStudy = study.getProgressOfStudy();
        this.district = study.getDistrict();
        this.commentCount = study.getCommentCount();
        this.viewCount = study.getViewCount();
        this.likeCount = study.getLikeCount();
    }

    public StudyLandingResponse(StudyHashTag studyHashTag) {
        this.studyId = studyHashTag.getStudyId();
        this.title = studyHashTag.getStudy().getTitle();
        this.content = studyHashTag.getStudy().getContent();
        this.studyOrganizer = null;
        this.studyStatus = studyHashTag.getStudy().getStudyStatus();
        this.createdAt = studyHashTag.getStudy().getCreatedAt();
        this.startDate = studyHashTag.getStudy().getStartDate();
        this.endDate = studyHashTag.getStudy().getEndDate();
        this.currentStudyMemberCount = studyHashTag.getStudy().getCurrentStudyMemberCount();
        this.maxStudyMemberCount = studyHashTag.getStudy().getMaxStudyMemberCount();
        this.progressOfStudy = studyHashTag.getStudy().getProgressOfStudy();
        this.district = studyHashTag.getStudy().getDistrict();
        this.commentCount = studyHashTag.getStudy().getCommentCount();
        this.viewCount = studyHashTag.getStudy().getViewCount();
        this.likeCount = studyHashTag.getStudy().getLikeCount();
    }

    public Long getStudyId() {
        return studyId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getStudyOrganizer() {
        return studyOrganizer;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getCurrentStudyMemberCount() {
        return currentStudyMemberCount;
    }

    public int getMaxStudyMemberCount() {
        return maxStudyMemberCount;
    }

    public ProgressOfStudy getProgressOfStudy() {
        return progressOfStudy;
    }

    public District getDistrict() {
        return district;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(studyId, JSON_STYLE);
    }
}
