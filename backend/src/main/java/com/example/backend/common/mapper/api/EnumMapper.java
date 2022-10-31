package com.example.backend.common.mapper.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class EnumMapper {

    private final Map<String, List<EnumMapperValue>> factory = new LinkedHashMap<>();
    private final Map<String, String> keys = new HashMap<>();

    public EnumMapper() {
    }

    public void put(String key, Class<? extends EnumMapperType> clazz) {
        factory.put(key, toEmojiValues(clazz));
        keys.put(key, key);
    }

    private List<EnumMapperValue> toEmojiValues(Class<? extends EnumMapperType> clazz) {
        return Arrays.stream(clazz.getEnumConstants())
                .map(EnumMapperValue::new)
                .collect(toList());
    }

    public List<EnumMapperValue> getValues(String key) {
        if (key == null || !keys.containsKey(key)) {
            return Collections.emptyList();
        }
        return factory.get(key);
    }
}
