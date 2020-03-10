package com.szachnowicz.searchengine;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@EqualsAndHashCode
class Document {
    private final int id;
    private final List<String> words;
    private final Map<String, Double> termFrequencyIndex;
}
