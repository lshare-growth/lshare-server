package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.member.entity.Member;

public class StudyJoinResponse {

    private final Long memberId;

    private StudyJoinResponse(Member member) {
        this.memberId = member.getMemberId();
    }

    public static StudyJoinResponse of(Member member) {
        return new StudyJoinResponse(member);
    }

    public Long getMemberId() {
        return memberId;
    }
}
