package com.example.backend.business.core.tag.infrastructure.command.queryutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HashTagQuerySupport {

    private static final Logger log = LoggerFactory.getLogger(HashTagQuerySupport.class);

    private HashTagQuerySupport() {
        log.error("올바르지 않은 방식으로 ApiUtils 생성자를 호출했습니다.");
        throw new AssertionError("올바른 방법으로 생성자를 호출해주세요.");
    }

    public static final String HASH_TAG_ID = "hash_tag_id";
    public static final String TAG_NAME = "tag_name";
    public static final String STUDY_ID = "study_id";
}
