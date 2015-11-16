package com.feexon.jyose.handlers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.HttpResponse;
import com.feexon.jyose.Matcher;
import com.feexon.jyose.RequestHandler;
import com.feexon.jyose.internals.MimeTypeRegistry;
import com.feexon.jyose.internals.MimeTypeResolver;
import com.feexon.jyose.internals.ResourceResolver;
import com.feexon.jyose.matchers.Matchers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.feexon.jyose.actions.Actions.from;

/**
 * Created by L.x on 15-11-17.
 */
public class ResourceHandler implements RequestHandler {

    private Map<Matcher<HttpRequest>, String> resourcesMapping = new LinkedHashMap<Matcher<HttpRequest>, String>();

    private final ResourceResolver resourceResolver;
    private MimeTypeResolver mimeTypeResolver = new MimeTypeRegistry();

    public ResourceHandler(ResourceResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        URL url = getResource(request);
        response.setContentType(mimeTypeResolver.resolve(url));
        response.render(from(url));
    }

    private URL getResource(HttpRequest request) throws IOException {
        String uri = resourceUri(request);
        URL url = resourceResolver.getResourceURL(uri);
        if (url == null) {
            throw new FileNotFoundException(uri);
        }
        return url;
    }

    private String resourceUri(HttpRequest request) {
        for (Matcher<HttpRequest> matcher : resourcesMapping.keySet()) {
            if (matcher.matches(request)) {
                return resourcesMapping.get(matcher);
            }
        }
        return request.getRequestURI();
    }

    public void alias(String uri, String path) {
        resourcesMapping.put(Matchers.path(Matchers.equal(uri)), path);
    }

    public void setMimeTypeResolver(MimeTypeResolver mimeTypeResolver) {
        this.mimeTypeResolver = mimeTypeResolver;
    }
}
