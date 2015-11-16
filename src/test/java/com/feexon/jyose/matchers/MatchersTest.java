package com.feexon.jyose.matchers;

import org.junit.Test;

import static com.feexon.jyose.matchers.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by L.x on 15-11-17.
 */
public class MatchersTest {
    @Test
    public void equality() throws Exception {
        assertTrue(equal("one").matches("one"));
        assertFalse(equal("one").matches("other"));
        assertFalse(equal(null).matches("one"));
        assertFalse(equal("one").matches(null));
        assertTrue(equal(null).matches(null));
    }

    @Test
    public void stringStartsWith() throws Exception {
        assertTrue(startsWith("/css/").matches("/css/index.css"));
        assertFalse(startsWith("/css/").matches("/shop/css/index.css"));
        assertFalse(startsWith("/css/").matches("/images/fav.png"));
        assertFalse(startsWith(null).matches(null));
        assertFalse(startsWith(null).matches("one"));
        assertFalse(startsWith("one").matches(null));
    }

    @Test
    public void stringEndsWith() throws Exception {
        assertTrue(endsWith(".gif").matches("/css/logo.gif"));
        assertTrue(endsWith(".gif").matches("logo.gif"));
        assertFalse(endsWith(".gif").matches("/css/logo.gif.tmp"));
        assertFalse(endsWith(null).matches(null));
        assertFalse(endsWith(null).matches("one"));
        assertFalse(endsWith("one").matches(null));
    }

    @Test
    public void matchesAny() throws Exception {
        assertTrue(any(Object.class).matches(true));
        assertTrue(any(Object.class).matches(null));
        assertTrue(any(Object.class).matches(false));
    }
}