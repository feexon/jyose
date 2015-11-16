package com.feexon.jyose.utils;

import java.io.*;
import java.net.URL;

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

    public static String toString(URL url) throws IOException {
        InputStream in = url.openStream();
        try {
            return toString(in);
        } finally {
            close(in);
        }
    }

    public static String toString(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        return out.toString();
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
        for (int n; (n = in.read(buff)) != -1; ) {
            out.write(buff, 0, n);
        }
    }
}
