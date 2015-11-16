package com.feexon.jyose.matchers;

import com.feexon.jyose.Matcher;

/**
 * Created by L.x on 15-11-17.
 */
public class Any<T> implements Matcher<T> {
    @Override
    public boolean matches(T actual) {
        return true;
    }
}
