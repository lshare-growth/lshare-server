package com.example.backend.business.web.member.presentation.member.dto.request;

import com.example.backend.business.core.member.entity.values.NickName;

import java.beans.Transient;
import java.util.Objects;

public class MemberNickNameUpdateRequest {

    private String nickName;

    private MemberNickNameUpdateRequest() {
    }

    public MemberNickNameUpdateRequest(String nickName) {
        this.nickName = nickName;
    }

    @Transient
    public NickName getNickNameAsValue() {
        return NickName.from(nickName);
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberNickNameUpdateRequest)) return false;
        MemberNickNameUpdateRequest that = (MemberNickNameUpdateRequest) o;
        return getNickName().equals(that.getNickName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName());
    }

    @Override
    public String toString() {
        return String.format("변경할 닉네임: %s", nickName);
    }
}
