package com.example.backend.data.common;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.reaction.entity.CommentReactionType;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.common.mapper.api.EnumMapperValue;

import java.util.LinkedList;
import java.util.List;

public class MapperTestData {

    public static List<EnumMapperValue> getDistrictsTestData() {
        List<EnumMapperValue> values = new LinkedList<>();
        values.add(new EnumMapperValue(District.SEOUL));
        values.add(new EnumMapperValue(District.BUSAN));
        values.add(new EnumMapperValue(District.ULSAN));
        values.add(new EnumMapperValue(District.DAEGU));
        values.add(new EnumMapperValue(District.DAEJEON));
        values.add(new EnumMapperValue(District.INCHEON));
        return values;
    }

    public static List<EnumMapperValue> getProgressOfStudies() {
        List<EnumMapperValue> values = new LinkedList<>();
        values.add(new EnumMapperValue(ProgressOfStudy.BOTH));
        values.add(new EnumMapperValue(ProgressOfStudy.ONLINE));
        values.add(new EnumMapperValue(ProgressOfStudy.OFFLINE));
        return values;
    }

    public static List<EnumMapperValue> getCommentReactions() {
        List<EnumMapperValue> values = new LinkedList<>();
        values.add(new EnumMapperValue(CommentReactionType.LIKE));
        values.add(new EnumMapperValue(CommentReactionType.HATE));
        values.add(new EnumMapperValue(CommentReactionType.SMILE));
        values.add(new EnumMapperValue(CommentReactionType.GROOMY));
        values.add(new EnumMapperValue(CommentReactionType.SHINE));
        values.add(new EnumMapperValue(CommentReactionType.HEART));
        values.add(new EnumMapperValue(CommentReactionType.ROCKET));
        values.add(new EnumMapperValue(CommentReactionType.EYES));
        return values;
    }
}
