package com.feexon.jyose.internals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by L.x on 15-11-17.
 */
public class ClasspathResourceResolver implements ResourceResolver {
    private String parent;

    public ClasspathResourceResolver(String packageName) {
        parent = pathOf(packageName);
    }

    public static ResourceResolver baseOn(Class targetClass) {
        return new ClasspathResourceResolver(targetClass.getPackage().getName());
    }

    private String pathOf(String packageName) {
        return packageName == null ? "" : (packageName.replace('.', '/') + "/");
    }

    @Override
    public URL getResourceURL(String uri) throws IOException {
        return ClassLoader.getSystemResource(uri(uri));
    }


    private String uri(String uri) {
        if (uri.startsWith("/")) {
            return parent + uri.substring(1);
        }
        return parent + uri;
    }
}
