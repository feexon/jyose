package com.feexon.jyose.matchers;

import com.feexon.jyose.HttpRequest;
import com.feexon.jyose.Matcher;

/**
 * Created by L.x on 15-11-17.
 */
public class Matchers {
    public static <T> Matcher<T> equal(T target) {
        return new Equal<T>(target);
    }

    public static Matcher<String> startsWith(final String prefix) {
        return new StringStartsWith(prefix);
    }

    public static Matcher<String> endsWith(String suffix) {
        return new StringEndsWith(suffix);
    }

    public static Matcher<HttpRequest> path(Matcher<String> matcher) {
        return new PathMatcher(matcher);
    }

    public static <T> Matcher<T> any(Class<T> target) {
        return new Any<T>();
    }

}
