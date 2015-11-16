package com.feexon.jyose.handlers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.RequestHandler;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public class FileNotFound implements RequestHandler {
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        response.setStatus(404);
    }
}
