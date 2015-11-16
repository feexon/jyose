package com.feexon.jyose;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class HomePage {
    private HttpURLConnection connection;

    public HomePage(HttpURLConnection connection) {
        this.connection = connection;
    }

    public void renderAsHtml() {
        assertThat(connection.getContentType(), equalTo("text/html"));
    }

    public void containing(String text) throws IOException {
        assertThat(body(), containsString(text));
    }

    private String body() throws IOException {
        InputStream in = connection.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        return out.toString();
    }
}
