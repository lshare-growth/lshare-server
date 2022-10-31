package com.example.backend.business.web.comment.presentation.dto.request;

public class ReCommentUpdateRequest {

    private Long studyId;
    private String content;

    private ReCommentUpdateRequest() {
    }

    public ReCommentUpdateRequest(Long studyId, String content) {
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
        return String.format("수정할 대댓글 내용: %s", content);
    }
}
