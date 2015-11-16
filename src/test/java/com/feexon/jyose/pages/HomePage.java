package com.feexon.jyose.pages;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class HomePage extends Page {

    public HomePage(HttpURLConnection connection) {
        super(connection);
    }

    public void renderAsHtml() {
        assertContentType(equalTo(TEXT_HTML));
    }


    public void containingText(String text) throws IOException {
        assertThat(body(), containsString(text));
    }

}
