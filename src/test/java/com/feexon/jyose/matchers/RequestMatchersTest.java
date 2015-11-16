package com.feexon.jyose.matchers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static com.feexon.jyose.matchers.Matchers.endsWith;
import static com.feexon.jyose.matchers.Matchers.equal;
import static com.feexon.jyose.matchers.Matchers.path;
import static org.junit.Assert.*;

/**
 * Created by L.x on 15-11-16.
 */
public class RequestMatchersTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void pathEqual() throws Exception {
        Matcher<HttpRequest> matcher = path(equal("/ping"));

        assertTrue(matcher.matches(requestAs("/ping")));
        assertFalse(matcher.matches(requestAs("/echo")));
    }


    @Test
    public void pathEndsWith() throws Exception {
        Matcher<HttpRequest> matcher = path(endsWith(".gif"));

        assertTrue(matcher.matches(requestAs("/fav.gif")));
        assertTrue(matcher.matches(requestAs("/images/fav.gif")));
        assertFalse(matcher.matches(requestAs("/images/fav.png")));
    }

    private HttpRequest requestAs(final String uri) {
        final HttpRequest request = context.mock(HttpRequest.class, uri);
        context.checking(new Expectations() {{
            allowing(request).getRequestURI();
            will(returnValue(uri));
        }});
        return request;
    }
}