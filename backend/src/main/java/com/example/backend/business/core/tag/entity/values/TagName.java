package com.example.backend.business.core.tag.entity.values;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TagName implements Serializable {

    private static final int MIN_TAG_NAME_LENGTH = 1;
    private static final int MAX_TAG_NAME_LENGTH = 15;

    private String tagName;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 tag 외부 패키지에서 호출하지 말 것.
     */
    protected TagName() {
    }

    private TagName(String tagName) {
        validateTagName(tagName);
        this.tagName = tagName;
    }

    private void validateTagName(String tagName) {
        if (Objects.isNull(tagName) || tagName.isBlank()) {
            throw new IllegalArgumentException("태그값을 입력해주세요.");
        }
        if (tagName.length() <= MIN_TAG_NAME_LENGTH) {
            throw new IllegalArgumentException("최소 한 글자 이상의 태그를 입력해주세요.");
        }
        if (tagName.length() > MAX_TAG_NAME_LENGTH) {
            throw new IllegalArgumentException("입력 가능한 태그의 최대길이를 초과했습니다.");
        }
    }

    public static TagName from(String tagName) {
        return new TagName(tagName);
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagName)) return false;
        TagName tagName1 = (TagName) o;
        return tagName.equals(tagName1.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }

    @Override
    public String toString() {
        return tagName;
    }
}
