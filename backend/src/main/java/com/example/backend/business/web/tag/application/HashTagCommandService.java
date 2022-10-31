package com.example.backend.business.web.tag.application;

import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.infrastructure.query.HashTagQueryDslQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HashTagCommandService {

    private final HashTagQueryDslQueryRepository hashTagQueryDslQueryRepository;

    @Transactional
    public HashTag save(HashTag hashTag) {
        return hashTagQueryDslQueryRepository.save(hashTag);
    }
}
