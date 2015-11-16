package com.feexon.jyose;

import com.feexon.jyose.pages.HomePage;
import org.junit.Test;

import static com.feexon.jyose.MediaTypes.TEXT_HTML;
import static org.hamcrest.Matchers.containsString;
import static org.testinfected.hamcrest.dom.DomMatchers.hasText;

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

    @Test
    public void containingRepositoryLinkThatHasReadMeElementContainsTheTextWithYoseTheGame() throws Exception {
        HomePage page = application.goHome();

        page.hasRepositoryLink();
        page.gotoRepositoryPage().hasElement("#readme", hasText(containsString("YoseTheGame")));
    }

}
