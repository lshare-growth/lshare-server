package com.example.backend.business.web.member.presentation.member.dto.request;

public class MyStudyPageRequest {

    private Long memberId;

    public MyStudyPageRequest(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
