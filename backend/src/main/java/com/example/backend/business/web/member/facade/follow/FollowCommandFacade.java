package com.example.backend.business.web.member.facade.follow;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.application.follow.FollowCommandService;
import com.example.backend.business.web.member.application.follow.FollowQueryService;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.member.presentation.follow.dto.response.FollowHistoryExistResponse;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class FollowCommandFacade {

    private final FollowQueryService followQueryService;
    private final FollowCommandService followCommandService;
    private final MemberQueryService memberQueryService;
    private final RedissonClient redissonClient;

    @Transactional
    public void updateFollow(MemberId sourceId, MemberId targetId) {
        RLock lock = redissonClient.getLock(targetId.getMemberId().toString());
        try {
            boolean available = lock.tryLock(3, 1, TimeUnit.SECONDS);

            if (!available) {
                Thread.sleep(1000);
            }

            Member source = memberQueryService.findById(sourceId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            Member target = memberQueryService.findById(targetId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            FollowHistoryExistResponse followHistoryResponse = followQueryService.findFollowHistoryById(source.getMemberIdAsValue(), target.getMemberIdAsValue());

            followCommandService.updateFollow(followHistoryResponse.getFollowHistory(), source, target);
        } catch (InterruptedException e) {
            lock.unlock();
        }
    }
}
