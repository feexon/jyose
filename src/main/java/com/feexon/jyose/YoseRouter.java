package com.feexon.jyose;

import com.feexon.jyose.Router;
import com.feexon.jyose.actions.Actions;

import java.io.IOException;

import static com.feexon.jyose.actions.Actions.withResource;
import static com.feexon.jyose.handlers.Challeges.helloYose;
import static com.feexon.jyose.handlers.Challeges.ping;
import static com.feexon.jyose.handlers.Challeges.renderAs;

/**
 * Created by L.x on 15-11-16.
 */
public class YoseRouter extends Router {
    public YoseRouter() {
        draw("/", helloYose());
        draw("/ping", ping());
        draw("/css/index.css", renderAs("text/css", withResource("/com/feexon/jyose/css/images/blacktocat.png")));
        draw("/css/images/blacktocat.png", renderAs("images/png", withResource("/com/feexon/jyose/css/images/blacktocat.png")));
    }
}
