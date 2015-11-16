package com.feexon.jyose.matchers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.Matcher;

/**
 * Created by L.x on 15-11-16.
 */
public class PathMatcher implements Matcher<HttpRequest> {
    protected Matcher<String> matcher;

    public PathMatcher(Matcher<String> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(HttpRequest request) {
        return matcher.matches(request.getRequestURI());
    }

}
