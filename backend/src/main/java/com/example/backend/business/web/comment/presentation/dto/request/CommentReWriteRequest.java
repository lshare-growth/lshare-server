package com.example.backend.business.web.comment.presentation.dto.request;

public class CommentReWriteRequest {

    private String content;

    private CommentReWriteRequest() {
    }

    public CommentReWriteRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("작성할 대댓글 내용: %s", content);
    }
}
