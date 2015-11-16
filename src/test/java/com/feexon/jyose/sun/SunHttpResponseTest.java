package com.feexon.jyose.sun;

import com.feexon.jyose.RenderingAction;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.OutputStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-16.
 */
public class SunHttpResponseTest {
    private final Headers headers = new Headers();
    @Mock
    private HttpExchange exchange;

    @Mock
    private RenderingAction action;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    private SunHttpResponse response;

    @Before
    public void setUp() throws Exception {
        context.checking(new Expectations() {{
            allowing(exchange).getResponseHeaders();
            will(returnValue(headers));
        }});
        response = new SunHttpResponse(exchange);
    }

    @Test
    public void setStatusBeforeRendering() throws Exception {
        response.setStatus(200);

        context.checking(new Expectations() {{
            ignoring(exchange).getResponseBody();
            ignoring(action);

            oneOf(exchange).sendResponseHeaders(with(200), with(any(long.class)));
        }});

        response.render(action);
    }

    @Test
    public void setStatus200BeforeRenderingIfStatusWasUnset() throws Exception {
        context.checking(new Expectations() {{
            ignoring(exchange).getResponseBody();
            ignoring(action);

            oneOf(exchange).sendResponseHeaders(with(200), with(any(long.class)));
        }});

        response.render(action);
    }

    @Test
    public void setContentType() throws Exception {
        response.setContentType("text/html");

        assertThat(headers.getFirst("Content-Type"), equalTo("text/html"));
    }

    @Test
    public void renderAndClosingResponseAfterRendering() throws Exception {
        final OutputStream out = context.mock(OutputStream.class);
        context.checking(new Expectations() {{
            allowing(exchange).getResponseBody();
            will(returnValue(out));

            ignoring(exchange).sendResponseHeaders(with(any(int.class)), with(any(long.class)));
        }});

        context.checking(new Expectations() {{
            oneOf(action).run(out);
            oneOf(out).close();
        }});

        response.render(action);
    }


}