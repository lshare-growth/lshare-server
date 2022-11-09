package com.example.backend.business.core.notice.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NoticeTitle {

    private static final int MAX_POST_TITLE_LENGTH = 50;

    private String title;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 notice 외부 패키지에서 호출하지 말 것.
     */
    protected NoticeTitle() {
    }

    private NoticeTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    private void validateTitle(String title) {
        if (Objects.isNull(title) || title.isBlank()) {
            throw new IllegalArgumentException("공지사항 제목을 입력해주세요.", ErrorField.of("title", title));
        }
        if (title.length() > MAX_POST_TITLE_LENGTH) {
            throw new IllegalArgumentException("입력 가능한 공지사항 제목의 최대길이를 초과했습니다.", ErrorField.of("title", title));
        }
    }

    public static NoticeTitle from(final String title) {
        return new NoticeTitle(title);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoticeTitle)) return false;
        NoticeTitle that = (NoticeTitle) o;
        return getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public String toString() {
        return title;
    }
}
