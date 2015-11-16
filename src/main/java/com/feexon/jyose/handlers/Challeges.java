package com.feexon.jyose.handlers;

import com.feexon.jyose.RenderingAction;
import com.feexon.jyose.RequestHandler;

import static com.feexon.jyose.MediaTypes.APPLICATION_JSON;
import static com.feexon.jyose.MediaTypes.TEXT_HTML;
import static com.feexon.jyose.actions.Actions.withResource;
import static com.feexon.jyose.actions.Actions.withText;

/**
 * Created by L.x on 15-11-16.
 */
public class Challeges {

    public static RequestHandler ping() {
        return renderAs(APPLICATION_JSON, withResource("/com/feexon/jyose/ping.json"));
    }

    public static RequestHandler helloYose() {
        return renderAs(TEXT_HTML, withResource("/com/feexon/jyose/index.html"));
    }

    public static RequestHandler renderAs(String contentType, RenderingAction action) {
        return new DefaultHandler(contentType, action);
    }
}
