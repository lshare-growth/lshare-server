package com.example.backend.business.core.image.entity.vo;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class OriginalFileName {

    private String originalFileName;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected OriginalFileName() {
    }

    private OriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public static OriginalFileName from(String originalFileName) {
        return new OriginalFileName(originalFileName);
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OriginalFileName)) return false;
        OriginalFileName that = (OriginalFileName) o;
        return getOriginalFileName().equals(that.getOriginalFileName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginalFileName());
    }

    @Override
    public String toString() {
        return originalFileName;
    }
}
