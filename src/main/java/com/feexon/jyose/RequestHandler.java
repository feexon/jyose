package com.feexon.jyose;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public interface RequestHandler {
    void handle(HttpRequest request, HttpResponse response) throws IOException;
}
