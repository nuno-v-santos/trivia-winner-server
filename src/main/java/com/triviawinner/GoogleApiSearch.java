package com.triviawinner;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.triviawinner.Constants.GOOGLE_API_KEY;
import static com.triviawinner.Constants.GOOGLE_SEARCH_ENGINE_ID;

public class GoogleApiSearch {

    public static Collection<SearchResult> search(final String query) {
        Preconditions.checkNotNull(query);

        try {
            final Customsearch search = new Customsearch.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null)
                    .setApplicationName("Trivia Winner")
                    .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer(GOOGLE_API_KEY))
                    .build();

            final Customsearch.Cse.List list = search.cse().list(query).setCx(GOOGLE_SEARCH_ENGINE_ID);

            final List<Result> searchResults = list.execute().getItems();

            return searchResults.stream()
                    .map(result -> ImmutableSearchResult.builder()
                            .title(result.getTitle())
                            .description(result.getSnippet())
                            .link(result.getLink())
                            .build())
                    .collect(Collectors.toList());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return ImmutableList.of();
        }
    }
}
