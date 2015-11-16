package com.feexon.jyose;

import com.feexon.jyose.pages.PingPage;
import org.junit.Test;

import static com.feexon.jyose.MimeTypes.APPLICATION_JSON;

/**
 * Created by L.x on 15-11-16.
 */
public class PingChallege extends YoseChallege {

    @Test
    public void renderAsJSONResponse() throws Exception {
        PingPage page = application.ping();
        page.renderAs(APPLICATION_JSON);
    }

    @Test
    public void respondWithAliveAttribute() throws Exception {
        PingPage page = application.ping();
        page.hasAttribute("alive",true);
    }

}
