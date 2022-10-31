package com.example.backend.business.web.reaction.presentation;

import com.example.backend.business.web.reaction.presentation.dto.response.ReactionsResponse;
import com.example.backend.common.mapper.api.EnumMapper;
import com.example.backend.common.mapper.api.EnumMapperValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.backend.common.mapper.keys.CacheKey.COMMENT_REACTIONS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reactions")
public class ReactionQueryController {

    public final EnumMapper enumMapper;

    @GetMapping("/comment-reactions")
    public ResponseEntity<ReactionsResponse> findCommentReactions() {

        List<EnumMapperValue> reactions = enumMapper.getValues(
                COMMENT_REACTIONS
        );

        return ResponseEntity.ok(ReactionsResponse.of(reactions));
    }
}
