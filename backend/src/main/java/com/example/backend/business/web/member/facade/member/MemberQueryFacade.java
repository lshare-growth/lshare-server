package com.example.backend.business.web.member.facade.member;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.Followers;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.member.presentation.member.dto.response.FollowHistoryExistResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class MemberQueryFacade {

    private final MemberQueryService memberQueryService;

    @Transactional(readOnly = true)
    public Member findMemberById(MemberId memberId) {
        return memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public Member findMemberByNickName(NickName nickName) {
        return memberQueryService.findMemberByNickName(nickName).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public NickNameExistResponse validateDuplicatedNickName(MemberId memberId, NickName nickName) {
        Member findMember = memberQueryService.findMemberById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        return memberQueryService.validateDuplicatedNickName(nickName);
    }

    @Transactional(readOnly = true)
    public Followers findFollowerListById(MemberId memberId, Cursor cursor) {
        return memberQueryService.findFollowerListById(memberId, cursor);
    }

    @Transactional(readOnly = true)
    public FollowHistoryExistResponse findFollowHistoryById(MemberId sourceId, MemberId targetId) {
        return memberQueryService.findFollowHistoryById(sourceId, targetId);
    }

    @Transactional(readOnly = true)
    public List<Long> findMyFollowList(MemberId memberId, List<Long> ids) {
        return memberQueryService.findMyFollowerList(memberId, ids);
    }

    @Transactional(readOnly = true)
    public Followers findFollowingListById(MemberId memberId, Cursor cursor) {
        return memberQueryService.findFollowingListById(memberId, cursor);
    }
}
