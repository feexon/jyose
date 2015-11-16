package com.feexon.jyose.pages;

import com.feexon.jyose.MediaTypes;
import org.hamcrest.Matcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static com.feexon.jyose.MediaTypes.APPLICATION_JSON;
import static com.feexon.jyose.MediaTypes.TEXT_HTML;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class Page {
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

    public void renderAs(Matcher<String> matcher) {
        assertThat(connection.getContentType(), matcher);
    }

    public void renderAs(String contentType) {
        renderAs(equalTo(contentType));
    }
}
