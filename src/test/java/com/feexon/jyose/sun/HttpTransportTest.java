package com.feexon.jyose.sun;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.Router;
import com.sun.net.httpserver.HttpExchange;
import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.api.Action;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.action.VoidAction;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.sameInstance;
import static org.jmock.Expectations.throwException;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by L.x on 15-11-16.
 */
public class HttpTransportTest {
    @Mock
    private HttpExchange exchange;
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Mock
    private Router router;
    private HttpTransport transport;

    @Before
    public void setUp() throws Exception {
        transport = new HttpTransport(router);
    }

    @Test
    public void closeExchangeAfterRequestDispatched() throws Exception {
        dispatching(successful());

        exchangeWillBeClosed();

        transport.handle(exchange);
    }



    @Test
    public void closeExchangeEvenIfOccurErrorOnDispatching() throws Exception {
        final IOException error = new IOException();
        dispatching(throwException(error));

        exchangeWillBeClosed();

        try {
            transport.handle(exchange);
            fail("should raising exception");
        } catch (IOException actual) {
            assertThat(actual, sameInstance(error));
        }
    }

    private VoidAction successful() {
        return VoidAction.INSTANCE;
    }

    private void dispatching(final Action action) throws IOException {
        context.checking(new Expectations() {{
            oneOf(router).dispatch(with(any(HttpRequest.class)), with(any(HttpResponse.class)));
            will(action);
        }});
    }

    private void exchangeWillBeClosed() {
        context.checking(new Expectations() {{
            oneOf(exchange).close();
        }});
    }

}