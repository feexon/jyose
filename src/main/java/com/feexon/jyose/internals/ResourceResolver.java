package com.feexon.jyose.internals;

import java.io.IOException;
import java.net.URL;

/**
 * Created by L.x on 15-11-17.
 */
public interface ResourceResolver {
    URL getResourceURL(String uri) throws IOException;
}
