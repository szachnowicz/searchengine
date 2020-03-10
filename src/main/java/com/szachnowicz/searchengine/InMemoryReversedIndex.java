package com.szachnowicz.searchengine;

import com.sun.istack.internal.NotNull;

import javax.naming.ldap.SortKey;
import java.util.*;
import java.util.stream.Collectors;


class InMemoryReversedIndex implements ReversedIndex {


    private Collection<Document> documentCollection = new HashSet<>();
    private Map<String, Set<Integer>> wordsInDocumentsIndexes = new HashMap<>();
    private Map<String, Double> wordsInverseDocumentFrequencyValueMap = new HashMap<>();

    @Override
    public void putDocument(Document document) {
        documentCollection.add(document);
        processAddedDocument(document);
    }

    private void processAddedDocument(Document document) {
        document.getWords()
                .forEach(s -> wordsInDocumentsIndexes
                        .computeIfAbsent(s, k -> new HashSet<>()).add(document.getId()));
        wordsInDocumentsIndexes
                .keySet()
                .forEach(this::computeInverseDocumentFrequencyMap);
    }

    private void computeInverseDocumentFrequencyMap(String word) {
        wordsInverseDocumentFrequencyValueMap.computeIfAbsent(word.toLowerCase(), k ->
                InverseDocumentFrequencyCalculator.calculate(wordsInDocumentsIndexes.keySet().size(),
                        wordsInDocumentsIndexes.get(word).size()));
    }

    @Override
    public Collection<Integer> getAllDocIdsOfKey(String word) {
        return wordsInDocumentsIndexes.getOrDefault(word.toLowerCase(), Collections.EMPTY_SET);
    }

    @Override
    public int getTotalKeyCount(String word) {
        return wordsInDocumentsIndexes.getOrDefault(word.toLowerCase(), new HashSet<>()).size();
    }

    @Override
    public List<ThfdifIndex> getThfidForWordFormAllDocuments(@NotNull String word) {
        final String wordLc = word.toLowerCase();
        //all documents where word has been found
        Set<Integer> documentIdList = wordsInDocumentsIndexes.get(wordLc);
        Double wordFrequencyInAllDocuments = wordsInverseDocumentFrequencyValueMap.get(wordLc);
        List<Document> allDocumentsWithWord = documentCollection.stream().filter(d -> documentIdList.contains(d.getId())).collect(Collectors.toList());


        return allDocumentsWithWord.stream().map(doc ->
        {
            Double wordFrequency = doc.getTermFrequencyIndex().get(wordLc);
            Double tfdIndexValue = wordFrequencyInAllDocuments * wordFrequency;

            return ThfdifIndex.builder()
                    .docId(doc.getId())
                    .tfdIndexValue(tfdIndexValue)
                    .word(wordLc)
                    .build();
        }).collect(Collectors.toList());

    }

    @Override
    public List<ThfdifIndex> getThfidForWordFormAllDocumentsSorted(String word, boolean descSort) {
        Comparator<ThfdifIndex> comparingDesc = descSort ? Comparator.comparing(ThfdifIndex::getTfdIndexValue)
                : Comparator.comparing(ThfdifIndex::getTfdIndexValue).reversed();
        return getThfidForWordFormAllDocuments(word).stream().sorted(comparingDesc).collect(Collectors.toList());
    }


}
