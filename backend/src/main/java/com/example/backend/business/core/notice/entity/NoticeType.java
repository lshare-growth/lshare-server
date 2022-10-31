package com.example.backend.business.core.notice.entity;

import com.example.backend.common.mapper.api.EnumMapperType;

public enum NoticeType implements EnumMapperType {

    ALARAM(1, "ALARM", "알림"),
    NOTICE(2, "NOTICE", "공지");

    private final int noticeId;
    private final String noticeType;
    private final String noticeValue;

    NoticeType(int noticeId,
               String noticeType,
               String noticeValue) {

        this.noticeId = noticeId;
        this.noticeType = noticeType;
        this.noticeValue = noticeValue;
    }

    @Override
    public int getId() {
        return noticeId;
    }

    @Override
    public String getType() {
        return noticeType;
    }

    @Override
    public String getValue() {
        return noticeValue;
    }
}
