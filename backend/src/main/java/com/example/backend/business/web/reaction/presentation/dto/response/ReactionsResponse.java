package com.example.backend.business.web.reaction.presentation.dto.response;

import com.example.backend.common.mapper.api.EnumMapperValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class ReactionsResponse implements Serializable {

    private final List<EnumMapperValue> reactions;

    private ReactionsResponse(List<EnumMapperValue> reactions) {
        this.reactions = reactions;
    }

    public static ReactionsResponse of(List<EnumMapperValue> reactions) {
        return new ReactionsResponse(reactions);
    }

    public List<EnumMapperValue> getReactions() {
        return reactions;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
