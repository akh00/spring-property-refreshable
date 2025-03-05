package com.example.refreshable.config.env;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class RefreshFilePropertyProvider implements PropertiesProvider
{
    private final String refreshFilePath;

    public RefreshFilePropertyProvider(String refreshFilePath) {
        this.refreshFilePath = refreshFilePath;
    }

    @Override
    public Map<String, Object> getProperties() throws IOException {
            Map<String, Object> map = new HashMap<>();

            PropertiesLoaderUtils.loadProperties(new FileSystemResource(refreshFilePath))
                    .forEach((key, value) -> map.putIfAbsent((String) key, value));
            return map;
    }

}
