package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class StudyId {

    private Long studyId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected StudyId() {
    }

    private StudyId(Long studyId) {
        validateStudyId(studyId);
        this.studyId = studyId;
    }

    private void validateStudyId(Long studyId) {
        if (Objects.isNull(studyId) || studyId <= 0L) {
            throw new IllegalArgumentException("올바른 스터디 아이디를 입력해주세요.");
        }
    }

    public static StudyId from(Long studyId) {
        return new StudyId(studyId);
    }

    public Long getStudyId() {
        return studyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyId)) return false;
        StudyId studyId1 = (StudyId) o;
        return getStudyId().equals(studyId1.getStudyId());
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
