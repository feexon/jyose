package com.feexon.jyose.sun;

import com.feexon.jyose.HttpRequest;
import com.sun.net.httpserver.HttpExchange;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by L.x on 15-11-16.
 */
public class SunHttpRequestTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void requestURI() throws Exception {
        HttpRequest request = new SunHttpRequest(requestAs("http://localhost/ping"));

        assertThat(request.getRequestURI(), equalTo("/ping"));
    }

    private HttpExchange requestAs(final String uri) throws URISyntaxException {
        final HttpExchange exchange = context.mock(HttpExchange.class);
        context.checking(new Expectations() {{
            allowing(exchange).getRequestURI();
            will(returnValue(new URI(uri)));
        }});
        return exchange;
    }
}