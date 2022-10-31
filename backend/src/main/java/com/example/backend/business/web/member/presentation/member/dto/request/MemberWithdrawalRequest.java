package com.example.backend.business.web.member.presentation.member.dto.request;

public class MemberWithdrawalRequest {

    private Long memberId;

    private MemberWithdrawalRequest() {
    }

    public MemberWithdrawalRequest(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return String.format("탈퇴할 회원 아이디: %s", memberId);
    }
}
