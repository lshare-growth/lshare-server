package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.common.mapper.api.EnumMapperValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class ProgressOfStudyResponse {

    private final List<EnumMapperValue> progressOfStudies;

    private ProgressOfStudyResponse(List<EnumMapperValue> progressOfStudies) {
        this.progressOfStudies = progressOfStudies;
    }

    public static ProgressOfStudyResponse of(List<EnumMapperValue> processOfStudy) {
        return new ProgressOfStudyResponse(processOfStudy);
    }

    public List<EnumMapperValue> getProgressOfStudies() {
        return progressOfStudies;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
