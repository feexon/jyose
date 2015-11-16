package com.feexon.jyose;

import com.jayway.jsonassert.JsonAssert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class PingPage {
    private HttpURLConnection connection;

    public PingPage(HttpURLConnection connection) {
        this.connection = connection;
    }

    public void renderAsJSON() {
        assertThat(connection.getContentType(), equalTo("application/json"));
    }

    public void hasAttribute(String name, boolean value) throws IOException {
        JsonAssert.with(body()).assertThat(name, equalTo(value));
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
