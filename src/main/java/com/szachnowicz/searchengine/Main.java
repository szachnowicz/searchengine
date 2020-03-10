package com.szachnowicz.searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Document> documentList = new ArrayList();
        for (String arg : args) {
            documentList.add(DocumentFactory.createDocument(arg));
        }
        ReversedIndex inMemoryReversedIndex = new InMemoryReversedIndex();
        documentList.forEach(inMemoryReversedIndex::putDocument);

    }
}
