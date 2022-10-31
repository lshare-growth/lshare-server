package com.example.backend.business.core.notice.entity.values;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
public class NoticeContent {

    public static final int MAXIMUM_CONTENT_LENGTH = 10000;

    @Lob
    private String content;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 notice 외부 패키지에서 호출하지 말 것.
     */
    protected NoticeContent() {
    }

    public NoticeContent(String content) {
        validateContent(content);
        this.content = content;
    }

    public static NoticeContent from(String content) {
        return new NoticeContent(content);
    }

    private void validateContent(String content) {
        if (Objects.isNull(content) || content.isBlank()) {
            throw new IllegalArgumentException("공지사항 내용을 입력해주세요.");
        }
        if (content.length() > MAXIMUM_CONTENT_LENGTH) {
            throw new IllegalArgumentException("입력 가능한 공지사항 내용의 최대 길이를 초과했습니다.");
        }
    }

    public void changeContent(final NoticeContent content) {
        if (this.equals(content)) {
            return;
        }
        this.content = content.getContent();
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoticeContent)) return false;
        NoticeContent that = (NoticeContent) o;
        return getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }

    @Override
    public String toString() {
        return content;
    }
}
