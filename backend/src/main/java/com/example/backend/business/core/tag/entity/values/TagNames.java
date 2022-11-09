package com.example.backend.business.core.tag.entity.values;

import com.example.backend.business.core.common.ErrorField;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TagNames {

    private final Set<TagName> tagNames;

    public TagNames(List<String> tagNames) {
        validateTagNames(tagNames);
        this.tagNames = convertToSet(tagNames);
    }

    private void validateTagNames(List<String> tagNames) {
        if (Objects.isNull(tagNames) || tagNames.isEmpty()) {
            throw new IllegalArgumentException("해시태그 값을 입력해주세요.", ErrorField.of("tagNames", tagNames));
        }
        if (tagNames.size() > 8) {
            throw new IllegalArgumentException("해시태그는 최대 8개까지 입력할 수 있습니다.", ErrorField.of("tagNames-length", tagNames.size()));
        }
    }

    private Set<TagName> convertToSet(List<String> tagNames) {
        return tagNames.stream()
                .map(TagName::from)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static TagNames from(List<String> tagNames) {
        return new TagNames(tagNames);
    }

    public Set<TagName> getTagNames() {
        return tagNames;
    }

    @Override
    public String toString() {
        return String.format("태그 리스트: %s", tagNames);
    }
}
