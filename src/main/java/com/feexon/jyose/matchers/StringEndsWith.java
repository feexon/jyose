package com.feexon.jyose.matchers;

import com.feexon.jyose.Matcher;

/**
 * Created by L.x on 15-11-17.
 */
public class StringEndsWith implements Matcher<String> {
    private final String suffix;

    public StringEndsWith(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean matches(String actual) {
        return actual != null && suffix != null && actual.endsWith(suffix);
    }
}
