package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.web.common.page.CustomSlice;

public class FollowersResponse {

    private final CustomSlice<FollowerResponse> followers;

    private FollowersResponse(CustomSlice<FollowerResponse> followers) {
        this.followers = followers;
    }

    public static FollowersResponse of(CustomSlice<FollowerResponse> followers) {
        return new FollowersResponse(followers);
    }

    public CustomSlice<FollowerResponse> getFollowers() {
        return followers;
    }

    @Override
    public String toString() {
        return String.format("팔로워 목록: %s", followers);
    }
}
