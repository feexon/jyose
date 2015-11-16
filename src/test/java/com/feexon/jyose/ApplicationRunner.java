package com.feexon.jyose;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by L.x on 15-11-16.
 */
public class ApplicationRunner {

    public static final int MILLIS_TIMEOUT = 1000;
    private static final int serverPort = 1915;
    private YoseServer server;

    public void start() throws IOException {
        server = new YoseServer(serverPort);
        server.start();
    }

    public void close() {
        server.close();
    }

    public HomePage goHome() throws IOException {
        return new HomePage(connect("http://localhost:" + serverPort));
    }

    private HttpURLConnection connect(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(MILLIS_TIMEOUT);
        connection.setReadTimeout(MILLIS_TIMEOUT);
        connection.connect();
        return connection;
    }
}
