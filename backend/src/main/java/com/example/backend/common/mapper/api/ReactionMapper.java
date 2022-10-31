package com.example.backend.common.mapper.api;

import java.util.List;

public interface ReactionMapper extends EnumMapperType {
    List<? extends ReactionMapper> getReactions();
}
