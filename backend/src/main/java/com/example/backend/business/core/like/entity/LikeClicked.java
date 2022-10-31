package com.example.backend.business.core.like.entity;

public enum LikeClicked {

    TRUE,
    FALSE;

    public static LikeClicked checkClick(Integer exist) {
        if (exist == null) {
            return LikeClicked.FALSE;
        }
        return LikeClicked.TRUE;
    }

    public boolean isTrue() {
        return this.equals(TRUE);
    }
}
