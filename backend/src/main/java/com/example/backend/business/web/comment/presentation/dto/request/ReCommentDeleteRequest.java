package com.example.backend.business.web.comment.presentation.dto.request;

public class ReCommentDeleteRequest {

    private Long studyId;

    private ReCommentDeleteRequest() {
    }

    public ReCommentDeleteRequest(Long studyId) {
        this.studyId = studyId;
    }

    public Long getStudyId() {
        return studyId;
    }

    @Override
    public String toString() {
        return String.format("삭제할 대댓글 스터디 아이디: %s", studyId);
    }
}
