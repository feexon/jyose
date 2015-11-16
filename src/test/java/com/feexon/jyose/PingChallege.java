package com.feexon.jyose;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by L.x on 15-11-16.
 */
public class PingChallege {
    ApplicationRunner application = new ApplicationRunner();

    @Before
    public void startApplication() throws Exception {
        application.start();
    }

    @After
    public void stopApplication() throws Exception {
        application.close();
    }

    @Test
    public void renderAsJSONResponse() throws Exception {
        PingPage page = application.ping();
        page.renderAsJSON();
    }

    @Test
    public void respondWithAliveAttribute() throws Exception {
        PingPage page = application.ping();
        page.hasAttribute("alive",true);
    }

}
