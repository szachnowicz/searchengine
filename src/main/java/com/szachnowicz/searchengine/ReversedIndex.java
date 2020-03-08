package com.szachnowicz.searchengine;

import java.util.*;


class ReversedIndex implements InMemoryIndex {

    Collection<Document> list = new HashSet<>();
    Map<String, Set<Integer>> wordsIndexes = new HashMap<>();

    public void putKey(Document document) {
        list.add(document);
        document.getWords()
                .forEach(s -> wordsIndexes
                        .computeIfAbsent(s, k -> new HashSet<>()).add(document.getId()));
    }

    public Collection<Integer> getAllDocIdsOfKey(String word) {
        return wordsIndexes.getOrDefault(word, Collections.EMPTY_SET);
    }


    public int getTotalKeyCount(String word) {
        return wordsIndexes.getOrDefault(word, new HashSet<>()).size();
    }
}
