package com.feexon.jyose;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public interface YoseServer {
    int serverPort();

    void start() throws IOException;

    void close();

    void stopAtShutdown();
}
