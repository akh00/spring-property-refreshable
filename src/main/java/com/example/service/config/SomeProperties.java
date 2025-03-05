package com.example.service.config;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "app.example.properties")
@RefreshScope
public class SomeProperties
{
    private boolean someProperty = false;
    private Integer somePropertyPercent;

    public void setSomePropertyPercent(Integer somePropertyPercent)
    {
        this.somePropertyPercent = somePropertyPercent;
    }

    public boolean isSomeProperty()
    {
        return somePropertyPercent == null ? someProperty : ThreadLocalRandom.current().nextInt(100) < somePropertyPercent;
    }

    public void setSomeProperty(boolean someProperty)
    {
        this.someProperty = someProperty;
    }

}
