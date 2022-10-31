package com.example.backend.business.web.member.application.member;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.Followers;
import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.core.member.infrastructure.member.query.MemberQueryDslQueryRepository;
import com.example.backend.business.web.member.presentation.member.dto.response.FollowHistoryExistResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberQueryDslQueryRepository memberQueryDslQueryRepository;

    @Transactional(readOnly = true)
    public Optional<Member> findMemberById(MemberId memberId) {
        return memberQueryDslQueryRepository.findById(memberId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByGithubId(GithubId githubId) {
        return memberQueryDslQueryRepository.findByGithubId(githubId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByNickName(NickName nickName) {
        return memberQueryDslQueryRepository.findMemberByNickName(nickName);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberAndStudyJoinRequestsById(MemberId memberId) {
        return memberQueryDslQueryRepository.findStudyJoinRequestsById(memberId);
    }

    @Transactional(readOnly = true)
    public FollowHistoryExistResponse findFollowHistoryById(MemberId sourceId, MemberId targetId) {
        return memberQueryDslQueryRepository.findFollowHistoryById(sourceId, targetId);
    }

    @Transactional(readOnly = true)
    public NickNameExistResponse validateDuplicatedNickName(NickName nickName) {
        return memberQueryDslQueryRepository.validateDuplicatedNickName(nickName);
    }

    @Transactional(readOnly = true)
    public Followers findFollowerListById(MemberId memberId, Cursor cursor) {
        return Followers.from(memberQueryDslQueryRepository.findFollowerListById(memberId, cursor));
    }

    @Transactional(readOnly = true)
    public List<Long> findMyFollowerList(MemberId memberId, List<Long> targetIds) {
        return memberQueryDslQueryRepository.findFollowOrNot(memberId, targetIds);
    }

    @Transactional(readOnly = true)
    public Followers findFollowingListById(MemberId memberId, Cursor cursor) {
        return Followers.from(memberQueryDslQueryRepository.findFollowingList(memberId, cursor));
    }
}
