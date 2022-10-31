package com.example.backend.business.web.member.presentation.member.dto.request;

import com.example.backend.business.core.common.District;

import java.time.LocalDate;
import java.util.Objects;

public class MemberProfileUpdateRequest {

    private Long memberId;
    private String blogUrl;
    private String district;
    private LocalDate birthDate;
    private String introduction;

    private MemberProfileUpdateRequest() {
    }

    public MemberProfileUpdateRequest(Long memberId, String blogUrl, String district, LocalDate birthDate, String introduction) {
        this.memberId = memberId;
        this.blogUrl = blogUrl;
        this.district = district;
        this.birthDate = birthDate;
        this.introduction = introduction;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public String getDistrict() {
        return district;
    }

    public District getDistrictAsEnum() {
        if (Objects.isNull(district)) {
            return null;
        }
        return District.findDistrictByName(district);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    @Override
    public String toString() {
        return String.format("수정할 회원 아이디: %s, 블로그 URL: %s, 지역: %s, 생년월일: %s, 한 줄 소개: %s", memberId, blogUrl, district, birthDate, introduction);
    }
}
