package com.example.backend.business.web.member.presentation.follow.dto.response;

import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.values.Followers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FollowingListResponse {

    private final List<FollowingResponse> followerList;
    private final List<Long> myFollowList;
    private final boolean hasNext;

    private FollowingListResponse(Followers followingList, boolean hasNext) {
        this.followerList = extractSource(followingList);
        this.myFollowList = Collections.emptyList();
        this.hasNext = hasNext;
    }

    private FollowingListResponse(Followers followingList, List<Long> myFollowList, boolean hasNext) {
        this.followerList = extractSource(followingList);
        this.myFollowList = myFollowList;
        this.hasNext = hasNext;
    }

    public static FollowingListResponse of(Followers followingList) {
        return new FollowingListResponse(followingList, followingList.hasNext());
    }

    public static FollowingListResponse of(Followers followingList, List<Long> myFollowList) {
        return new FollowingListResponse(followingList, myFollowList, followingList.hasNext());
    }

    private List<FollowingResponse> extractSource(Followers followingList) {
        return followingList.getFollowers().stream()
                .map(Follow::getTarget)
                .map(FollowingResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<FollowingResponse> getFollowerList() {
        return followerList;
    }

    public List<Long> getMyFollowList() {
        return myFollowList;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
