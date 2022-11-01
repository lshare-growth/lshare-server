package com.example.backend.business.web.member.presentation.follow.dto.response;

import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.values.Followers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FollowerListResponse {

    private final List<FollowerResponse> followerList;
    private final List<Long> myFollowList;
    private final boolean hasNext;

    private FollowerListResponse(Followers followerList, boolean hasNext) {
        this.followerList = extractSource(followerList);
        this.myFollowList = Collections.emptyList();
        this.hasNext = hasNext;
    }

    private FollowerListResponse(Followers followerList, List<Long> myFollowList, boolean hasNext) {
        this.followerList = extractSource(followerList);
        this.myFollowList = myFollowList;
        this.hasNext = hasNext;
    }

    public static FollowerListResponse of(Followers followerList) {
        return new FollowerListResponse(followerList, followerList.hasNext());
    }

    public static FollowerListResponse of(Followers followerList, List<Long> myFollowList) {
        return new FollowerListResponse(followerList, myFollowList, followerList.hasNext());
    }

    private List<FollowerResponse> extractSource(Followers followerList) {
        return followerList.getFollowers().stream()
                .map(Follow::getSource)
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
