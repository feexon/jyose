package com.feexon.jyose.supports;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by L.x on 15-11-16.
 */
public class Network {
    private static final int MILLIS_TIMEOUT = 5000;

    public static HttpURLConnection openConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(MILLIS_TIMEOUT);
        connection.setReadTimeout(MILLIS_TIMEOUT);
        connection.connect();
        return connection;
    }
}
