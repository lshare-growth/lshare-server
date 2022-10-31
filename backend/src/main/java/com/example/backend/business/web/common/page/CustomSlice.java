package com.example.backend.business.web.common.page;

import org.springframework.data.domain.Slice;

import java.util.List;

public class CustomSlice<T> {

    private final List<T> content;
    private final boolean first;
    private final boolean last;
    private final boolean sorted;
    private final boolean empty;
    private final boolean hasNext;

    public CustomSlice(Slice<T> slice) {
        this.content = slice.getContent();
        this.first = slice.isFirst();
        this.last = slice.isLast();
        this.sorted = getSorted(slice);
        this.empty = slice.isEmpty();
        this.hasNext = slice.hasNext();
    }

    public static <T> CustomSlice<T> of(Slice<T> t) {
        return new CustomSlice<>(t);
    }

    private boolean getSorted(Slice<T> slice) {
        return slice.getSort().isSorted();
    }

    public List<T> getContent() {
        return content;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isSorted() {
        return sorted;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
