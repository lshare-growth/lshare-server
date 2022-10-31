package com.example.backend.common.mapper.keys;

public final class CacheKey {

    public static final String HASHTAG_KEY = "#HashTag";
    public static final String PROGRESS_OF_STUDY = "#ProgressOfStudy";
    public static final String DISTRICTS = "#Districts";
    public static final String COMMENT_REACTIONS = "#CommentReactions";
    public static final String DAILY_PLAN_REACTIONS = "#DailyPlanReactions";

    private CacheKey() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }
}
