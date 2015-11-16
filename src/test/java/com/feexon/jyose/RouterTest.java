package com.feexon.jyose;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.RequestHandler;
import com.feexon.jyose.Router;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by L.x on 15-11-16.
 */
public class RouterTest {
    @Mock
    private HttpResponse response;
    @Mock
    private HttpRequest request;
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    private RequestHandler handler;
    private final Router router = new Router();

    @Test
    public void routingRequests() throws Exception {
        router.draw("/ping", handler);

        requestUri("/ping");

        expectHandingBy(handler);

        router.dispatch(request, response);
    }

    private void expectHandingBy(final RequestHandler handler) throws Exception {
        context.checking(new Expectations() {{
            oneOf(handler).handle(request, response);
        }});
    }

    @Test
    public void handleByDefaultHandlerIfNoRequestMatches() throws Exception {
        router.setUnknownHandler(handler);
        requestUri("/404");

        expectHandingBy(handler);

        router.dispatch(request, response);
    }


    @Test
    public void handleByInternalDefaultHandlerIfNoRequestMatches() throws Exception {
        requestUri("/404");

        context.checking(new Expectations() {{
            oneOf(response).setStatus(404);
        }});

        router.dispatch(request, response);
    }

    private void requestUri(final String uri) {
        context.checking(new Expectations() {{
            allowing(request).getRequestURI();
            will(returnValue(uri));
        }});
    }
}
