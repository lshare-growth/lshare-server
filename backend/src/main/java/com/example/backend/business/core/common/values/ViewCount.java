package com.example.backend.business.core.common.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ViewCount {

    private static final int ZERO = 0;

    private int viewCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 외부 패키지에서 호출하지 말 것.
     */
    protected ViewCount() {
        this.viewCount = ZERO;
    }

    public static ViewCount initViewCount() {
        return new ViewCount();
    }

    public ViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public ViewCount increaseAndGet() {
        return new ViewCount(viewCount + 1);
    }

    public int getViewCount() {
        return viewCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewCount)) return false;
        ViewCount viewCount1 = (ViewCount) o;
        return Objects.equals(getViewCount(), viewCount1.getViewCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getViewCount());
    }

    @Override
    public String toString() {
        return String.valueOf(viewCount);
    }
}
