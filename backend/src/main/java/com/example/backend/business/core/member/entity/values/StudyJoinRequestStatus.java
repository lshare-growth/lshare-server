package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.member.entity.values.pojo.StudyMemberCountIncrement;
import com.example.backend.business.core.member.entity.values.pojo.StudyMemberCountMaintenence;
import com.example.backend.business.core.study.entity.values.pojo.studymember.StudyMemberCountUpdateStatus;

import java.util.Arrays;

public enum StudyJoinRequestStatus {

    NONE("NONE", new StudyMemberCountMaintenence()),
    REQUESTED("REQUEST", new StudyMemberCountMaintenence()),
    REJECTED("REJECT", new StudyMemberCountMaintenence()),
    APPROVED("APPROVED", new StudyMemberCountIncrement());

    private final String studyJoinRequestStatus;
    private final StudyMemberCountUpdateStatus studyMemberCountUpdateStatus;

    StudyJoinRequestStatus(String studyJoinRequestStatus, StudyMemberCountUpdateStatus studyMemberCountUpdateStatus) {
        this.studyJoinRequestStatus = studyJoinRequestStatus;
        this.studyMemberCountUpdateStatus = studyMemberCountUpdateStatus;
    }

    public String getStudyJoinRequestStatus() {
        return studyJoinRequestStatus;
    }

    public StudyMemberCountUpdateStatus getStudyMemberCountInterface() {
        return studyMemberCountUpdateStatus;
    }

    public static StudyJoinRequestStatus findStudyJoinRequestStatus(String studyJoinRequest) {
        return Arrays.stream(values())
                .filter(request -> isEqualStatus(studyJoinRequest, request))
                .findAny()
                .orElseThrow();
    }

    private static boolean isEqualStatus(String studyJoinRequest, StudyJoinRequestStatus request) {
        return request.getStudyJoinRequestStatus().equals(studyJoinRequest);
    }
}
