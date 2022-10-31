package com.example.backend.business.core.study.entity.values.pojo.studymember.status;

import com.example.backend.business.core.study.entity.values.pojo.studymember.StudyMemberCountUpdateStatus;

public class StudyMemberCountIncrement implements StudyMemberCountUpdateStatus {

    @Override
    public int getStudyMemberCountUpdateValue() {
        return 1;
    }
}
