package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.web.study.presentation.dto.response.StudyMemberResponse;

import java.util.List;

public class StudyMembersResponse {

    private final List<StudyMemberResponse> studyMembers;

    public StudyMembersResponse(List<StudyMemberResponse> studyMembers) {
        this.studyMembers = studyMembers;
    }

    public static StudyMembersResponse of(List<StudyMemberResponse> studyMembers) {
        return new StudyMembersResponse(studyMembers);
    }

    public List<StudyMemberResponse> getStudyMembers() {
        return studyMembers;
    }
}
