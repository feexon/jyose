package com.feexon.jyose.actions;

import com.feexon.jyose.RenderingAction;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by L.x on 15-11-16.
 */
public class Actions {
    public static RenderingAction withText(final String text) {
        return new RenderingAction() {
            @Override
            public void run(OutputStream out) throws IOException {
                out.write(text.getBytes());
            }
        };
    }
}
