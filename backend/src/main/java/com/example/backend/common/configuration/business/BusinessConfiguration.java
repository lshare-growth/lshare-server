package com.example.backend.common.configuration.business;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.reaction.entity.CommentReactionType;
import com.example.backend.business.core.reaction.entity.DailyPlanReactionType;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.common.mapper.api.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.backend.common.mapper.keys.CacheKey.COMMENT_REACTIONS;
import static com.example.backend.common.mapper.keys.CacheKey.DAILY_PLAN_REACTIONS;
import static com.example.backend.common.mapper.keys.CacheKey.DISTRICTS;
import static com.example.backend.common.mapper.keys.CacheKey.PROGRESS_OF_STUDY;

@Configuration
public class BusinessConfiguration {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put(COMMENT_REACTIONS, CommentReactionType.class);
        enumMapper.put(DAILY_PLAN_REACTIONS, DailyPlanReactionType.class);
        enumMapper.put(DISTRICTS, District.class);
        enumMapper.put(PROGRESS_OF_STUDY, ProgressOfStudy.class);
        return enumMapper;
    }
}
