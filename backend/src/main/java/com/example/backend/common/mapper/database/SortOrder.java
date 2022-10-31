package com.example.backend.common.mapper.database;

import com.example.backend.business.core.study.entity.QStudy;
import com.example.backend.common.mapper.api.EnumMapperType;
import com.querydsl.core.types.OrderSpecifier;

import java.util.Arrays;
import java.util.Objects;

public enum SortOrder implements EnumMapperType {

    LIKE(1, "LIKE", "좋아요 순", QStudy.study.likeCount.likeCount.desc()),
    COMMENT(2, "COMMENT", "댓글 순", QStudy.study.commentCount.commentCount.desc()),
    RECENTLY(3, "RECENTLY", "최신순 정렬", OrderByNull.DEFAULT);

    private final int id;
    private final String type;
    private final String value;
    private final OrderSpecifier order;

    SortOrder(int id, String type, String value, OrderSpecifier order) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.order = order;
    }

    public OrderSpecifier getOrder() {
        if (this.equals(RECENTLY)) {
            return QStudy.study.studyId.desc();
        }
        return order;
    }

    public static SortOrder findByType(String sortOrder) {
        if (Objects.isNull(sortOrder)) {
            return SortOrder.RECENTLY;
        }
        return Arrays.stream(values())
                .filter(order -> order.type.equals(sortOrder))
                .findAny()
                .orElseGet(() -> RECENTLY);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}
