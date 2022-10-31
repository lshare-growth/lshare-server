package com.example.backend.business.core.common.values;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class CreatedAt {

    private LocalDateTime createdAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected CreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    private CreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static CreatedAt initCreatedAt() {
        return new CreatedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatedAt)) return false;
        CreatedAt createdAt1 = (CreatedAt) o;
        return getCreatedAt().equals(createdAt1.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedAt());
    }

    @Override
    public String toString() {
        return createdAt.toString();
    }
}
