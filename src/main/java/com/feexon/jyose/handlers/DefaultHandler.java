package com.feexon.jyose.handlers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.RenderingAction;
import com.feexon.jyose.RequestHandler;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public class DefaultHandler implements RequestHandler {
    protected final String contentType;
    protected final RenderingAction action;

    public DefaultHandler(String contentType, RenderingAction action) {
        this.contentType = contentType;
        this.action = action;
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        response.setContentType(contentType);
        response.render(action);
    }
}
