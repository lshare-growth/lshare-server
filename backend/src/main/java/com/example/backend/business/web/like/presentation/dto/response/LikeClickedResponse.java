package com.example.backend.business.web.like.presentation.dto.response;

import com.example.backend.business.core.like.entity.LikeClicked;

public class LikeClickedResponse {

    private final LikeClicked clicked;

    private LikeClickedResponse(Integer exist) {
        this.clicked = LikeClicked.checkClick(exist);
    }

    private LikeClickedResponse(LikeClicked likeClicked) {
        this.clicked = likeClicked;
    }

    public static LikeClickedResponse of(Integer result) {
        return new LikeClickedResponse(result);
    }

    public static LikeClickedResponse of(LikeClicked likeClicked) {
        return new LikeClickedResponse(likeClicked);
    }

    public LikeClicked getClicked() {
        return clicked;
    }
}
