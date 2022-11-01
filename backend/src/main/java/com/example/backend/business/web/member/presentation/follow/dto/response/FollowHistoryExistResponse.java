package com.example.backend.business.web.member.presentation.follow.dto.response;

import com.example.backend.business.core.member.entity.FollowHistory;

public class FollowHistoryExistResponse {

    private final FollowHistory followHistory;

    public FollowHistoryExistResponse(Integer result) {
        this.followHistory = FollowHistory.findHistoryByValue(result);
    }

    private FollowHistoryExistResponse(FollowHistory followHistory) {
        this.followHistory = followHistory;
    }

    public static FollowHistoryExistResponse of(FollowHistory followHistory) {
        return new FollowHistoryExistResponse(followHistory);
    }

    public FollowHistory getFollowHistory() {
        return followHistory;
    }
}
