package com.szachnowicz.searchengine;

class InverseDocumentFrequencyCalculator {

    public static Double calculate(int totalNumberOfDocuments, int wordOccurrenceInDocument) {
        return Math.log10((double) totalNumberOfDocuments / wordOccurrenceInDocument);
    }
}
