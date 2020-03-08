package com.szachnowicz.searchengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ReversedIndexTest {

    ReversedIndex reversedIndex = new ReversedIndex();

    @BeforeEach
    void setUp() {
        reversedIndex = new ReversedIndex();
    }

    @Test
    public void putTest() {

        // given
        Document document = Document.builder()
                .id(1)
                .words(Arrays.asList("kot", "pies", "pies", "kaczka"))
                .build();
        // when
        reversedIndex.putKey(document);
        //then
        assertThat(reversedIndex.getTotalKeyCount("kot"), equalTo(1));
        assertThat(reversedIndex.getTotalKeyCount("pies"), equalTo(1));
        assertThat(reversedIndex.getTotalKeyCount("kaczka"), equalTo(1));

    }

    @Test
    public void getDocIndexTestTest() {
        // given
        Document document = Document.builder()
                .id(1)
                .words(Arrays.asList("kot", "pies", "kaczka"))
                .build();
        reversedIndex.putKey(document);

        Document document2 = Document.builder()
                .id(2)
                .words(Arrays.asList("koty jedzą kaczki ale psów nie", "pies", "kaczka", "kot"))
                .build();

        reversedIndex.putKey(document2);

        // when
        Collection<Integer> allDocIdsOfKey = reversedIndex.getAllDocIdsOfKey("kot");
        //then

        assertThat(allDocIdsOfKey, containsInAnyOrder(1, 2));


    }
}