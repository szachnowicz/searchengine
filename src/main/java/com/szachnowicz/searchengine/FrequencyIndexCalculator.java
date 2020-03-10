package com.szachnowicz.searchengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FrequencyIndexCalculator {
    static Map<String, Double> calculateFrequencyIndex(List<String> words, int sizeOfDocument) {
        Map<String, Double> termFrequencyIndex = new HashMap<>();
        for (String word : words) {
            long wordsInDocument = words.stream().filter(word::equals).count();
            termFrequencyIndex.put(word, (double) wordsInDocument / sizeOfDocument);
        }
        return termFrequencyIndex;
    }
}
