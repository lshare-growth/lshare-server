package com.example.backend.lshare.common.mapper;

import com.example.backend.business.core.reaction.entity.CommentReactionType;
import com.example.backend.common.mapper.api.EnumMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EnumMapper 조회 테스트")
class EnumMapperTest {

    private EnumMapper createEnumMapper() {
        return new EnumMapper();
    }

    @Nested
    @DisplayName("EnumMapper를 조회할때")
    class EnumMapperReadTest {

        @DisplayName("key가 null또는 등록되지 않았을 경우 빈 리스트가 조회된다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"NOT_REGISTERED"})
        void 잘못된_키값_조회_테스트(String parameter) {
            EnumMapper enumMapper = createEnumMapper();
            enumMapper.put("Emotions", CommentReactionType.class);

            assertThat(enumMapper.getValues(parameter)).isEqualTo(Collections.emptyList());
        }

        @DisplayName("등록된 Reaction타입을 넣으면 해당 값들이 조회된다.")
        @ParameterizedTest
        @ValueSource(strings = {"Emotions"})
        void 등록된_키값_조회_테스트(String parameter) {
            EnumMapper enumMapper = createEnumMapper();
            enumMapper.put("Emotions", CommentReactionType.class);

            List<String> commentReactions = CommentReactionType.getCommentReactionTypesAsString();

            assertThat(enumMapper.getValues(parameter).size()).isEqualTo(commentReactions.size());
        }
    }
}

