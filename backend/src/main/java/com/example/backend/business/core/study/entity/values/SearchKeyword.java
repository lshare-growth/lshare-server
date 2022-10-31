package com.example.backend.business.core.study.entity.values;

import java.util.LinkedList;
import java.util.List;

public class SearchKeyword {

    private List<String> keywords;

    public SearchKeyword(String... keywords) {
        this.keywords = addKeywords(keywords);
    }

    public List<String> addKeywords(String... keywords) {
        List<String> searchKeywords = new LinkedList<>();
        for (String keyword : keywords) {
            searchKeywords.add(String.valueOf(keyword));
        }
        return searchKeywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
