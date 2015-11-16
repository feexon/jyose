package com.feexon.jyose.pages;

import com.feexon.jyose.utils.IO;
import org.cyberneko.html.parsers.DOMParser;
import org.hamcrest.Matcher;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

/**
 * Created by L.x on 15-11-16.
 */
public class Page {
    protected HttpURLConnection connection;
    private ByteArrayOutputStream out;

    public Page(HttpURLConnection connection) {
        this.connection = connection;

    }

    public String asText() throws IOException {
        if (out == null) {
            out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            IO.copy(in, out);
        }
        return out.toString();
    }

    public void renderAs(Matcher<String> matcher) {
        assertThat(connection.getContentType(), matcher);
    }

    public void renderAs(String contentType) {
        renderAs(equalTo(contentType));
    }

    protected Element asHtml() throws IOException {
        DOMParser parser = new DOMParser();
        try {
            parser.parse(new InputSource(new StringReader(asText())));

            return parser.getDocument().getDocumentElement();
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }

    public void hasElement(String selector) throws IOException {
        assertThat(asHtml(), hasUniqueSelector(selector));
    }

    public void hasElement(String selector, Matcher<Element> matcher) throws IOException {
        assertThat(asHtml(), hasUniqueSelector(selector, matcher));
    }
}
