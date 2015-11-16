package com.feexon.jyose.supports;

import com.feexon.jyose.YoseServer;
import com.feexon.jyose.pages.HomePage;
import com.feexon.jyose.pages.PingPage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.format;

/**
 * Created by L.x on 15-11-16.
 */
public class ApplicationRunner {

    public static final int MILLIS_TIMEOUT = 3000;
    public static final String URL_FORMAT = "http://localhost:%d/%s";
    private YoseServer server;

    public void start() throws IOException {
        server = LifecycleYoseServer.instance();
        server.start();
    }

    public void close() {
        server.close();
    }

    public HomePage goHome() throws IOException {
        return new HomePage(connect("/"));
    }

    public PingPage ping() throws IOException {
        return new PingPage(connect("/ping"));
    }

    private HttpURLConnection connect(String path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url(path)).openConnection();
        connection.setConnectTimeout(MILLIS_TIMEOUT);
        connection.setReadTimeout(MILLIS_TIMEOUT);
        connection.connect();
        return connection;
    }

    private String url(String path) {
        return format(URL_FORMAT, server.serverPort(), path(path));
    }

    private String path(String path) {
        return path.startsWith("/") ? path.substring(1) : path;
    }

}
