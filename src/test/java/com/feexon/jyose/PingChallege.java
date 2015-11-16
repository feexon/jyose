package com.feexon.jyose;

import com.feexon.jyose.ApplicationRunner;
import com.feexon.jyose.pages.PingPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by L.x on 15-11-16.
 */
public class PingChallege extends YoseChallege {

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
