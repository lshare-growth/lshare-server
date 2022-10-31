package com.example.backend.business.core.common;

public enum Deleted {

    TRUE,
    FALSE;

    public static Deleted initDeletion() {
        return Deleted.FALSE;
    }

    public Deleted delete() {
        return Deleted.TRUE;
    }
}
