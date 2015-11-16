package com.feexon.jyose.matchers;

import com.feexon.jyose.Matcher;

/**
 * Created by L.x on 15-11-17.
 */
public class StringStartsWith implements Matcher<String> {
    private final String prefix;

    public StringStartsWith(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean matches(String actual) {
        return actual != null && prefix != null && actual.startsWith(prefix);
    }
}
