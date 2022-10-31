package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyMember;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudyResponse {

    private final Long memberId;
    private final Long studyOrganizerId;
    private final String memberProfileImageUrl;
    private final Long studyId;
    private final String title;
    private final String content;
    private final String studyOrganizer;
    private final String studyStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int currentStudyMemberCount;
    private final int maxStudyMemberCount;
    private final ProgressOfStudy progressOfStudy;
    private final District district;
    private final int commentCount;
    private final int viewCount;
    private final int likeCount;

    private StudyResponse(Long memberId,
                          StudyMember studyMember,
                          Study study) {

        Member studyOrganizer = studyMember.getMember();

        this.memberId = memberId;
        this.studyOrganizerId = studyOrganizer.getMemberId();
        this.memberProfileImageUrl = studyMember.getMemberProfileImageUrl();
        this.studyId = study.getStudyId();
        this.title = study.getTitle();
        this.content = study.getContent();
        this.studyOrganizer = studyMember.getStudyLeaderNickName();
        this.studyStatus = study.getStudyStatus();
        this.createdAt = study.getCreatedAt();
        this.lastModifiedAt = study.getLastModifiedAt();
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

    public static StudyResponse of(Long memberId,
                                   StudyMember studyMember,
                                   Study study) {

        return new StudyResponse(memberId, studyMember, study);
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getStudyOrganizerId() {
        return studyOrganizerId;
    }

    public String getMemberProfileImageUrl() {
        return memberProfileImageUrl;
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

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
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
        return String.format("[번호: %s, 제목: %s, 작성자: %s, 시작일: %s, 마감일: %s]", studyId, title.length() > 15 ? title.substring(0, 15) : title, studyOrganizer, startDate, endDate);
    }
}
