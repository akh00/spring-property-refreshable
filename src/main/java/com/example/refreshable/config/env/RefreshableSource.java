package com.example.refreshable.config.env;

import java.util.List;
import java.util.Map;

public interface RefreshableSource
{
    List<String> refresh(Map<String, Object> newProperties);
}
