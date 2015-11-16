package com.feexon.jyose.sun;

import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.RenderingAction;
import com.feexon.jyose.utils.IO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by L.x on 15-11-16.
 */
public class SunHttpResponse implements HttpResponse {
    private HttpExchange exchange;
    private int status = SC_OK;

    public SunHttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public void setContentType(String contentType) {
        exchange.getResponseHeaders().add("Content-Type", contentType);
    }

    @Override
    public void setStatus(int status) throws IOException {
        this.status = status;
    }

    @Override
    public void render(RenderingAction action) throws IOException {
        exchange.sendResponseHeaders(status, 0);
        OutputStream out = exchange.getResponseBody();
        try {
            action.run(out);
        } finally {
            IO.close(out);
        }
    }

}
