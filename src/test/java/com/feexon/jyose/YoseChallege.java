package com.feexon.jyose;

import org.junit.After;
import org.junit.Before;

/**
 * Created by L.x on 15-11-16.
 */
abstract public class YoseChallege {
    protected ApplicationRunner application = new ApplicationRunner();

    @Before
    public void startApplication() throws Exception {
        application.start();
    }

    @After
    public void stopApplication() throws Exception {
        application.close();
    }
}
