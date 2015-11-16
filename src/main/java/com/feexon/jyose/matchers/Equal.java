package com.feexon.jyose.matchers;

import com.feexon.jyose.Matcher;

/**
 * Created by L.x on 15-11-17.
 */
public class Equal<T> implements Matcher<T> {
    private T target;

    public Equal(T target) {
        this.target = target;
    }

    @Override
    public boolean matches(T actual) {
        return eq(actual);
    }

    private boolean eq(T actual) {
        return target == null ? actual == null : target.equals(actual);
    }
}
