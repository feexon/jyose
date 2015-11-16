package com.feexon.jyose;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by L.x on 15-11-16.
 */
public class HelloYoseChallege {
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
    public void renderAsHtmlPage() throws Exception {
        HomePage page = application.goHome();
        page.renderAsHtml();
    }

    @Test
    public void pageContainingHelloYose() throws Exception {
        HomePage page = application.goHome();
        page.containing("Hello Yose");
    }
}
