package com.example.backend.business.core.study.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum StudyStatus {

    RECRUITING(1, "RECRUITING", "스터디원 모집중"),
    RECRUITMENT_COMPLETE(2, "RECRUITMENT_COMPLETE", "스터디원 모집완료"),
    FINISHED(3, "FINISHED", "스터디 종료");

    private final int studyStatusId;
    private final String type;
    private final String value;

    StudyStatus(int studyStatusId,
                String type,
                String value) {

        this.studyStatusId = studyStatusId;
        this.type = type;
        this.value = value;
    }

    public static StudyStatus findStudyStatus(String status) {
        return Arrays.stream(values())
                .filter(isEqualTo(status))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("스터디 상태를 찾을 수 없습니다."));
    }

    private static Predicate<StudyStatus> isEqualTo(String status) {
        return studyStatus -> studyStatus.type.equals(status);
    }

    public String getType() {
        return type;
    }
}
