package com.feexon.jyose.pages;

import org.hamcrest.Matcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class Page {
    public static final String TEXT_HTML = "text/html";
    public static final String APPLICATION_JSON = "application/json";
    protected HttpURLConnection connection;

    public Page(HttpURLConnection connection) {
        this.connection = connection;
    }

    protected String body() throws IOException {
        InputStream in = connection.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        return out.toString();
    }

    protected void assertContentType(Matcher<String> matcher) {
        assertThat(connection.getContentType(), matcher);
    }
}
