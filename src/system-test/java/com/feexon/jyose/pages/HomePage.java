package com.feexon.jyose.pages;

import com.feexon.jyose.supports.Network;
import com.threelevers.css.Selector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.feexon.jyose.supports.Network.openConnection;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasText;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

/**
 * Created by L.x on 15-11-16.
 */
public class HomePage extends Page {

    public static final String REPOSITORY_LINK_ID = "a#repository-link";

    public HomePage(HttpURLConnection connection) {
        super(connection);
    }


    public void containingText(String text) throws IOException {
        assertThat(asHtml(), hasText(containsString(text)));
    }

    public void hasRepositoryLink() throws IOException {
        hasElement(REPOSITORY_LINK_ID);
    }

    public RepositoryPage gotoRepositoryPage() throws IOException {
        String location = Selector.from(asHtml()).select(REPOSITORY_LINK_ID).iterator().next().getAttribute("href");
        return new RepositoryPage(openConnection(new URL(location)));
    }
}
