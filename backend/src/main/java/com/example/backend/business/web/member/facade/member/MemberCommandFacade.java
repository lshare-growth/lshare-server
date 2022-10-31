package com.example.backend.business.web.member.facade.member;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.BirthDate;
import com.example.backend.business.core.member.entity.values.BlogUrl;
import com.example.backend.business.core.member.entity.values.Introduction;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.application.member.MemberCommandService;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class MemberCommandFacade {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Transactional
    public void updateProfile(MemberId memberId,
                              BlogUrl blogUrl,
                              District district,
                              BirthDate birthDate,
                              Introduction introduction) {

        Member findMember = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        memberCommandService.updateProfile(findMember, blogUrl, district, birthDate, introduction);
    }

    @Transactional
    public void updateNickName(MemberId memberId, NickName nickName) {
        Member findMember = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        memberCommandService.updatNickName(findMember, nickName);
    }

    @Transactional
    public void withdrawalMembership(MemberId memberId) {
        Member findMember = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        memberCommandService.delete(findMember);
    }
}
