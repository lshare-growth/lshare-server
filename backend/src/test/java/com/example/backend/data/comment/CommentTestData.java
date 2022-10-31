package com.example.backend.data.comment;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.data.study.StudyTestData;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

public class CommentTestData {

    public static final Long COMMENT_ID = 2L;
    public static final Long COMMENT_PARENT_ID = 1L;

    public static Comment createCommentWithId() {
        Comment newComment = Comment.writeComment(
                MemberTestData.createMemberTestDataWithId(),
                StudyTestData.createStudyTestDataWithId(),
                CommentContent.from("공유해주신 자료 잘봤습니다. 많은 도움 되었습니다."));

        Field idField = ReflectionUtils.findField(Comment.class, "commentId");
        ReflectionUtils.makeAccessible(Objects.requireNonNull(idField));
        ReflectionUtils.setField(Objects.requireNonNull(idField), newComment, COMMENT_ID);
        return newComment;
    }
}
