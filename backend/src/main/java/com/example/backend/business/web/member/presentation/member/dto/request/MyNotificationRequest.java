package com.example.backend.business.web.member.presentation.member.dto.request;

public class MyNotificationRequest {

    private Long memberId;

    private MyNotificationRequest() {
    }

    public MyNotificationRequest(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return String.format("알림 조회할 회원 아이디: %s", memberId);
    }
}
