package com.example.backend.business.web.member.presentation.member.dto.request;

public class UnFollowRequest {

    private Long targetId;

    private UnFollowRequest() {
    }

    public UnFollowRequest(Long targetId) {
        this.targetId = targetId;
    }

    public Long getTargetId() {
        return targetId;
    }

    @Override
    public String toString() {
        return String.format("언팔로우 대상: %s", targetId);
    }
}
