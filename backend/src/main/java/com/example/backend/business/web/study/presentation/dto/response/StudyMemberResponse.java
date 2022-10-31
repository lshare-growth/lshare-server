package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.member.entity.Member;

public class StudyMemberResponse {

    private final Long memberId;
    private final String nickName;
    private final String profileImageUrl;

    public StudyMemberResponse(Long memberId, String nickName, String profileImageUrl) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }

    private StudyMemberResponse(Member member) {
        this.memberId = member.getMemberId();
        this.nickName = member.getGithubId();
        this.profileImageUrl = member.getProfileImageUrl();
    }

    public static StudyMemberResponse of(Member member) {
        return new StudyMemberResponse(member);
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public String toString() {
        return String.format("[깃허브 아이디: %s]", nickName);
    }
}
