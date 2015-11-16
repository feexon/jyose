package com.feexon.jyose.pages;

import com.jayway.jsonassert.JsonAssert;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class PingPage extends Page{

    public PingPage(HttpURLConnection connection) {
        super(connection);
    }

    public void hasAttribute(String name, boolean value) throws IOException {
        JsonAssert.with(asText()).assertThat(name, equalTo(value));
    }

}
