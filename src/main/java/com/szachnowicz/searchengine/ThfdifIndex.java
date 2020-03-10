package com.szachnowicz.searchengine;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class ThfdifIndex {
    private int docId;
    private String word;
    private Double tfdIndexValue;


}
