package com.example.backend.business.core.study.entity;

import com.example.backend.common.mapper.api.EnumMapperType;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ProgressOfStudy implements EnumMapperType {

    ONLINE(1, "ONLINE", "온라인"),
    OFFLINE(2, "OFFLINE", "오프라인"),
    BOTH(3, "BOTH", "온라인/오프라인");

    private final int progressOfStudyId;
    private final String type;
    private final String value;

    ProgressOfStudy(int progressOfStudyId,
                    String type,
                    String value) {

        this.progressOfStudyId = progressOfStudyId;
        this.type = type;
        this.value = value;
    }

    public static ProgressOfStudy findProcessByType(String type) {
        return Arrays.stream(values())
                .filter(isEqualTo(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 스터디 진행방식을 찾을 수 없습니다."));
    }

    private static Predicate<ProgressOfStudy> isEqualTo(String type) {
        return progressOfStudy -> progressOfStudy.type.equals(type);
    }

    public ProgressOfStudy updateProgressOfStudy(ProgressOfStudy progressOfStudy) {
        if (contains(progressOfStudy)) {
            return progressOfStudy;
        }
        throw new IllegalArgumentException("해당하는 스터디 진행방식을 찾을 수 없습니다.");
    }

    private boolean contains(ProgressOfStudy progressOfStudy) {
        return Arrays.asList(values()).contains(progressOfStudy);
    }

    @Override
    public int getId() {
        return progressOfStudyId;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}
