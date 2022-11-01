package com.example.backend.business.web.member.presentation.follow.dto.response;

import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.Member;

public class FollowingResponse {

    private final Long memberId;
    private final String nickName;
    private final String profileImageUrl;

    public FollowingResponse(Long memberId, String nickName, String profileImageUrl) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }

    public FollowingResponse(Member member) {
        this.memberId = member.getMemberId();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
    }

    public FollowingResponse(Follow follow) {
        Member member = follow.getSource();

        this.memberId = member.getMemberId();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
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
        return String.format("팔로워 정보: [아이디: %s, 닉네임: %s, 프로필 이미지: %s", memberId, nickName, profileImageUrl);
    }
}
