package com.feexon.jyose;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by L.x on 15-11-16.
 */
public interface RenderingAction {
    void run(OutputStream out) throws IOException;
}
