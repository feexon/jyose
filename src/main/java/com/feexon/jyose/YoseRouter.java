package com.feexon.jyose;

import com.feexon.jyose.handlers.ResourceHandler;

import static com.feexon.jyose.internals.ClasspathResourceResolver.baseOn;
import static com.feexon.jyose.matchers.Matchers.any;

/**
 * Created by L.x on 15-11-16.
 */
public class YoseRouter extends Router {

    public YoseRouter() {
        draw(any(HttpRequest.class), new ResourceHandler(baseOn(YoseServer.class)) {{
            alias("/", "index.html");
            alias("/ping", "ping.json");
        }});
    }

}
