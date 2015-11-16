package com.feexon.jyose;

import com.feexon.jyose.handlers.FileNotFound;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by L.x on 15-11-16.
 */
public class Router {
    private Map<String, RequestHandler> handlers = new HashMap<String,RequestHandler>();
    private RequestHandler unknownHandler = new FileNotFound();

    public void draw(String uri, RequestHandler handler) {
        handlers.put(uri, handler);

    }

    public void dispatch(HttpRequest request, HttpResponse response) throws IOException {
        matching(request).handle(request, response);
    }

    private RequestHandler matching(HttpRequest request) {
        RequestHandler handler = handlers.get(request.getRequestURI());
        return handler == null ? unknownHandler : handler;
    }

    public void setUnknownHandler(RequestHandler handler) {
        unknownHandler = handler;
    }

}
