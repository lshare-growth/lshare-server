package com.example.backend.business.web.member.presentation.member.dto.request;

public class FollowRequest {

    private Long targetId;

    private FollowRequest() {
    }

    public FollowRequest(Long targetId) {
        this.targetId = targetId;
    }

    public Long getTargetId() {
        return targetId;
    }

    @Override
    public String toString() {
        return String.format("팔로잉 대상: %s", targetId);
    }
}
