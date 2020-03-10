package com.szachnowicz.searchengine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyIndexCalculatorTest {

    @Test
    public void basicIndexTest() {
        // given
        List<String> list = Arrays.asList("kot", "smok", "dog", "smok", "kaczka");
        // when
        Map<String, Double> indexMap = FrequencyIndexCalculator.calculateFrequencyIndex(list, list.size());
        //then
        assertEquals((double) 2 / 5, indexMap.get("smok"));

    }

}