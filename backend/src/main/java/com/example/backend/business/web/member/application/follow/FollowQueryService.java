package com.example.backend.business.web.member.application.follow;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.values.Followers;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.infrastructure.follow.query.FollowQueryRepository;
import com.example.backend.business.web.member.presentation.follow.dto.response.FollowHistoryExistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowQueryService {

    private final FollowQueryRepository followQueryRepository;

    @Transactional(readOnly = true)
    public Followers findFollowerListById(MemberId memberId, Cursor cursor) {
        return Followers.from(followQueryRepository.findFollowerListById(memberId, cursor));
    }

    @Transactional(readOnly = true)
    public FollowHistoryExistResponse findFollowHistoryById(MemberId sourceId, MemberId targetId) {
        return followQueryRepository.findFollowHistoryById(sourceId, targetId);
    }

    @Transactional(readOnly = true)
    public Followers findFollowingListById(MemberId memberId, Cursor cursor) {
        return Followers.from(followQueryRepository.findFollowingList(memberId, cursor));
    }

    @Transactional(readOnly = true)
    public List<Long> findMyFollowerList(MemberId memberId, List<Long> targetIds) {
        return followQueryRepository.findFollowOrNot(memberId, targetIds);
    }
}
