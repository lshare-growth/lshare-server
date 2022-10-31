package com.example.backend.business.core.tag.entity.values;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TagCount implements Serializable {

    private static final int FIRST_TAGED = 1;

    private int tagCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 tag 외부 패키지에서 호출하지 말 것.
     */
    protected TagCount() {
        this.tagCount = FIRST_TAGED;
    }

    public static TagCount initTagCount() {
        return new TagCount();
    }

    public void increaseTagCount() {
        this.tagCount++;
    }

    public int getTagCount() {
        return tagCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagCount)) return false;
        TagCount that = (TagCount) o;
        return tagCount == that.tagCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagCount);
    }

    @Override
    public String toString() {
        return String.valueOf(tagCount);
    }
}
