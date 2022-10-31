package com.example.backend.data.study;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Objects;

public class StudyTestData {

    public static final Long STUDY_ID = 1L;

    public static Study createStudyTestData() {
        return Study.createStudy(
                StudyTitle.from("Backend 스터디 모집"),
                StudyContent.from("영한님 강의 수강"),
                Milestone.from(LocalDate.now(), LocalDate.now().plusDays(7)),
                MaxStudyMemberCount.from(3),
                ProgressOfStudy.ONLINE,
                District.SEOUL,
                null);
    }

    public static Study createStudyTestDataWithId() {
        Study newStudy = Study.createStudy(
                StudyTitle.from("Backend 스터디 모집"),
                StudyContent.from("영한님 강의 수강"),
                Milestone.from(LocalDate.now(), LocalDate.now().plusDays(7)),
                MaxStudyMemberCount.from(3),
                ProgressOfStudy.ONLINE,
                District.SEOUL,
                null);

        Field idField = ReflectionUtils.findField(Study.class, "studyId");
        ReflectionUtils.makeAccessible(Objects.requireNonNull(idField));
        ReflectionUtils.setField(Objects.requireNonNull(idField), newStudy, 1L);
        return newStudy;
    }

    public static Study createExpiredStudyDummy() {
        return Study.createStudy(
                StudyTitle.from("Backend 스터디 모집"),
                StudyContent.from("영한님 강의 수강"),
                Milestone.from(LocalDate.now(), LocalDate.of(2020, 10, 1)),
                MaxStudyMemberCount.from(3),
                ProgressOfStudy.ONLINE,
                District.SEOUL,
                null);
    }
}
