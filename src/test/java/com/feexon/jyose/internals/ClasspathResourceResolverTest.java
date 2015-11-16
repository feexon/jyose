package com.feexon.jyose.internals;

import com.feexon.jyose.utils.IO;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-17.
 */
public class ClasspathResourceResolverTest {
    @Test
    public void openResourceAsStream() throws Exception {
        ClasspathResourceResolver resourceResolver = new ClasspathResourceResolver("pkg");

        URL location = resourceResolver.getResourceURL("resource.txt");

        assertThat(IO.toString(location), equalTo("<pkg resource>"));
    }

    @Test
    public void openResourceAsStreamInDefaultPackage() throws Exception {
        ClasspathResourceResolver resourceResolver = new ClasspathResourceResolver(null);

        URL location = resourceResolver.getResourceURL("resource.txt");

        assertThat(IO.toString(location), equalTo("<resource>"));
    }
}