package com.example.backend.data.tag;

import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.entity.values.TagName;

public class HashTagTestData {

    public static HashTag createHashTag() {
        return HashTag.createHashTag(TagName.from("Backend"));
    }
}
