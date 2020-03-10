package com.szachnowicz.searchengine;

import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentFactory {
    @Setter
    private static int id = 0;

    public static Document createDocument(String... stringDocInput) {
        List<String> words = Arrays
                .stream(stringDocInput)
                .map(s -> s.trim().split("\\W+"))
                .flatMap(Arrays::stream)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        Map<String, Double> termFrequencyIndex = FrequencyIndexCalculator.calculateFrequencyIndex(words, words.size());

        return Document.builder()
                .id(++id)
                .words(words)
                .termFrequencyIndex(termFrequencyIndex)
                .build();

    }

}

