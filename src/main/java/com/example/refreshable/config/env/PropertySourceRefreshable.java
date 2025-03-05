package com.example.refreshable.config.env;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.core.env.EnumerablePropertySource;

public class PropertySourceRefreshable extends EnumerablePropertySource<Map<String, Object>>
        implements RefreshableSource
{
    private Map<String, Object> properties = new HashMap<>();

    public PropertySourceRefreshable(String name) {
        super(name);
    }

    @Override
    public String[] getPropertyNames() {
        return properties.keySet().toArray(new String[0]);
    }

    @Override
    public Object getProperty(String name) {
        return properties.get(name);
    }

    @Override
    public List<String> refresh(Map<String, Object> newProperties) {
        Set<String> deleted = new HashSet<>(properties.keySet());
        deleted.removeAll(newProperties.keySet());
        properties.keySet().removeAll(deleted);

        List <String> updatedProperties = newProperties.entrySet().stream()
                .filter(entry -> !Objects.equals(entry.getValue(), properties.get(entry.getKey())))
                .map(Map.Entry::getKey).toList();

        List<String> propertiesToRefresh = new ArrayList<>(updatedProperties);
        propertiesToRefresh.addAll(deleted);

        properties.putAll(newProperties);
        return propertiesToRefresh;
    }
}
