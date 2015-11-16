package com.feexon.jyose.sun;

import com.feexon.jyose.Router;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public class HttpTransport implements HttpHandler {
    private final Router router;

    public HttpTransport(Router router) {
        this.router = router;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            router.dispatch(new SunHttpRequest(exchange), new SunHttpResponse(exchange));
        } finally {
            exchange.close();
        }
    }
}
