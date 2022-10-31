package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.core.member.entity.Member;

public class MemberEditResponse {

    private final Long memberId;

    private MemberEditResponse(Member member) {
        this.memberId = member.getMemberId();
    }

    public static MemberEditResponse of(Member member) {
        return new MemberEditResponse(member);
    }

    @Override
    public String toString() {
        return String.format("수정한 회원 아이디 %s", memberId);
    }
}
