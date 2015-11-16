package com.feexon.jyose;

import com.feexon.jyose.pages.HomePage;
import com.feexon.jyose.pages.Page;
import org.junit.Test;

import static com.feexon.jyose.pages.Page.TEXT_HTML;

/**
 * Created by L.x on 15-11-16.
 */
public class HelloYoseChallege extends YoseChallege {

    @Test
    public void renderAsHtmlPage() throws Exception {
        HomePage page = application.goHome();
        page.renderAs(TEXT_HTML);
    }

    @Test
    public void containingTextHelloYose() throws Exception {
        HomePage page = application.goHome();
        page.containingText("Hello Yose");
    }
}
