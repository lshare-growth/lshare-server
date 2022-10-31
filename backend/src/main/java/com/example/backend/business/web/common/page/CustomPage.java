package com.example.backend.business.web.common.page;

import org.springframework.data.domain.Page;

import java.util.List;

public class CustomPage<T> {

    private final List<T> page;
    private final boolean sorted;
    private final int requestPageSize;
    private final long currentPageNumber;
    private final long totalElementSize;
    private final boolean firstPage;
    private final boolean last;
    private final boolean empty;

    private CustomPage(Page<T> page) {
        this.page = page.getContent();
        this.sorted = page.getSort().isSorted();
        this.requestPageSize = page.getSize();
        this.currentPageNumber = page.getTotalPages();
        this.totalElementSize = page.getTotalElements();
        this.firstPage = page.isFirst();
        this.last = page.isLast();
        this.empty = page.isEmpty();
    }

    private CustomPage(List<T> content, Page<T> page) {
        this.page = content;
        this.sorted = page.getSort().isSorted();
        this.requestPageSize = page.getSize();
        this.currentPageNumber = page.getTotalPages();
        this.totalElementSize = page.getTotalElements();
        this.firstPage = page.isFirst();
        this.last = page.isLast();
        this.empty = page.isEmpty();
    }

    public static <T> CustomPage<T> from(Page<T> pages) {
        return new CustomPage<>(pages);
    }

    public static <T> CustomPage<T> from(List<T> content, Page<T> pages) {
        return new CustomPage<>(content, pages);
    }

    public List<T> getPage() {
        return page;
    }

    public boolean isSorted() {
        return sorted;
    }

    public int getRequestPageSize() {
        return requestPageSize;
    }

    public long getTotalElementSize() {
        return totalElementSize;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public boolean isLast() {
        return last;
    }

    public long getCurrentPageNumber() {
        return currentPageNumber;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public String toString() {
        return String.format("현재 페이지: %s, 조회된 게시물 수: %s", getCurrentPageNumber(), getTotalElementSize());
    }
}
