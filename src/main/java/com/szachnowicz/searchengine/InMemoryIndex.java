package com.szachnowicz.searchengine;

import java.util.Collection;

public interface InMemoryIndex {
    void putKey(Document word);

    Collection<Integer> getAllDocIdsOfKey(String word);

    int getTotalKeyCount(String word);

}
