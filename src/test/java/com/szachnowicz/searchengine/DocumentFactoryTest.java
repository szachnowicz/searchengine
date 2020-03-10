package com.szachnowicz.searchengine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DocumentFactoryTest {


    @Test
    public void test() {
        // given
        String[] doc = new String[]{"", "Ala ma kota, kot ma ale", "Will smith will smith will? Will smith."};
        // when
        Document document = DocumentFactory.createDocument(doc);
        //then
        assertEquals(Arrays.asList("ala", "ma", "kota", "kot", "ma", "ale", "will", "smith", "will", "smith", "will", "will", "smith")
                , document.getWords());

            assertEquals((double) 4/13,document.getTermFrequencyIndex().get("will"));


    }
}