package com.feexon.jyose.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by L.x on 15-11-16.
 */
public class IO {

    public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
        for (int n; (n = in.read(buff)) != -1; ) {
            out.write(buff, 0, n);
        }
    }
}
