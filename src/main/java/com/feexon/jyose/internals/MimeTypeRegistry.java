package com.feexon.jyose.internals;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.feexon.jyose.MimeTypes.*;

/**
 * Created by L.x on 15-11-17.
 */
public class MimeTypeRegistry implements MimeTypeResolver {
    private Map<String, String> mimeTypeMapping = new HashMap<String, String>();

    public MimeTypeRegistry() {
        addMapping("html", TEXT_HTML);
        addMapping("png", IMAGES_PNG);
        addMapping("json", APPLICATION_JSON);
        addMapping("css", TEXT_CSS);
    }

    public void addMapping(String extension, String mimeType) {
        mimeTypeMapping.put(extension, mimeType);
    }

    @Override
    public String resolve(URL url) {
        return mimeTypeMapping.get(getExtension(url.getPath()));
    }

    private String getExtension(String file) {
        int extPos = file.lastIndexOf('.');
        if (extPos != -1) {
            return file.substring(extPos + 1);
        }
        int filePos = file.indexOf('/');
        if (filePos != -1) {
            return file.substring(filePos + 1);
        }
        return file;
    }
}
