package com.example.backend.business.web.member.facade.follow;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.values.Followers;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.application.follow.FollowQueryService;
import com.example.backend.business.web.member.presentation.member.dto.response.FollowHistoryExistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FollowQueryFacade {

    private final FollowQueryService followQueryService;

    @Transactional(readOnly = true)
    public FollowHistoryExistResponse findFollowHistoryById(MemberId sourceId, MemberId targetId) {
        return followQueryService.findFollowHistoryById(sourceId, targetId);
    }

    @Transactional(readOnly = true)
    public Followers findFollowerListById(MemberId memberId, Cursor cursor) {
        return followQueryService.findFollowerListById(memberId, cursor);
    }

    @Transactional(readOnly = true)
    public Followers findFollowingListById(MemberId memberId, Cursor cursor) {
        return followQueryService.findFollowingListById(memberId, cursor);
    }

    @Transactional(readOnly = true)
    public List<Long> findMyFollowList(MemberId memberId, List<Long> ids) {
        return followQueryService.findMyFollowerList(memberId, ids);
    }
}
