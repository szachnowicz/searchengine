package com.szachnowicz.searchengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class InMemoryReversedIndexTest {

    InMemoryReversedIndex inMemoryReversedIndex = new InMemoryReversedIndex();

    @BeforeEach
    void setUp() {
        inMemoryReversedIndex = new InMemoryReversedIndex();
        DocumentFactory.setId(0);
    }

    @Test
    public void putTest() {

        // given
        Document document = DocumentFactory.createDocument("kot", "pies", "pies", "kaczka");
        // when
        inMemoryReversedIndex.putDocument(document);
        //then
        assertThat(inMemoryReversedIndex.getTotalKeyCount("kot"), equalTo(1));
        assertThat(inMemoryReversedIndex.getTotalKeyCount("pies"), equalTo(1));
        assertThat(inMemoryReversedIndex.getTotalKeyCount("kaczka"), equalTo(1));

    }

    @Test
    public void getDocIndexTestTest() {
        // given
        Document document = DocumentFactory.createDocument("kot", "pies", "pies", "kaczka");
        Document document2 = DocumentFactory.createDocument("koty jedzą kaczki ale psów nie", "pies", "kaczka", "kot");

        addDocumentsToIndex(document, document2);
        // when
        Collection<Integer> allDocIdsOfKey = inMemoryReversedIndex.getAllDocIdsOfKey("kot");
        //then
        assertThat(allDocIdsOfKey, containsInAnyOrder(1, 2));
    }

    @Test
    public void searchForBrownTest() {
        // given
        // id = 1
        Document document1 = DocumentFactory.createDocument("The brown fox jumped over the brown dog.");
        // id = 2
        Document document2 = DocumentFactory.createDocument("The lazy brown dog, sat in the other corner");
        // id = 3
        Document document3 = DocumentFactory.createDocument("“The Red Fox bit the lazy dog!”");

        addDocumentsToIndex(document1, document2, document3);
        // when

        List<ThfdifIndex> brownFounds = inMemoryReversedIndex.getThfidForWordFormAllDocumentsSorted("brown", false);
        //then
        assertThat(brownFounds, hasSize(2));
        assertThat(brownFounds.stream().map(ThfdifIndex::getDocId).collect(Collectors.toList()), containsInAnyOrder(1, 2));
        assertThat(brownFounds.get(0).getTfdIndexValue(), greaterThan(brownFounds.get(1).getTfdIndexValue()));
    }


    @Test
    public void searchForFoxTest() {
        // given
        // id = 1
        Document document1 = DocumentFactory.createDocument("The brown fox jumped over the brown dog.");
        // id = 2
        Document document2 = DocumentFactory.createDocument("The lazy brown dog, sat in the other corner");
        // id = 3
        Document document3 = DocumentFactory.createDocument("“The Red Fox bit the lazy dog!”");

        addDocumentsToIndex(document1, document2, document3);
        // when

        List<ThfdifIndex> brownFounds = inMemoryReversedIndex.getThfidForWordFormAllDocumentsSorted("fox", true);

        //then
        assertThat(brownFounds, hasSize(2));
        assertThat(brownFounds.stream().map(ThfdifIndex::getDocId).collect(Collectors.toList()), containsInAnyOrder(1, 3));
        assertThat(brownFounds.get(0).getTfdIndexValue(), lessThan(brownFounds.get(1).getTfdIndexValue()));

    }

    @Test
    public void searchForDogTest() {
        // given
        // id = 1
        Document document1 = DocumentFactory.createDocument("The brown fox jumped over the brown dog.");
        // id = 2
        Document document2 = DocumentFactory.createDocument("The lazy brown dog, sat in the other corner");
        // id = 3
        Document document3 = DocumentFactory.createDocument("“The Red Fox bit the lazy dog!”");

        addDocumentsToIndex(document1, document2, document3);
        // when

        List<ThfdifIndex> brownFounds = inMemoryReversedIndex.getThfidForWordFormAllDocumentsSorted("dog", true);

        //then
        assertThat(brownFounds, hasSize(3));
        assertThat(brownFounds.stream().map(ThfdifIndex::getDocId).collect(Collectors.toList()), containsInAnyOrder(1, 2, 3));
        assertThat(brownFounds.get(0).getTfdIndexValue(), lessThan(brownFounds.get(1).getTfdIndexValue()));
        assertThat(brownFounds.get(1).getTfdIndexValue(), lessThan(brownFounds.get(2).getTfdIndexValue()));

    }


    private void addDocumentsToIndex(Document... document) {
        for (Document doc : document) {
            inMemoryReversedIndex.putDocument(doc);
        }
    }


}