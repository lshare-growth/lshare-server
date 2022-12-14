package com.example.backend.business.core.study.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class StudyContent {

    private String content;

    /**
     * @Nullary-Constructor JPA 기본 생성자.
     * 외부 패키지에서 호출하지 말 것.
     */
    protected StudyContent() {
    }

    public StudyContent(String content) {
        validateContent(content);
        this.content = content;
    }

    private void validateContent(String content) {
        if (Objects.isNull(content) || content.isBlank()) {
            throw new IllegalArgumentException("스터디 내용이 존재하지 않습니다.", ErrorField.of("content", content));
        }
        if (content.length() > 2000) {
            throw new IllegalArgumentException("입력 가능한 스터디 내용의 최대 길이를 초과했습니다.", ErrorField.of("content-length", content.length()));
        }
    }

    public static StudyContent from(String content) {
        return new StudyContent(content);
    }

    public String getContent() {
        return content;
    }

    public void updateContent(StudyContent content) {
        if (this.equals(content)) {
            return;
        }
        this.content = content.getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyContent)) return false;
        StudyContent that = (StudyContent) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
