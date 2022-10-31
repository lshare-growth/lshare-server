package com.example.backend.business.web.comment.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomSlice;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.Collections;
import java.util.List;

public class CommentPageResponse {

    // 빈 댓글
    private static final Slice<CommentResponse> emptyComments = new SliceImpl<>(Collections.emptyList());
    private static final CommentPageResponse emptyPage = new CommentPageResponse(emptyComments, Collections.emptyList());

    private final CustomSlice<CommentResponse> comments;
    private final List<ReactionResponse> reactions;
    private List<ReactionResponse> reactionsV2;
    private final boolean first;
    private final boolean last;
    private final boolean empty;
    private final boolean hasNext;

    private CommentPageResponse(Slice<CommentResponse> comments, List<ReactionResponse> reactions) {
        this.comments = CustomSlice.of(comments);
        this.reactions = reactions;
        this.first = comments.isFirst();
        this.last = comments.isLast();
        this.empty = comments.isEmpty();
        this.hasNext = comments.hasNext();
        this.reactionsV2 = reactions;
    }

    public static CommentPageResponse emptyPage() {
        return emptyPage;
    }

    public static CommentPageResponse of(Slice<CommentResponse> comments) {
        return new CommentPageResponse(comments, Collections.emptyList());
    }

    public static CommentPageResponse of(Slice<CommentResponse> comments, List<ReactionResponse> reactions) {
        return new CommentPageResponse(comments, reactions);
    }

    public CustomSlice<CommentResponse> getComments() {
        return comments;
    }

    public List<ReactionResponse> getReactions() {
        return reactions;
    }

    public List<ReactionResponse> getReactionsV2() {
        return reactionsV2;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
