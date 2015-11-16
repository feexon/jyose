package com.feexon.jyose.actions;

import com.feexon.jyose.RenderingAction;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static com.feexon.jyose.actions.Actions.from;
import static com.feexon.jyose.actions.Actions.withResource;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by L.x on 15-11-16.
 */
public class ActionsTest {
    private OutputStream out = new ByteArrayOutputStream();

    @Test
    public void renderAsText() throws Exception {
        Actions.withText("hello").run(out);

        assertThat(out.toString(), equalTo("hello"));
    }

    @Test
    public void renderFromURI() throws Exception {
        from(getClass().getResource("/resource.txt")).run(out);

        assertThat(out.toString(), equalTo("<resource>"));
    }

    @Test
    public void renderFromURIMoreThanOnce() throws Exception {
        RenderingAction action = from(getClass().getResource("/resource.txt"));

        action.run(out);
        action.run(out);
        assertThat(out.toString(), equalTo("<resource><resource>"));
    }

    @Test
    public void renderWithUnknownResourceWillReportingError() throws Exception {
        try {
            withResource("unknown.txt");
            fail("should failed");
        } catch (Exception expected) {
            assertThat(expected.getMessage(), containsString("unknown.txt"));
        }
    }
}