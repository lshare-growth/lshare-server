package com.example.backend.data.reaction;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.reaction.entity.CommentReaction;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.data.comment.CommentTestData;

public class CommentReactionTestData {

    public static CommentReaction createCommentReactionTestDataWithId() {
        return CommentReaction.from(
                CommentTestData.createCommentWithId(),
                StudyId.from(1L),
                MemberId.from(1L),
                CommentReactionEmotion.from("✨"));
    }
}
