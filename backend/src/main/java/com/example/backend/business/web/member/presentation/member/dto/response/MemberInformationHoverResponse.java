package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;

public class MemberInformationHoverResponse {

    private final Long memberId;
    private final String nickName;
    private final String profileImageUrl;
    private final String introduction;
    private final String githubLink;
    private final District district;
    private final int followerCount;
    private final int followingCount;

    private MemberInformationHoverResponse(Member member) {
        this.memberId = member.getMemberId();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
        this.introduction = member.getIntroduction();
        this.githubLink = member.getGithubLink();
        this.district = member.getDistrict();
        this.followerCount = member.getFollowerCount();
        this.followingCount = member.getFollowingCount();
    }

    public static MemberInformationHoverResponse of(Member member) {
        return new MemberInformationHoverResponse(member);
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

    public String getIntroduction() {
        return introduction;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public District getDistrict() {
        return district;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    @Override
    public String toString() {
        return String.format("회원 아이디: %s", memberId);
    }
}
