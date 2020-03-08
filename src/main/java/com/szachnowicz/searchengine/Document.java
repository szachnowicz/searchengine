package com.szachnowicz.searchengine;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
class Document {
    final int id;
    final List<String> words;
}
