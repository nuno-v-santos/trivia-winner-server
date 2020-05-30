package com.triviawinner;

import org.immutables.value.Value;

@Value.Immutable
public interface SearchResult {

    String title();

    String description();

    String link();
}
