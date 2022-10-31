package com.example.backend.common.mapper.api;

import java.io.Serializable;

public class EnumMapperValue implements EnumMapperType, Serializable {

    private int id;
    private String type;
    private String value;

    public EnumMapperValue(EnumMapperType emojiMapperType) {
        this.id = emojiMapperType.getId();
        this.type = emojiMapperType.getType();
        this.value = emojiMapperType.getValue();
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
