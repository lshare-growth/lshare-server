package com.example.backend.business.web.common.request;

import com.example.backend.business.core.common.values.Cursor;

public class CursorPageRequest {

    private Long previous;
    private Long next;
    private Integer pageSize;

    private CursorPageRequest() {
    }

    public CursorPageRequest(Long previous, Long next, Integer pageSize) {
        this.previous = previous;
        this.next = next;
        this.pageSize = pageSize;
    }

    public Long getPrevious() {
        return previous;
    }

    public Long getNext() {
        return next;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Cursor getCursor() {
        return new Cursor(previous, pageSize);
    }

    @Override
    public String toString() {
        return String.format("이전 페이지 번호: %s, 다음 페이지 번호: %s, 페이지 크기: %s", previous, next, pageSize);
    }
}
