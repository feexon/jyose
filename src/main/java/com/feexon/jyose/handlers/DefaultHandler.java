package com.feexon.jyose.handlers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.RenderingAction;
import com.feexon.jyose.RequestHandler;
import com.feexon.jyose.actions.NoAction;

import java.io.IOException;

import static com.feexon.jyose.HttpResponse.SC_FILE_NOT_FOUND;
import static com.feexon.jyose.MimeTypes.TEXT_HTML;

/**
 * Created by L.x on 15-11-16.
 */
public class DefaultHandler implements RequestHandler {
    private final String contentType;
    private final int status;
    private final RenderingAction action;

    public DefaultHandler(String contentType, RenderingAction action) {
        this(contentType, HttpResponse.SC_OK, action);
    }

    public DefaultHandler(String contentType, int status, RenderingAction action) {
        this.contentType = contentType;
        this.status = status;
        this.action = action;
    }

    public static DefaultHandler fileNotFound() {
        return new DefaultHandler(TEXT_HTML, SC_FILE_NOT_FOUND, NoAction.instance());
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.render(action);
    }
}
