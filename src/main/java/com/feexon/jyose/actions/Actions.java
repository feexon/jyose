package com.feexon.jyose.actions;

import com.feexon.jyose.RenderingAction;
import com.feexon.jyose.handlers.Challeges;
import com.feexon.jyose.utils.IO;

import java.io.*;
import java.net.URL;

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


    public static RenderingAction from(final URL resource) throws IOException {
        final InputStream in = resource.openStream();
        try {
            final ByteArrayOutputStream content = new ByteArrayOutputStream();
            IO.copy(in, content);
            return new RenderingAction() {
                @Override
                public void run(OutputStream out) throws IOException {
                    content.writeTo(out);
                }
            };
        } finally {
            IO.close(in);
        }
    }

    public static RenderingAction withResource(String uri) {
        URL resource = Challeges.class.getResource(uri);
        if(resource==null){
            throw new RuntimeException(new FileNotFoundException(uri));
        }
        try {
            return from(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
