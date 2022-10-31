package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class StudyPageByHashTagResponse {

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

    public StudyPageByHashTagResponse(Study study) {
        this.studyId = study.getStudyId();
        this.title = study.getTitle();
        this.content = study.getContent();
        this.studyOrganizer = study.findStudyLeader().getNickName();
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
