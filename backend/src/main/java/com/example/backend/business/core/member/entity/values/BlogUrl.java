package com.example.backend.business.core.member.entity.values;

import com.example.backend.common.exception.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class BlogUrl {

    private static final String NULL_STRING = "";

    private static final int MAX_BLOG_URL_LENGTH = 60;

    private String blogUrl;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected BlogUrl() {
        this.blogUrl = NULL_STRING;
    }

    private BlogUrl(String blogUrl) {
        validateBlogUrl(blogUrl);
        this.blogUrl = convertTo(blogUrl);
    }

    public static BlogUrl initBlogUrl() {
        return new BlogUrl();
    }

    private void validateBlogUrl(String blogUrl) {
        if (!Objects.isNull(blogUrl) && blogUrl.length() > MAX_BLOG_URL_LENGTH) {
            throw new IllegalArgumentException("입력 가능한 블로그 주소 길이를 초과했습니다.", ErrorField.of("blogUrl", blogUrl));
        }
    }

    private String convertTo(String blogUrl) {
        if (Objects.isNull(blogUrl) || blogUrl.isBlank()) {
            return "";
        }
        return blogUrl;
    }

    public static BlogUrl from(String blogUrl) {
        return new BlogUrl(blogUrl);
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public boolean isEmpty() {
        return Objects.isNull(blogUrl) || blogUrl.isBlank() || blogUrl.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlogUrl)) return false;
        BlogUrl blogUrl1 = (BlogUrl) o;
        return blogUrl.equals(blogUrl1.blogUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogUrl);
    }

    @Override
    public String toString() {
        return blogUrl;
    }
}
