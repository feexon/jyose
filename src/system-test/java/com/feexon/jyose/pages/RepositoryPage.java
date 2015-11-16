package com.feexon.jyose.pages;

import org.hamcrest.Matcher;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.testinfected.hamcrest.dom.DomMatchers.hasText;

/**
 * Created by L.x on 15-11-16.
 */
public class RepositoryPage extends Page {
    public RepositoryPage(HttpURLConnection connection) {
        super(connection);
    }

    public void hasReadMeElement(Matcher<String> matcher) throws IOException {
        hasElement("#readme", hasText(matcher));
    }
}
