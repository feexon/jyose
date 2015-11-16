package com.feexon.jyose.handlers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.internals.ResourceResolver;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URL;

import static org.junit.Assert.fail;

/**
 * Created by L.x on 15-11-17.
 */
public class ResourceHandlerTest {
    @Mock
    private ResourceResolver resourceResolver;
    @Mock
    private HttpResponse response;
    @Mock
    private HttpRequest request;
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void alias() throws Exception {
        ResourceHandler handler = new ResourceHandler(resourceResolver);
        handler.alias("/", "index.html");
        context.checking(new Expectations() {{
            allowing(request).getRequestURI();
            will(returnValue("/"));
            ignoring(response);
        }});

        context.checking(new Expectations() {{
            oneOf(resourceResolver).getResourceURL("index.html");
            will(returnValue(new URL("http://alias")));
        }});

        handler.handle(request, response);
    }

    @Test
    public void throwsExceptionIfResourceNotFound() throws Exception {
        ResourceHandler handler = new ResourceHandler(resourceResolver);
        context.checking(new Expectations() {{
            ignoring(request).getRequestURI();
        }});
        context.checking(new Expectations() {{
            oneOf(resourceResolver).getResourceURL(with(any(String.class)));
            will(returnValue(null));
        }});

        try {
            handler.handle(request, response);
            fail("should raising exception");
        } catch (FileNotFoundException actual) {
        }
    }
}