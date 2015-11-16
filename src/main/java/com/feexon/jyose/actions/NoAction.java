package com.feexon.jyose.actions;

import com.feexon.jyose.RenderingAction;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by L.x on 15-11-17.
 */
public class NoAction implements RenderingAction {

    private static final RenderingAction INSTANCE = new NoAction();

    private NoAction() {
    }

    public static RenderingAction instance() {
        return INSTANCE;
    }

    @Override
    public void run(OutputStream out) throws IOException {

    }
}
