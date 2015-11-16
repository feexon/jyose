package com.feexon.jyose;

import com.feexon.jyose.pages.HomePage;
import org.junit.Test;

/**
 * Created by L.x on 15-11-16.
 */
public class HelloYoseChallege extends YoseChallege {

    @Test
    public void renderAsHtmlPage() throws Exception {
        HomePage page = application.goHome();
        page.renderAsHtml();
    }

    @Test
    public void containingTextHelloYose() throws Exception {
        HomePage page = application.goHome();
        page.containingText("Hello Yose");
    }
}
