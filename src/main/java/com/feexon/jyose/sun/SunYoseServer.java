package com.feexon.jyose.sun;

import com.feexon.jyose.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static java.lang.Integer.parseInt;

/**
 * Created by L.x on 15-11-16.
 */
public class SunYoseServer implements YoseServer {

    public static final int DEFAULT_SERVER_PORT = 1314;
    private HttpServer server;
    private Router router = new YoseRouter();

    public SunYoseServer(int serverPort) throws IOException {
        server = createServerListening(serverPort);
    }

    private HttpServer createServerListening(int serverPort) throws IOException {
        return HttpServer.create(new InetSocketAddress(serverPort), 0);
    }

    public void stopAtShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));
    }

    public void close() {
        server.stop(0);
    }

    public static void main(String... args) throws IOException {
        YoseServer server = new SunYoseServer(serverPort(args));
        server.start();
        server.stopAtShutdown();
    }

    private static int serverPort(String[] args) {
        try {
            return args.length > 0 ? parseInt(args[0]) : DEFAULT_SERVER_PORT;
        } catch (NumberFormatException e) {
            return DEFAULT_SERVER_PORT;
        }
    }

    @Override
    public int serverPort() {
        return server.getAddress().getPort();
    }

    public void start() throws IOException {
        server.createContext("/", new HttpTransport(router));
        server.start();
    }

}
