package com.example.backend.business.web.comment.presentation.dto.request;

public class CommentUpdateRequest {

    private String content;

    private CommentUpdateRequest() {
    }

    public CommentUpdateRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("수정할 댓글 내용: %s", content);
    }
}
