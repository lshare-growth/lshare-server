package com.example.backend.business.core.common.values;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class LastModifiedAt {

    private LocalDateTime lastModifiedAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected LastModifiedAt() {
        this.lastModifiedAt = null;
    }

    public static LastModifiedAt initLastModifiedAt() {
        return new LastModifiedAt();
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public static LastModifiedAt update() {
        return new LastModifiedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastModifiedAt)) return false;
        LastModifiedAt that = (LastModifiedAt) o;
        return lastModifiedAt.equals(that.lastModifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastModifiedAt);
    }

    @Override
    public String toString() {
        return lastModifiedAt.toString();
    }
}
