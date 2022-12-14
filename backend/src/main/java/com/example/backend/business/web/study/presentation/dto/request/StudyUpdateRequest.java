package com.example.backend.business.web.study.presentation.dto.request;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.study.entity.values.Milestone;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class StudyUpdateRequest {

    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxStudyMemberCount;
    private String district;
    private String progressOfStudy;
    private List<String> hashTags;

    private StudyUpdateRequest() {
    }

    public StudyUpdateRequest(String title,
                              String content,
                              int maxStudyMemberCount,
                              String district,
                              String progressOfStudy,
                              LocalDate startDate,
                              LocalDate endDate,
                              List<String> hashTags) {

        this.title = title;
        this.content = content;
        this.hashTags = hashTags;
        this.maxStudyMemberCount = maxStudyMemberCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.district = district;
        this.progressOfStudy = progressOfStudy;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getMaxStudyMemberCount() {
        return maxStudyMemberCount;
    }

    @Transient
    public District getDistrictAsEnum() {
        if (Objects.isNull(district)) {
            return null;
        }
        return District.findDistrictByName(district);
    }

    public String getDistrict() {
        return district;
    }

    public String getProgressOfStudy() {
        return progressOfStudy;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    @Override
    public String toString() {
        return String.format("????????? ?????? ??????: [??????: %s, ??????: %s, ???????????????: %s, ??????: %s, ????????????: %s, ????????????: %s, ????????????: %s",
                title, content, maxStudyMemberCount, district, progressOfStudy, Milestone.from(getStartDate(), getEndDate()), getHashTags());
    }
}
