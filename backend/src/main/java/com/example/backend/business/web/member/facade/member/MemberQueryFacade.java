package com.example.backend.business.web.member.facade.member;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class MemberQueryFacade {

    private final MemberQueryService memberQueryService;

    @Transactional(readOnly = true)
    public Member findById(MemberId memberId) {
        return memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public Member findByNickName(NickName nickName) {
        return memberQueryService.findByNickName(nickName).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public NickNameExistResponse validateDuplicatedNickName(MemberId memberId, NickName nickName) {
        Member findMember = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        return memberQueryService.validateDuplicatedNickName(nickName);
    }
}
