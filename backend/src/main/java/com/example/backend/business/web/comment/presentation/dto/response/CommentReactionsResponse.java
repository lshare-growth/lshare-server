package com.example.backend.business.web.comment.presentation.dto.response;

import com.example.backend.business.web.reaction.presentation.dto.response.CommentReactionResponse;

import java.util.List;

public class CommentReactionsResponse {

    private final List<CommentReactionResponse> responses;

    private CommentReactionsResponse(List<CommentReactionResponse> response) {
        this.responses = response;
    }

    public static CommentReactionsResponse of(List<CommentReactionResponse> response) {
        return new CommentReactionsResponse(response);
    }

    public List<CommentReactionResponse> getResponses() {
        return responses;
    }
}
