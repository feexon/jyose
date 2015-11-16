package com.feexon.jyose;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static java.lang.Integer.parseInt;

/**
 * Created by L.x on 15-11-16.
 */
public class SunYoseServer implements YoseServer {

    public static final int DEFAULT_SERVER_PORT = 1314;
    private HttpServer server;

    public SunYoseServer(int serverPort) throws IOException {
        server = createServerListening(serverPort);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String path = exchange.getRequestURI().getPath();
                if (path.equals("/ping")) {
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, 0);
                    OutputStream out = exchange.getResponseBody();
                    out.write("{\"alive\":true}".getBytes());
                    out.close();
                } else {
                    exchange.getResponseHeaders().add("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, 0);
                    OutputStream out = exchange.getResponseBody();
                    out.write("Hello Yose".getBytes());
                    out.close();
                }
                exchange.close();
            }
        });
    }

    private HttpServer createServerListening(int serverPort) throws IOException {
        return HttpServer.create(new InetSocketAddress(serverPort), 0);
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

    @Override
    public int serverPort() {
        return server.getAddress().getPort();
    }

    public void start() throws IOException {
        server.start();
    }
}
