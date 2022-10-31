package com.example.backend.business.web.member.application.follow;

import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.FollowHistory;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.infrastructure.follow.command.FollowCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowCommandService {

    private final FollowCommandRepository followCommandRepository;

    @Transactional
    public void updateFollow(FollowHistory followHistory,
                             Member source,
                             Member target) {

        if (followHistory.exist()) {
            followCommandRepository.unfollow(source.getMemberIdAsValue(), target.getMemberIdAsValue());
            updateUnfollow(source, target);
            return;
        }
        updateFollow(source, target);
    }

    private void updateUnfollow(Member source, Member target) {
        source.decreaseFollowingCount();
        target.decraseFollowerCount();
    }

    private void updateFollow(Member source, Member target) {
        source.follow(new Follow(source, target));
        target.increaseFollowerCount();
    }
}
