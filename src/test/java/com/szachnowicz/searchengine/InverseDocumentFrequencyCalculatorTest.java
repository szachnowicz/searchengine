package com.szachnowicz.searchengine;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InverseDocumentFrequencyCalculatorTest {
    @Test
    public void calculatorBasicTest() {
        // given

        int totalNumberOfDocuments = 10000000;
        int wordOccurrenceInDocument = 1000;
        // when

        Double calculate = InverseDocumentFrequencyCalculator.calculate(totalNumberOfDocuments, wordOccurrenceInDocument);
        //then
        assertEquals(4, calculate);
    }
}