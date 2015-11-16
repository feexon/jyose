package com.feexon.jyose.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by L.x on 15-11-16.
 */
public class IO {
    public static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ignored) {
            }
        }
    }
}
