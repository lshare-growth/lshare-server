package com.example.backend.common.login.model.authentication;

import com.example.backend.business.core.member.entity.values.MemberId;

import java.util.Objects;

public class AuthenticatedMember {

    private MemberId authenticatedId;

    private AuthenticatedMember() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public AuthenticatedMember(Object authenticatedId) {
        this.authenticatedId = MemberId.from(authenticatedId);
    }

    public MemberId getAuthenticatedIdAsValue() {
        return authenticatedId;
    }

    public Long getAuthenticatedId(){
        return authenticatedId.getMemberId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticatedMember)) return false;
        AuthenticatedMember that = (AuthenticatedMember) o;
        return getAuthenticatedIdAsValue().equals(that.getAuthenticatedIdAsValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthenticatedIdAsValue());
    }

    @Override
    public String toString() {
        return String.format("인증된 사용자 아이디: %s", authenticatedId);
    }
}
