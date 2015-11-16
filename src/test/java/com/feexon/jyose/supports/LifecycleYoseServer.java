package com.feexon.jyose.supports;

import com.feexon.jyose.YoseServer;
import com.feexon.jyose.SunYoseServer;

import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public class LifecycleYoseServer implements YoseServer {

    private YoseServer server;

    public LifecycleYoseServer(SunYoseServer server) throws IOException {
        this.server = server;
    }

    @Override
    public int serverPort() {
        return server.serverPort();
    }

    private boolean started = false;

    @Override
    public void start() throws IOException {
        if (started) {
            return;
        }
        server.start();
        stopAtShutdown();
        started = true;
    }

    @Override
    public void close() {
    }

    @Override
    public void stopAtShutdown() {
        server.stopAtShutdown();
    }

    private static class LifecycleServerFactory {
        private static final YoseServer INSTANCE;

        static {
            try {
                INSTANCE = new LifecycleYoseServer(new SunYoseServer(1314));
            } catch (IOException e) {
                throw new Error(e);
            }
        }
    }

    public static YoseServer instance() throws IOException {
        return LifecycleServerFactory.INSTANCE;
    }
}
