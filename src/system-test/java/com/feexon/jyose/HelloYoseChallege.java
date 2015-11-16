package com.feexon.jyose;

import com.feexon.jyose.pages.HomePage;
import com.feexon.jyose.pages.RepositoryPage;
import org.junit.Before;
import org.junit.Test;

import static com.feexon.jyose.MediaTypes.TEXT_HTML;
import static org.hamcrest.Matchers.containsString;
import static org.testinfected.hamcrest.dom.DomMatchers.hasText;

/**
 * Created by L.x on 15-11-16.
 */
public class HelloYoseChallege extends YoseChallege {

    private HomePage page;

    @Before
    public void setUp() throws Exception {
        page = application.goHome();
    }

    @Test
    public void renderAsHtmlPage() throws Exception {
        page.renderAs(TEXT_HTML);
    }

    @Test
    public void containingTextHelloYose() throws Exception {
        page.containingText("Hello Yose");
    }

    @Test
    public void containingRepositoryLinkThatHasReadMeElementContainsTheTextWithYoseTheGame() throws Exception {
        page.hasRepositoryLink();
        page.gotoRepositoryPage().hasReadMeElement(containsString("YoseTheGame"));
    }

}
