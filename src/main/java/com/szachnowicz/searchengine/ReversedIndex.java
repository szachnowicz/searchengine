package com.szachnowicz.searchengine;

import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.List;

public interface ReversedIndex {
    void putDocument(Document word);

    Collection<Integer> getAllDocIdsOfKey(String word);

    int getTotalKeyCount(String word);

    List<ThfdifIndex> getThfidForWordFormAllDocuments(String word);

    List<ThfdifIndex> getThfidForWordFormAllDocumentsSorted(String word, boolean asc);

}
