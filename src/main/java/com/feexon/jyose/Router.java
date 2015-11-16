package com.feexon.jyose;

import com.feexon.jyose.handlers.DefaultHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.feexon.jyose.matchers.Matchers.equal;
import static com.feexon.jyose.matchers.Matchers.path;

/**
 * Created by L.x on 15-11-16.
 */
public class Router {
    public static final DefaultHandler fileNotFound = DefaultHandler.fileNotFound();
    private Map<Matcher<HttpRequest>, RequestHandler> handlers = new LinkedHashMap<Matcher<HttpRequest>, RequestHandler>();
    private RequestHandler unknownHandler = fileNotFound;

    public void draw(String uri, RequestHandler handler) {
        draw(path(equal(uri)), handler);
    }

    public void draw(Matcher<HttpRequest> matcher, RequestHandler handler) {
        handlers.put(matcher, handler);
    }

    public void dispatch(HttpRequest request, HttpResponse response) throws IOException {
        try {
            matching(request).handle(request, response);
        } catch (FileNotFoundException e) {
            fileNotFound.handle(request, response);
        }
    }

    private RequestHandler matching(HttpRequest request) {
        for (Matcher<HttpRequest> candidate : handlers.keySet()) {
            if (candidate.matches(request)) {
                return handlers.get(candidate);
            }
        }
        return unknownHandler;
    }

    public void setUnknownHandler(RequestHandler handler) {
        unknownHandler = handler;
    }

}
