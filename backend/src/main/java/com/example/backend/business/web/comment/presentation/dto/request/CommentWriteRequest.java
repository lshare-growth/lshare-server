package com.example.backend.business.web.comment.presentation.dto.request;

public class CommentWriteRequest {

    private Long studyId;
    private String content;

    private CommentWriteRequest() {
    }

    public CommentWriteRequest(Long studyId, String content) {
        this.studyId = studyId;
        this.content = content;
    }

    public Long getStudyId() {
        return studyId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("작성할 댓글 내용: %s", content);
    }
}
