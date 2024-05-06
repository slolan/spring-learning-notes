package com.xxxs.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ClassPathResource implements Resource {
    private final String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.path);

        if (inputStream == null)
            throw new FileNotFoundException(this.path + " cannot be opened.");

        return inputStream;
    }
}
