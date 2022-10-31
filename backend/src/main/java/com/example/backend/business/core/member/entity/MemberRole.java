package com.example.backend.business.core.member.entity;

public enum MemberRole {

    ADMIN,
    USER;

    public static MemberRole initMemberRole() {
        return USER;
    }
}
