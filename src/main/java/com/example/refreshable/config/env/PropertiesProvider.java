package com.example.refreshable.config.env;

import java.io.IOException;
import java.util.Map;

public interface PropertiesProvider
{
    Map<String, Object> getProperties();
}
