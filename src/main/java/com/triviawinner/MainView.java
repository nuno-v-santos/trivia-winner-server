package com.triviawinner;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Collection;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    public MainView() {

        TextField question = new TextField();
        question.setLabel("Question");
        question.setPlaceholder("What is ... ?");

        TextField options = new TextField();
        options.setLabel("Options");
        options.setPlaceholder("a,b,c");

        Button submit = new Button("Submit");
        submit.addClickListener(click -> submit(question.getValue(), ImmutableList.of(options.getValue())));

        add(
                new H1("Trivia Winner"),
                question,
                options,
                submit
        );
    }

    private void submit(final String question, final List<String> options) {
        Preconditions.checkNotNull(question);
        Preconditions.checkNotNull(options);

       final Collection<SearchResult> searchResults = GoogleApiSearch.search(question);

        showSearchResults(searchResults);
    }

    private void showSearchResults(final Collection<SearchResult> searchResults) {
        for (final SearchResult result : searchResults) {
            add(
                    new ListItem(result.title()),
                    new ListItem(result.description()),
                    new ListItem(result.link())
            );
        }
    }
}