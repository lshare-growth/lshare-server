package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;

import java.time.LocalDate;

public class MemberProfileResponse {

    private final Long memberId;
    private final String nickName;
    private final String memberProfileImageUrl;
    private final LocalDate birthDate;
    private final String githubLink;
    private final District district;
    private final String introduction;
    private final String blogUrl;

    public MemberProfileResponse(Member member) {
        this.memberId = member.getMemberId();
        this.nickName = member.getNickName();
        this.memberProfileImageUrl = member.getProfileImageUrl();
        this.birthDate = member.getBirthDate();
        this.githubLink = member.getGithubLink();
        this.district = member.getDistrict();
        this.introduction = member.getIntroduction();
        this.blogUrl = member.getBlogUrl();
    }

    public static MemberProfileResponse of(Member member) {
        return new MemberProfileResponse(member);
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getMemberProfileImageUrl() {
        return memberProfileImageUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public District getDistrict() {
        return district;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    @Override
    public String toString() {
        return String.format("회원 아이디: %s, 닉네임: %s", memberId, nickName);
    }
}
