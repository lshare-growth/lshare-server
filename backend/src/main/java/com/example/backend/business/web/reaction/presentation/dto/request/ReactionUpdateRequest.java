package com.example.backend.business.web.reaction.presentation.dto.request;

public class ReactionUpdateRequest {

    private String emotion;

    private ReactionUpdateRequest() {
    }

    public ReactionUpdateRequest(String emotion) {
        this.emotion = emotion;
    }

    public String getEmotion() {
        return emotion;
    }

    @Override
    public String toString() {
        return String.format("추가할 이모티콘: %s", emotion);
    }
}
