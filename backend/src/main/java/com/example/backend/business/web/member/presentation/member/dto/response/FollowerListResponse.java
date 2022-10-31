package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.values.Followers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FollowerListResponse {

    private final List<FollowerResponse> followerList;
    private final List<Long> myFollowList;
    private final boolean hasNext;

    private FollowerListResponse(Followers followers, boolean hasNext) {
        this.followerList = extractFollowers(followers);
        this.myFollowList = Collections.emptyList();
        this.hasNext = hasNext;
    }

    private FollowerListResponse(Followers followers, List<Long> myFollowList, boolean hasNext) {
        this.followerList = extractFollowers(followers);
        this.myFollowList = myFollowList;
        this.hasNext = hasNext;
    }

    public static FollowerListResponse of(Followers follwerList, boolean hasNext) {
        return new FollowerListResponse(follwerList, hasNext);
    }

    public static FollowerListResponse of(Followers followerListResponse, List<Long> myFollowList, boolean hasNext) {
        return new FollowerListResponse(followerListResponse, myFollowList, hasNext);
    }

    private List<FollowerResponse> extractFollowers(Followers followers) {
        return followers.getFollowers().stream()
                .map(Follow::getTarget)
                .map(FollowerResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<FollowerResponse> getFollowerList() {
        return followerList;
    }

    public List<Long> getMyFollowList() {
        return myFollowList;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
