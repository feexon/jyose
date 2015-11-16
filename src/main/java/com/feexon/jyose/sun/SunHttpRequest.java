package com.feexon.jyose.sun;


import com.feexon.jyose.HttpRequest;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by L.x on 15-11-16.
 */
public class SunHttpRequest implements HttpRequest {
    private HttpExchange exchange;

    public SunHttpRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String getRequestURI() {
        return exchange.getRequestURI().getPath();
    }
}
