package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class StudyTitle {

    private static final int MAX_POST_TITLE_LENGTH = 50;

    private String title;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected StudyTitle() {
    }

    public StudyTitle(String title) {
        validatePostTitle(title);
        this.title = title;
    }

    private void validatePostTitle(String title) {
        if (Objects.isNull(title) || title.isBlank()) {
            throw new IllegalArgumentException("스터디 제목을 입력해주세요.");
        }
        if (title.length() > MAX_POST_TITLE_LENGTH) {
            throw new IllegalArgumentException("입력 가능한 스터디 제목의 최대길이를 초과했습니다.");
        }
    }

    public static StudyTitle from(final String title) {
        return new StudyTitle(title);
    }

    public void updateTitle(final StudyTitle title) {
        if (this.equals(title)) {
            return;
        }
        this.title = title.getTitle();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyTitle)) return false;
        StudyTitle studyTitle = (StudyTitle) o;
        return title.equals(studyTitle.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
