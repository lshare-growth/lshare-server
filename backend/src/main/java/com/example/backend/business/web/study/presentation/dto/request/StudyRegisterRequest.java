package com.example.backend.business.web.study.presentation.dto.request;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyTitle;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

public class StudyRegisterRequest {

    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxStudyMemberCount;
    private String district;
    private String progressOfStudy;
    private List<String> hashTags;

    private StudyRegisterRequest() {
    }

    public StudyRegisterRequest(String title,
                                String content,
                                LocalDate startDate,
                                LocalDate endDate,
                                int maxStudyMemberCount,
                                String district,
                                String progressOfStudy,
                                List<String> hashTags) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.district = district;
        this.progressOfStudy = progressOfStudy;
        this.maxStudyMemberCount = maxStudyMemberCount;
        this.hashTags = hashTags;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getMaxStudyMemberCount() {
        return maxStudyMemberCount;
    }

    public String getDistrict() {
        return district;
    }

    public String getProgressOfStudy() {
        return progressOfStudy;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    @Transient
    public Study toEntity() {
        return Study.createStudy(
                StudyTitle.from(title),
                StudyContent.from(content),
                Milestone.from(startDate, endDate),
                MaxStudyMemberCount.from(maxStudyMemberCount),
                ProgressOfStudy.findProcessByType(progressOfStudy),
                District.findDistrictByName(district),
                null);
    }

    @Override
    public String toString() {
        return String.format("스터디 개설: [제목: %s, 내용: %s, 최대인원수: %s, 지역: %s, 진행방식: %s, 마일스톤: %s, 해시태그: %s",
                title, content, maxStudyMemberCount, district, progressOfStudy, Milestone.from(startDate, endDate), hashTags);
    }
}
