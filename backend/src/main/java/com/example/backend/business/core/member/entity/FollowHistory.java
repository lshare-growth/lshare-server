package com.example.backend.business.core.member.entity;

public enum FollowHistory {

    EXIST,
    NON_EXIST;

    public static FollowHistory findHistoryByValue(Integer result) {
        if (result == null) {
            return NON_EXIST;
        }
        return EXIST;
    }

    public boolean exist() {
        return this.equals(EXIST);
    }
}
