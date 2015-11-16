package com.feexon.jyose;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public interface HttpResponse {
    int SC_OK = 200;
    int SC_FILE_NOT_FOUND = 404;

    void setContentType(String contentType);

    void setStatus(int status) throws IOException;

    void render(RenderingAction action) throws IOException;
}
