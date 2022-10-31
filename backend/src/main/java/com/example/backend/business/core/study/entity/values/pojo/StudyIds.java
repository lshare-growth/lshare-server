package com.example.backend.business.core.study.entity.values.pojo;

import java.util.List;
import java.util.Objects;

public class StudyIds {

    private List<Long> studyIds;

    private StudyIds(List<Long> studyIds) {
        this.studyIds = studyIds;
    }

    public static StudyIds from(List<Long> studyIds) {
        return new StudyIds(studyIds);
    }

    public List<Long> getStudyIds() {
        return studyIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyIds)) return false;
        StudyIds studyIds1 = (StudyIds) o;
        return getStudyIds().equals(studyIds1.getStudyIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudyIds());
    }

    @Override
    public String toString() {
        return studyIds.toString();
    }
}
