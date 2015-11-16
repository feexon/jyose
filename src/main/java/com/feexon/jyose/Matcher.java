package com.feexon.jyose;

/**
 * Created by L.x on 15-11-17.
 */
public interface Matcher<T> {
    boolean matches(T actual);
}
