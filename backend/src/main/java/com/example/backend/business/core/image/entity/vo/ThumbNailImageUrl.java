package com.example.backend.business.core.image.entity.vo;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ThumbNailImageUrl {

    private String noticeThumbNailImageUrl;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 notice 외부 패키지에서 호출하지 말 것.
     */
    protected ThumbNailImageUrl() {
    }

    private ThumbNailImageUrl(String noticeThumbNailImageUrl) {
        this.noticeThumbNailImageUrl = noticeThumbNailImageUrl;
    }

    public static ThumbNailImageUrl from(String noticeThumbNailImageUrl) {
        return new ThumbNailImageUrl(noticeThumbNailImageUrl);
    }

    public String getNoticeThumbNailImageUrl() {
        return noticeThumbNailImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThumbNailImageUrl)) return false;
        ThumbNailImageUrl that = (ThumbNailImageUrl) o;
        return getNoticeThumbNailImageUrl().equals(that.getNoticeThumbNailImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoticeThumbNailImageUrl());
    }

    @Override
    public String toString() {
        return noticeThumbNailImageUrl;
    }
}
