package com.example.refreshable.config.env;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class RefreshFilePropertyProvider implements PropertiesProvider
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshFilePropertyProvider.class);
    private final String refreshFilePath;

    public RefreshFilePropertyProvider(String refreshFilePath) {
        this.refreshFilePath = refreshFilePath;
    }

    @Override
    public Map<String, Object> getProperties() {
            Map<String, Object> map = new HashMap<>();

            try
            {
                PropertiesLoaderUtils.loadProperties(new FileSystemResource(refreshFilePath))
                        .forEach((key, value) -> map.putIfAbsent((String) key, value));
            }
            catch (IOException e) {
                LOGGER.info("Can not load properties from file {}", refreshFilePath);
            }
            return map;
    }

}
