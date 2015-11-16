package com.feexon.jyose.internals;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.feexon.jyose.MimeTypes.IMAGES_PNG;
import static com.feexon.jyose.MimeTypes.TEXT_HTML;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-11-17.
 */
public class MimeTypeRegistryTest {
    MimeTypeRegistry registry = new MimeTypeRegistry();

    @Test
    public void resolveInternals() throws Exception {
        assertThat(registry.resolve(url("index.html")), equalTo(TEXT_HTML));
        assertThat(registry.resolve(url("index.html?t=123")), equalTo(TEXT_HTML));
        assertThat(registry.resolve(url("index.html#hash")), equalTo(TEXT_HTML));
        assertThat(registry.resolve(url("index.png")), equalTo(IMAGES_PNG));
    }

    @Test
    public void resolveCustomMapping() throws Exception {
        registry.addMapping("", TEXT_HTML);

        assertThat(registry.resolve(url("")), equalTo(TEXT_HTML));
    }

    private URL url(String file) throws MalformedURLException {
        return new URL("http://localhost/" + file);
    }
}